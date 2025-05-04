package com.fuji.erms.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmployeeMasterExceptionTest {

	EmployeeMasterException employeeMasterException = new EmployeeMasterException(
			null);

	@Test
	void testEmployeeMasterException() {

	}

	@Test
	void testGetErrorMessage() {
		employeeMasterException.setErrorMessage("Error");
		assertEquals("Error", employeeMasterException.getErrorMessage());
	}

	@Test
	void testSetErrorMessage() {
		employeeMasterException.setErrorMessage("Error");
		assertEquals("Error", employeeMasterException.getErrorMessage());
	}

}