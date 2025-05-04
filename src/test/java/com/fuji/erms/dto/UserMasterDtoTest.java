package com.fuji.erms.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserMasterDtoTest {
	UserMasterDto user = new UserMasterDto();

	@Test
	void testgetUserId() {
		user.setUserId(1);
		assertEquals(1, user.getUserId());

	}

	@Test
	void testsetUserId() {
		user.setUserId(1);
		assertEquals(1, user.getUserId());
	}

	@Test
	void testgetUserName() {
		user.setUserName("Subhash");
		assertEquals("Subhash", user.getUserName());
	}

	@Test
	void testsetUserName() {
		user.setUserName("Subhash");
		assertEquals("Subhash", user.getUserName());
	}

	@Test
	void testgetPassword() {
		user.setPassword("Subhash@123");
		assertEquals("Subhash@123", user.getPassword());

	}

	@Test
	void testsetPassword() {
		user.setPassword("Subhash@123");
		assertEquals("Subhash@123", user.getPassword());
	}

	@Test
	void testgetUserRole() {
		user.setUserRole("Employee");
		assertEquals("Employee", user.getUserRole());
	}

	@Test
	void setUserRole() {
		user.setUserRole("Employee");
		assertEquals("Employee", user.getUserRole());
	}

	@Test
	void testAllArgsConstructor() {
		UserMasterDto users = new UserMasterDto(1, "Subhash", "Subhash@123",
				"Employee");
		assertEquals(1, users.getUserId());
		assertEquals("Subhash", users.getUserName());
		assertEquals("Subhash@123", users.getPassword());
		assertEquals("Employee", users.getUserRole());
	}

}