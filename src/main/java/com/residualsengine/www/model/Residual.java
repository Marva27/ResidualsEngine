package com.residualsengine.www.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "modelYear", "modelCode", "mrm", "termResidual", "mileage", "effectiveDate", "expiryDate" })
@Generated("jsonschema2pojo")
public class Residual {

	@JsonProperty("modelYear")
	private Integer modelYear;
	@JsonProperty("modelCode")
	private String modelCode;
	@JsonProperty("mrm")
	private Integer mrm;
	@JsonProperty("termResidual")
	private List<TermResidual> termResidual = null;
	@JsonProperty("mileage")
	private Integer mileage;
	@JsonProperty("effectiveDate")
	private String effectiveDate;
	@JsonProperty("expiryDate")
	private String expiryDate;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("modelYear")
	public Integer getModelYear() {
		return modelYear;
	}

	@JsonProperty("modelYear")
	public void setModelYear(Integer modelYear) {
		this.modelYear = modelYear;
	}

	@JsonProperty("modelCode")
	public String getModelCode() {
		return modelCode;
	}

	@JsonProperty("modelCode")
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	@JsonProperty("mrm")
	public Integer getMrm() {
		return mrm;
	}

	@JsonProperty("mrm")
	public void setMrm(Integer mrm) {
		this.mrm = mrm;
	}

	@JsonProperty("termResidual")
	public List<TermResidual> getTermResidual() {
		return termResidual;
	}

	@JsonProperty("termResidual")
	public void setTermResidual(List<TermResidual> termResidual) {
		this.termResidual = termResidual;
	}

	@JsonProperty("mileage")
	public Integer getMileage() {
		return mileage;
	}

	@JsonProperty("mileage")
	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	@JsonProperty("effectiveDate")
	public String getEffectiveDate() {
		return effectiveDate;
	}

	@JsonProperty("effectiveDate")
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@JsonProperty("expiryDate")
	public String getExpiryDate() {
		return expiryDate;
	}

	@JsonProperty("expiryDate")
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}