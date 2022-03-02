package com.residualsengine.www.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResidualsEngineExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {RuntimeException.class})
	public ResponseEntity<ErrorMessage> handleExceptions(RuntimeException e) {
		String errorId = null, errorDescription = null;
		HttpStatus httpStatus = null;
		
		if(e instanceof ApplicationError) {
			errorId = ((ApplicationError) e).getErrorId();
			errorDescription = ((ApplicationError) e).getUserMessage();
		}
		
		if(e instanceof DuplicateKeyException) {
			errorId = "bad_request";
			errorDescription = "dealer id already exists";
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		
		if(errorId.startsWith(ApplicationError.ErrorType.badRequest.toString()))
			httpStatus = HttpStatus.BAD_REQUEST;
		if(errorId.startsWith(ApplicationError.ErrorType.functionalError.toString()))
			httpStatus = HttpStatus.NOT_FOUND;
		
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorId(errorId);
		errorMessage.setErrorMessage(errorDescription);
		
		return new ResponseEntity<ErrorMessage>(errorMessage, httpStatus);
	}
}
