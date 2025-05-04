package com.fuji.erms.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;

class RequestTransactionDtoTest {
	RequestTransactionDto reqdto = new RequestTransactionDto();

	@Test
	void testgetRequestId() {
		reqdto.setRequestId(1);
		assertEquals(1, reqdto.getRequestId());
	}

	@Test
	void testsetRequestId() {
		reqdto.setRequestId(1);
		assertEquals(1, reqdto.getRequestId());
	}

	@Test
	void testgetEmployeeId() {
		reqdto.setEmployeeId(29961);
		assertEquals(29961, reqdto.getEmployeeId());

	}

	@Test
	void testsetEmployeeId() {
		reqdto.setEmployeeId(29961);
		assertEquals(29961, reqdto.getEmployeeId());
	}

	@Test
	void testgetRequestType() {
		reqdto.setRequestType("Change FJ Level");
		assertEquals("Change FJ Level", reqdto.getRequestType());

	}

	@Test
	void testsetRequestType() {
		reqdto.setRequestType("Change FJ Level");
		assertEquals("Change FJ Level", reqdto.getRequestType());
	}

	@Test
	void testgetEmployeeName() {
		reqdto.setEmployeeName("Hrushal");
		assertEquals("Hrushal", reqdto.getEmployeeName());
	}

	@Test
	void testsetEmployeeName() {
		reqdto.setEmployeeName("Hrushal");
		assertEquals("Hrushal", reqdto.getEmployeeName());
	}

	@Test
	void testgetRequestStatus() {
		reqdto.setRequestStatus("Pending");
		assertEquals("Pending", reqdto.getRequestStatus());
	}

	@Test
	void testsetRequestStatus() {
		reqdto.setRequestStatus("Pending");
		assertEquals("Pending", reqdto.getRequestStatus());
	}

	@Test
	void testgetCreateUser() {
		reqdto.setCreateUser(1);
		assertEquals(1, reqdto.getCreateUser());
	}

	@Test
	void testsetCreateUser() {
		reqdto.setCreateUser(1);
		assertEquals(1, reqdto.getCreateUser());
	}

	@Test
	void testgetCreateDateTime() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter.setLenient(false);
		Date date = formatter.parse("02/01/2023");
		reqdto.setCreateDateTime(date);
		assertEquals(date, reqdto.getCreateDateTime());

	}

	@Test
	void testsetCreateDateTime() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter.setLenient(false);
		Date date = formatter.parse("02/01/2023");
		reqdto.setCreateDateTime(date);
		assertEquals(date, reqdto.getCreateDateTime());

	}

	@Test
	void testgetUpdateUser() {
		reqdto.setUpdateUser(1);
		assertEquals(1, reqdto.getUpdateUser());
	}

	@Test
	void setUpdateUser() {
		reqdto.setUpdateUser(1);
		assertEquals(1, reqdto.getUpdateUser());

	}

	@Test
	void testgetUpdateDateTime() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter.setLenient(false);
		Date date = formatter.parse("02/01/2023");

		reqdto.setUpdateDateTime(date);
		assertEquals(date, reqdto.getUpdateDateTime());

	}

	@Test
	void testsetUpdateDateTime() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter.setLenient(false);
		Date date = formatter.parse("02/01/2023");
		reqdto.setUpdateDateTime(date);
		assertEquals(date, reqdto.getUpdateDateTime());
	}

	@Test
	void testgetRemark() {
		reqdto.setRemark("Aprooved");
		assertEquals("Aprooved", reqdto.getRemark());

	}

	@Test
	void testsetRemark() {
		reqdto.setRemark("Aprooved");
		assertEquals("Aprooved", reqdto.getRemark());
	}

	@Test
	void testgetRequestBy() {
		reqdto.setRequestBy("PM");
		assertEquals("PM", reqdto.getRequestBy());

	}

	@Test
	void testsetRequestBy() {
		reqdto.setRequestBy("PM");
		assertEquals("PM", reqdto.getRequestBy());
	}
}