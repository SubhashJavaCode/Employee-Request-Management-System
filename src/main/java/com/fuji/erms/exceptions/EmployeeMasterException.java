package com.fuji.erms.exceptions;

public class EmployeeMasterException extends RuntimeException {

	/**
	 * @author Subhash This class is used to Handle Runtime Exception for
	 *         EmployeeMaster Controller and Service
	 */

	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public EmployeeMasterException(String errorMessage) {
		super(errorMessage);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
