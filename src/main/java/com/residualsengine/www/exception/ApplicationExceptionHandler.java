package com.residualsengine.www.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.residualsengine.www.model.Error;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {RuntimeException.class})
	public ResponseEntity<Error> handleApplicationError(ApplicationError ex) {
		String errorId = null;
		String errorDescription = null;
		HttpStatus httpStatus = null;
		
		if(ex instanceof ApplicationError) {
			errorId = ((ApplicationError) ex).getErrorId();
			errorDescription = ((ApplicationError) ex).getErrorMessage();
		}
		
		if(errorId.startsWith(ApplicationError.ErrorType.bad_request.toString()))
			httpStatus = HttpStatus.BAD_REQUEST;
		else if(errorId.startsWith(ApplicationError.ErrorType.server_error.toString()))
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		else if(errorId.startsWith(ApplicationError.ErrorType.functional_error.toString()))
			httpStatus = HttpStatus.NOT_FOUND;
		
		Error error = new Error();
		error.setErrorId(errorId);
		error.setErrorDescription(errorDescription);
		return new ResponseEntity<Error>(error, httpStatus);
	}
}
