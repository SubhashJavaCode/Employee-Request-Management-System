package com.fuji.erms.exceptions;

/**
 * @author HrushalG This class is used to Handle Runtime Exception for JWT
 *         Controller and Service
 */
public class JwtException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public JwtException(String errorMessage) {
		super(errorMessage);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
