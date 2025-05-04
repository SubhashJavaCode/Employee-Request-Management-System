package com.fuji.erms.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JwtExceptionTest {

	JwtException jwtException = new JwtException(null);

	@Test
	void testJwtException() {

	}

	@Test
	void testGetErrorMessage() {
		jwtException.setErrorMessage("Error");
		assertEquals("Error", jwtException.getErrorMessage());
	}

	@Test
	void testSetErrorMessage() {
		jwtException.setErrorMessage("Error");
		assertEquals("Error", jwtException.getErrorMessage());
	}

}