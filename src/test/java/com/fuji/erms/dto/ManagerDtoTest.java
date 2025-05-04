package com.fuji.erms.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

class ManagerDtoTest {
	ManagerDto mngdto = new ManagerDto();

	@Test
	void testgetEmployeeId() {
		mngdto.setEmployeeId(33320);
		assertEquals(33320, mngdto.getEmployeeId());
	}

	@Test
	void testsetEmployeeId() {
		mngdto.setEmployeeId(33320);
		assertEquals(33320, mngdto.getEmployeeId());
	}

	@Test
	void testgetEmployeeName() {
		mngdto.setEmployeeName("Subhash");
		assertEquals("Vivek", mngdto.getEmployeeName());
	}

	@Test
	void testsetEmployeeName() {
		mngdto.setEmployeeName("Subhash");
		assertEquals("Subhash", mngdto.getEmployeeName());
	}

	@Test
	void testAllArgsConstructor() {
		ManagerDto mng = new ManagerDto(33320, "Subhash");
		assertNotNull(mng);
		assertEquals(33320, mng.getEmployeeId());
		assertEquals("Subhash", mng.getEmployeeName());

	}
}