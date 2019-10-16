package com.agiles231.mokta.error;

public class Error {

	String errorCode;
	String errorSummary;
	String errorLink;
	String errorId;
	String[] errorCauses;
	
	public Error(String errorCode, String errorSummary, String errorLink, String errorId, String[] errorCauses) {
		super();
		this.errorCode = errorCode;
		this.errorSummary = errorSummary;
		this.errorLink = errorLink;
		this.errorId = errorId;
		this.errorCauses = errorCauses;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorSummary() {
		return errorSummary;
	}

	public String getErrorLink() {
		return errorLink;
	}

	public String getErrorId() {
		return errorId;
	}

	public String[] getErrorCauses() {
		return errorCauses;
	}
	
	
}
