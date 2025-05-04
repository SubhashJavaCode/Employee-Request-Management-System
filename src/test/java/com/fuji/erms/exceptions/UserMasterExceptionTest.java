package com.fuji.erms.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserMasterExceptionTest {

	UserMasterException userMasterException = new UserMasterException(null);

	@Test
	void testUserMasterException() {
	
	}

	@Test
	void testGetErrorMessage() {
		userMasterException.setErrorMessage("Error");
		assertEquals("Error", userMasterException.getErrorMessage());
	}

	@Test
	void testSetErrorMessage() {
		userMasterException.setErrorMessage("Error");
		assertEquals("Error", userMasterException.getErrorMessage());
	}

}