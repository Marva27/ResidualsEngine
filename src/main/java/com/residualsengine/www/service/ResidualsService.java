package com.residualsengine.www.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.residualsengine.www.exception.ApplicationError;
import com.residualsengine.www.exception.Validation;
import com.residualsengine.www.model.Residual;
import com.residualsengine.www.model.ResidualsRequest;
import com.residualsengine.www.model.ResidualsResponse;
import com.residualsengine.www.model.Success;
import com.residualsengine.www.model.TermResidual;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

@Service
public class ResidualsService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private Validation validation;

	public ResponseEntity<Success> storeResiduals(InputStream inputStream) {
		try {
			CsvParserSettings csvParserSettings = new CsvParserSettings();
			csvParserSettings.setHeaderExtractionEnabled(false);
			CsvParser csvParser = new CsvParser(csvParserSettings);
			List<Record> allRecords = csvParser.parseAllRecords(inputStream);
			String[] headerNames = allRecords.get(0).getValues();
			for (int i = 1; i < allRecords.size(); i++) {
				Residual residual = new Residual();
				residual.setModelYear(allRecords.get(i).getInt("MODELYEAR"));
				residual.setModelCode(allRecords.get(i).getString("MODELCODE"));
				residual.setMrm(allRecords.get(i).getInt("MRM"));
				List<TermResidual> termResiduals = new ArrayList<TermResidual>();
				for (int j = 0; j < headerNames.length; j++) {
					if (isTerm(headerNames[j])) {
						TermResidual termResidual = new TermResidual();
						termResidual.setTerm(Integer.parseInt(headerNames[j]));
						termResidual.setResidualPercentage(allRecords.get(i).getDouble(headerNames[j]));
						termResiduals.add(termResidual);
					}
				}
				residual.setTermResidual(termResiduals);
				residual.setMileage(allRecords.get(i).getInt("ANNUALMILEAGE"));
				residual.setEffectiveDate(allRecords.get(i).getString("EFFECTIVEDATE"));
				residual.setExpiryDate(allRecords.get(i).getString("EXPIRYDATE"));
				mongoTemplate.save(residual);
			}
			Success success = new Success();
			success.setMessage("residuals loaded successfully");
			return new ResponseEntity<Success>(success, HttpStatus.OK);
		} catch (Exception e) {
			throw new ApplicationError(ApplicationError.ErrorType.server_error, "server_error",
					"error in loading residuals", e);
		}
	}

	private boolean isTerm(String currentHeader) {
		Pattern pattern = Pattern.compile("\\b([0-9]|[1-9][0-9])\\b");
		Matcher matcher = pattern.matcher(currentHeader);
		if (matcher.find())
			return true;
		return false;
	}

	public ResponseEntity<ResidualsResponse> getResidualValue(ResidualsRequest residualRequest) {
		validation.ensureFieldNotZeroInt("modelYear", residualRequest.getModelYear());
		validation.ensureFieldNotNull("modelYear", residualRequest.getModelYear());

		validation.ensureFieldNotNull("modelCode", residualRequest.getModelCode());

		validation.ensureFieldNotZeroDouble("msrp", residualRequest.getMsrp());
		validation.ensureFieldNotNull("msrp", residualRequest.getMsrp());

		Query query = new Query();
		query.addCriteria(Criteria.where("modelYear").is(residualRequest.getModelYear()))
				.addCriteria(Criteria.where("modelCode").is(residualRequest.getModelCode()));
		System.out.println("Query is: " + query.toString());

		Residual residual = mongoTemplate.findOne(query, Residual.class);
		if (residual == null)
			throw new ApplicationError(ApplicationError.ErrorType.bad_request, "no_residual_value",
					"no residual found for modelYear [" + residualRequest.getModelYear() + "] and modelCode ["
							+ residualRequest.getModelCode() + "]");

		boolean termResidualFound = false;
		List<TermResidual> termResiduals = residual.getTermResidual();

		ResidualsResponse residualsResponse = null;

		for (TermResidual eachTermResidual : termResiduals) {
			if (eachTermResidual.getTerm() == residualRequest.getTerm()) {
				residualsResponse = new ResidualsResponse();
				if (residualRequest.getMsrp() < residual.getMrm()) {
					residualsResponse.setResidualValue(residualRequest.getMsrp() * eachTermResidual.getResidualPercentage());
					residualsResponse.setResidualBase(residualRequest.getMsrp());
				} else {
					residualsResponse.setResidualValue((double) (residual.getMrm() * eachTermResidual.getResidualPercentage()));
					residualsResponse.setResidualBase((double) residual.getMrm());
				}
				residualsResponse.setResidualPercentage(eachTermResidual.getResidualPercentage());
				termResidualFound = true;
				break;
			}
		}

		if (!termResidualFound) {
			throw new ApplicationError(ApplicationError.ErrorType.server_error, "no_residual_value",
					"no residual found for modelYear [" + residualRequest.getModelYear() + "] and modelCode ["
							+ residualRequest.getModelCode() + "] and term [" + residualRequest.getTerm() + "]");
		}
		
		return new ResponseEntity<ResidualsResponse>(residualsResponse, HttpStatus.OK);
	}
}
