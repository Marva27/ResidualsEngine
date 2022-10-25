package com.residualsengine.www.exception;

import org.springframework.stereotype.Component;

@Component
public class Validation {

	public void ensureFieldNotZeroInt(String fieldName, Object value) {
		if((int) value  == 0)
			throw new ApplicationError(ApplicationError.ErrorType.bad_request, fieldName, fieldName + " cannot be zero");
	}
	
	public void ensureFieldNotZeroDouble(String fieldName, Object value) {
		if((double) value  == 0.0)
			throw new ApplicationError(ApplicationError.ErrorType.bad_request, fieldName, fieldName + " cannot be zero");
	}
	
	public void ensureFieldNotNull(String fieldName, Object value) {
		if(value == "" || value == null)
			throw new ApplicationError(ApplicationError.ErrorType.bad_request, fieldName, fieldName + " cannot be empty or null");
	}
}
