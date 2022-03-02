package com.residualsengine.www.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.residualsengine.www.exception.ApplicationError;
import com.residualsengine.www.model.message.SuccessfulResponse;
import com.residualsengine.www.model.residuals.Residuals;
import com.residualsengine.www.model.residuals.ResidualsMap;
import com.residualsengine.www.model.residuals.ResidualsRequest;
import com.residualsengine.www.model.residuals.ResidualsResponse;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

@Component
public class ResidualsService {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	public ResponseEntity<SuccessfulResponse> loadResiduals(MultipartFile uploadedFile) {
		try {
			InputStream inputStream = uploadedFile.getInputStream();
			CsvParserSettings csvParserSettings = new CsvParserSettings();
			csvParserSettings.setHeaderExtractionEnabled(true);
			CsvParser csvParser = new CsvParser(csvParserSettings);
			List<com.univocity.parsers.common.record.Record> allRecords = csvParser.parseAllRecords(inputStream);
			allRecords.forEach(record -> {
				Residuals residuals = new Residuals();
				residuals.setModelYear(record.getInt("modelYear"));
				residuals.setModelCode(record.getString("modelCode"));
				List<ResidualsMap> residualsMapList = new ArrayList<ResidualsMap>();
				int currentTerm = 24;
				for(int i = 3; i <= record.getValues().length; i++) {
					ResidualsMap residualsMap = new ResidualsMap();
					residualsMap.setTerm(currentTerm);
					residualsMap.setRate(record.getDouble("term_" + currentTerm));
					residualsMapList.add(residualsMap);
					currentTerm = currentTerm + 12;
				}
				residuals.setResidualMap(residualsMapList);
				mongoTemplate.save(residuals);
			});
			SuccessfulResponse successfulResponse = new SuccessfulResponse();
			successfulResponse.setMessage("residuals loaded successfully");
			return new ResponseEntity<SuccessfulResponse>(successfulResponse, HttpStatus.OK);
		} catch(Exception e) {
			throw new ApplicationError(ApplicationError.ErrorType.badRequest, "file is invalid", e);
		}
	}

	public ResponseEntity<ResidualsResponse> getResiduals(ResidualsRequest residualsRequest) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("modelYear").is(residualsRequest.getModelYear()))
			.addCriteria(Criteria.where("modelCode").is(residualsRequest.getModelCode()));
			Residuals residuals = mongoTemplate.findOne(query, Residuals.class);
			if(residuals == null)
				throw new ApplicationError(ApplicationError.ErrorType.badRequest, "no residuals available", 
							"no residuals available for model code [" + residualsRequest.getModelCode() + "]");
			double residualBase = 0.0;
			double residualPercent = 0.0;
			double residualAmount = 0.0;
			if(residualsRequest.getMrm() < residualsRequest.getMsrp())
				residualBase = residualsRequest.getMrm();
			else
				residualBase = residualsRequest.getMsrp();
			ResidualsResponse residualsResponse = new ResidualsResponse();
			residualsResponse.setResidualBase(residualBase);
			for(ResidualsMap eachResidualsMap : residuals.getResidualsMap()) {
				if(eachResidualsMap.getTerm().equals(residualsRequest.getTerm())) {
					residualPercent = eachResidualsMap.getRate();
					residualAmount = residualBase * residualPercent;
				}
			}
			residualsResponse.setResidualPercent(residualPercent);
			residualsResponse.setResidual(residualAmount);
			return new ResponseEntity<ResidualsResponse>(residualsResponse, HttpStatus.OK);
		} catch (Exception e) {
			throw new ApplicationError(ApplicationError.ErrorType.functionalError, "error in calculating residuals", e);
		}
	}

}
