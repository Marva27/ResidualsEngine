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
@JsonPropertyOrder({ "residualBase", "residualPercent", "residual" })
@Generated("jsonschema2pojo")
public class ResidualsResponse {

	@Schema(example = "23500.75", description = "Base amount for calculating residuals", required = true)
	@JsonProperty("residualBase")
	private Double residualBase;
	@Schema(example = "0.52", description = "Residuals percentage based on vehicle lease term", required = true)
	@JsonProperty("residualPercent")
	private Double residualPercent;
	@Schema(example = "12220.39", description = "Residuals needed to calculate Lease BMP", required = true)
	@JsonProperty("residual")
	private Double residual;
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

	@JsonProperty("residualPercent")
	public Double getResidualPercent() {
		return residualPercent;
	}

	@JsonProperty("residualPercent")
	public void setResidualPercent(Double residualPercent) {
		this.residualPercent = residualPercent;
	}

	@JsonProperty("residual")
	public Double getResidual() {
		return residual;
	}

	@JsonProperty("residual")
	public void setResidual(Double residual) {
		this.residual = residual;
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