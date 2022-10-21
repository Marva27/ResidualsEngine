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
@JsonPropertyOrder({ "term", "residualPercentage" })
@Generated("jsonschema2pojo")
public class TermResidual {

	@JsonProperty("term")
	private Integer term;
	@JsonProperty("residualPercentage")
	private Double residualPercentage;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("term")
	public Integer getTerm() {
		return term;
	}

	@JsonProperty("term")
	public void setTerm(Integer term) {
		this.term = term;
	}

	@JsonProperty("residualPercentage")
	public Double getResidualPercentage() {
		return residualPercentage;
	}

	@JsonProperty("residualPercentage")
	public void setResidualPercentage(Double residualPercentage) {
		this.residualPercentage = residualPercentage;
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