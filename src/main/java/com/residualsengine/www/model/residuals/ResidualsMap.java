package com.residualsengine.www.model.residuals;

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
@JsonPropertyOrder({ "term", "rate" })
@Generated("jsonschema2pojo")
public class ResidualsMap {

	@JsonProperty("term")
	private Integer term;
	@JsonProperty("rate")
	private Double rate;
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

	@JsonProperty("rate")
	public Double getRate() {
		return rate;
	}

	@JsonProperty("rate")
	public void setRate(Double rate) {
		this.rate = rate;
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
