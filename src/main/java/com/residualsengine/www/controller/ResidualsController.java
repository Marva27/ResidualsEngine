package com.residualsengine.www.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.residualsengine.www.model.ResidualsRequest;
import com.residualsengine.www.model.ResidualsResponse;
import com.residualsengine.www.model.Success;
import com.residualsengine.www.service.ResidualsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class ResidualsController {
	
	@Autowired
	private ResidualsService residualsService;

	@Operation(summary = "Get Residual Value", description = "To get residual value")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Residuals returned successfully", content = @Content(schema = @Schema(implementation = ResidualsResponse.class))),
			@ApiResponse(responseCode = "404", description = "Residuals not found", content = @Content(schema = @Schema(implementation = Error.class))) })
	@RequestMapping(value = "/residuals/getResidual", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<ResidualsResponse> getResidualValue(@RequestBody ResidualsRequest residualRequest)
			throws JsonProcessingException {
		return null;
	}

	@RequestMapping(value = "/residuals/storeResidual", consumes = "multipart/form-data", produces = "application/json", method = RequestMethod.POST)
	@Operation(summary = "Store Residual Value", description = "To store residual value")
	@ApiResponses(value = { @ApiResponse(), @ApiResponse() })
	public ResponseEntity<Success> storeResidualValue(@RequestParam MultipartFile inputFile) throws IOException {
		residualsService.storeResiduals(inputFile.getInputStream());
		return null;
	}
}
