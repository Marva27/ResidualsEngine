package com.residualsengine.www.exception;

public class ApplicationError extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorId;
	public enum ErrorType {bad_request, functional_server, server_error};
	
	public ApplicationError(ErrorType errorType, String errorIdSuffix, String errorMessage) {
		super(errorMessage);
		this.errorId = generateErrorId(errorType, errorIdSuffix);
	}
	
	public ApplicationError(ErrorType errorType, String errorIdSuffix, String errorMessage, Throwable e) {
		super(errorMessage, e);
		this.errorId = generateErrorId(errorType, errorIdSuffix);
	}
	
	public String getErrorId() {
		return errorId;
	}

	private String generateErrorId(ErrorType errorType, String errorIdSuffix) {
		if(errorType != null && errorIdSuffix != null)
			return errorIdSuffix + "|" + errorType.toString();
		else if(errorType == null && errorIdSuffix != null)
			return errorIdSuffix;
		else if(errorType != null && errorIdSuffix == null)
			return errorType.toString();
		return "";
	}

}
