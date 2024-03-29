package com.residualsengine.www.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "modelYear", "modelCode", "msrp", "term", "annualMiles"})
@Generated("jsonschema2pojo")
public class ResidualsRequest {

	@JsonProperty("modelYear")
	private Integer modelYear;
	@JsonProperty("modelCode")
	private String modelCode;
	@JsonProperty("msrp")
	private Double msrp;
	@JsonProperty("term")
	private Integer term;
	@JsonProperty("annualMiles")
	private Integer annualMiles;
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

	@JsonProperty("msrp")
	public Double getMsrp() {
		return msrp;
	}

	@JsonProperty("msrp")
	public void setMsrp(Double msrp) {
		this.msrp = msrp;
	}
	
	@JsonProperty("term")
	public Integer getTerm() {
		return term;
	}

	@JsonProperty("term")
	public void setTerm(Integer term) {
		this.term = term;
	}
	
	@JsonProperty("annualMiles")
	public Integer getAnnualMiles() {
		return annualMiles;
	}

	@JsonProperty("annualMiles")
	public void setAnnualMiles(Integer annualMiles) {
		this.annualMiles = annualMiles;
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