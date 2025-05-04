package com.fuji.erms.exceptions;

/**
 * @author HrushalG This class is used to Handle Runtime Exception for
 *         UserMaster Controller and Service
 */

public class UserMasterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public UserMasterException(String errorMessage) {
		super(errorMessage);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
