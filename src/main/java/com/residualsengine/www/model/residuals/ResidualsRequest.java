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

import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "modelYear", "modelCode", "term", "mrm", "msrp" })
@Generated("jsonschema2pojo")
public class ResidualsRequest {

	@Schema(example = "2022", description = "Vehicle model year", required = true)
	@JsonProperty("modelYear")
	private Integer modelYear;
	@Schema(example = "CX9AEA", description = "Vehicle model code", required = true)
	@JsonProperty("modelCode")
	private String modelCode;
	@Schema(example = "24", description = "Vehicle lease term", required = true)
	@JsonProperty("term")
	private Integer term;
	@Schema(example = "23500.75", description = "MRM", required = true)
	@JsonProperty("mrm")
	private Double mrm;
	@Schema(example = "24000.25", description = "Vehicle MSRP", required = true)
	@JsonProperty("msrp")
	private Double msrp;
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

	@JsonProperty("term")
	public Integer getTerm() {
		return term;
	}

	@JsonProperty("term")
	public void setTerm(Integer term) {
		this.term = term;
	}

	@JsonProperty("mrm")
	public Double getMrm() {
		return mrm;
	}

	@JsonProperty("mrm")
	public void setMrm(Double mrm) {
		this.mrm = mrm;
	}

	@JsonProperty("msrp")
	public Double getMsrp() {
		return msrp;
	}

	@JsonProperty("msrp")
	public void setMsrp(Double msrp) {
		this.msrp = msrp;
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