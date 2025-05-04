package com.fuji.erms.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JwtRequestTest {
	JwtRequest jwt = new JwtRequest();

	@Test
	void testgetUserId() {
		jwt.setUserId(1);
		assertEquals(1, jwt.getUserId());
	}

	@Test
	void testsetUserId() {
		jwt.setUserId(1);
		assertEquals(1, jwt.getUserId());

	}

	@Test
	void testgetPassword() {
		jwt.setPassword("abc@123");
		assertEquals("abc@123", jwt.getPassword());
	}

	@Test
	void testsetPassword() {
		jwt.setPassword("abc@123");
		assertEquals("abc@123", jwt.getPassword());
	}

	@Test
	void testgetUserRole() {
		jwt.setUserRole("LM");
		assertEquals("LM", jwt.getUserRole());

	}

	@Test
	void testsetUserRole() {
		jwt.setUserRole("LM");
		assertEquals("LM", jwt.getUserRole());
	}
	
}