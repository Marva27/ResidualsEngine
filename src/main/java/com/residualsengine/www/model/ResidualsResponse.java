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
@JsonPropertyOrder({ "residualBase", "residualPercentage", "residualValue" })
@Generated("jsonschema2pojo")
public class ResidualsResponse {

	@JsonProperty("residualBase")
	private Double residualBase;
	@JsonProperty("residualPercentage")
	private Double residualPercentage;
	@JsonProperty("residualValue")
	private Double residualValue;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("residualBase")
	public Double getResidualBase() {
		return residualBase;
	}

	@JsonProperty("residualBase")
	public void setResidualBase(Double residualBase) {
		this.residualBase = residualBase;
	}

	@JsonProperty("residualPercentage")
	public Double getResidualPercentage() {
		return residualPercentage;
	}

	@JsonProperty("residualPercentage")
	public void setResidualPercentage(Double residualPercentage) {
		this.residualPercentage = residualPercentage;
	}

	@JsonProperty("residualValue")
	public Double getResidualValue() {
		return residualValue;
	}

	@JsonProperty("residualValue")
	public void setResidualValue(Double residualValue) {
		this.residualValue = residualValue;
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