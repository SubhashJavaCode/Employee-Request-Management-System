package com.fuji.erms.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserMasterTest {

	UserMaster user = new UserMaster();

	@Test
	void testgetUserId() {
		user.setUserId(29915);
		assertEquals(29915, user.getUserId());

	}

	@Test
	void testsetUserId() {
		user.setUserId(29915);
		assertEquals(29915, user.getUserId());
	}

	@Test
	void testgetUserName() {
		user.setUserName("Vivek");
		assertEquals("Vivek", user.getUserName());

	}

	@Test
	void testsetUserName() {
		user.setUserName("Vivek");
		assertEquals("Vivek", user.getUserName());
	}

	@Test
	void testgetPassword() {
		user.setPassword("abc@123");
		assertEquals("abc@123", user.getPassword());

	}

	@Test
	void testsetPassword() {
		user.setPassword("abc@123");
		assertEquals("abc@123", user.getPassword());
	}

	@Test
	void testgetUserRole() {
		user.setUserRole("PM");
		assertEquals("PM", user.getUserRole());
	}

	@Test
	void testsetUserRole() {
		user.setUserRole("PM");
		assertEquals("PM", user.getUserRole());
	}

	@Test
	void testgetEmployeeMaster() {
		user.setEmployeeMaster(null);
		assertEquals(null, user.getEmployeeMaster());
	}

	@Test
	void testsetEmployeeMaster() {
		user.setEmployeeMaster(null);
		assertEquals(null, user.getEmployeeMaster());

	}

}