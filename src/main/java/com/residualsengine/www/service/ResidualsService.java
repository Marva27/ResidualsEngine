package com.residualsengine.www.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.residualsengine.www.model.Residual;
import com.residualsengine.www.model.TermResidual;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

@Service
public class ResidualsService {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void storeResiduals(InputStream inputStream) {
		CsvParserSettings csvParserSettings = new CsvParserSettings();
		csvParserSettings.setHeaderExtractionEnabled(false);
		CsvParser csvParser = new CsvParser(csvParserSettings);
		List<Record> allRecords = csvParser.parseAllRecords(inputStream);
		String[] headerNames = allRecords.get(0).getValues();
		for(int i = 1; i < allRecords.size(); i++) {
			Residual residual = new Residual();
			residual.setModelYear(allRecords.get(i).getInt("MODELYEAR"));
			residual.setModelCode(allRecords.get(i).getString("MODELCODE"));
			residual.setMrm(allRecords.get(i).getInt("MRM"));
			List<TermResidual> termResiduals = new ArrayList<TermResidual>();
			for(int j = 0; j < headerNames.length; j++) {
				if(isTerm(headerNames[j])) {
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
	}
	
	private boolean isTerm(String currentHeader) {
		Pattern pattern = Pattern.compile("\\b([0-9]|[1-9][0-9])\\b");
		Matcher matcher = pattern.matcher(currentHeader);
		if(matcher.find())
			return true;
		return false;
	}
}
