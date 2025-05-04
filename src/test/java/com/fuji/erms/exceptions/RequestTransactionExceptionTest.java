package com.fuji.erms.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RequestTransactionExceptionTest {

	RequestTransactionException requestTransactionException = new RequestTransactionException(
			null);

	@Test
	void testRequestTransactionException() {
		
	}

	@Test
	void testGetErrorMessage() {
		requestTransactionException.setErrorMessage("Error");
		assertEquals("Error", requestTransactionException.getErrorMessage());
	}

	@Test
	void testSetErrorMessage() {
		requestTransactionException.setErrorMessage("Error");
		assertEquals("Error", requestTransactionException.getErrorMessage());
	}
}