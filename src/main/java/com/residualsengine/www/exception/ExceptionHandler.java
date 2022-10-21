package com.residualsengine.www.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {RuntimeException.class})
	public ResponseEntity<Error> handleApplicationError() {
		
	}
}
