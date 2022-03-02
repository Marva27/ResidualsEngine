package com.residualsengine.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.residualsengine.www.model.message.ErrorMessage;
import com.residualsengine.www.model.message.SuccessfulResponse;
import com.residualsengine.www.model.residuals.ResidualsRequest;
import com.residualsengine.www.model.residuals.ResidualsResponse;
import com.residualsengine.www.service.ResidualsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class ResidualsEngineController {

	@Autowired
	private ResidualsService residualsService;

	@Operation(tags = {"Residuals" }, description = "To load residuals", summary = "Load Residuals")
	@RequestMapping(value = "/loadResiduals", produces = { "application/json" }, consumes = {
			"multipart/form-data" }, method = RequestMethod.POST)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Residuals loaded successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessfulResponse.class))),
			@ApiResponse(responseCode = "404", description = "File is invalid", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))) })
	public ResponseEntity<SuccessfulResponse> loadFinanceSpecialRates(
			@RequestParam("source file") MultipartFile uploadedFile) {
		return residualsService.loadResiduals(uploadedFile);
	}

	@Operation(tags = {"Residuals"}, description = "To get residuals based on model code", summary = "Get Residuals")
	@RequestMapping(value = "/getResiduals", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Residuals returned successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResidualsResponse.class))),
			@ApiResponse(responseCode = "404", description = "No residuals found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))) })
	public ResponseEntity<ResidualsResponse> getResiduals(
			@RequestBody ResidualsRequest residualsRequest) {
		return residualsService.getResiduals(residualsRequest);
	}

}
