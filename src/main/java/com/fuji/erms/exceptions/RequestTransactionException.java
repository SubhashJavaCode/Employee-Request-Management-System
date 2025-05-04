package com.fuji.erms.exceptions;

/**
 * @author Subhash This class is used to Handle Runtime Exception for
 *         RequestTransaction Controller and Service
 */
public class RequestTransactionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public RequestTransactionException(String errorMessage) {
		super(errorMessage);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
