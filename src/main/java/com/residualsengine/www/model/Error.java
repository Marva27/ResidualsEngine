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
@JsonPropertyOrder({ "errorId", "errorDescription" })
@Generated("jsonschema2pojo")
public class Error {

	@JsonProperty("errorId")
	private String errorId;
	@JsonProperty("errorDescription")
	private String errorDescription;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("errorId")
	public String getErrorId() {
		return errorId;
	}

	@JsonProperty("errorId")
	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}

	@JsonProperty("errorDescription")
	public String getErrorDescription() {
		return errorDescription;
	}

	@JsonProperty("errorDescription")
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
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