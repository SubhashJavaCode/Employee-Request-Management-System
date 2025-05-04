package com.fuji.erms.model;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

class RequestTransactionTest {
	RequestTransaction req = new RequestTransaction();

	RequestTransaction requestTransaction = new RequestTransaction(1, 29961,
			"1", "1", 27841, null, 30001, null, "Remark: ok", null);

	@Test
	void test() {

	}

	@Test
	void testgetRequestId() {
		req.setRequestId(1);
		assertEquals(1, req.getRequestId());

	}

	@Test
	void testsetRequestId() {
		req.setRequestId(1);
		assertEquals(1, req.getRequestId());
	}

	@Test
	void testgetEmployeeId() {
		req.setEmployeeId(29920);
		assertEquals(29920, req.getEmployeeId());

	}

	@Test
	void testsetEmployeeId() {
		req.setEmployeeId(29920);
		assertEquals(29920, req.getEmployeeId());
	}

	@Test
	void testgetRequestType() {
		req.setRequestType("Manager Change");
		assertEquals("Manager Change", req.getRequestType());
	}

	@Test
	void testsetRequestType() {
		req.setRequestType("Manager Change");
		assertEquals("Manager Change", req.getRequestType());
	}

	@Test
	void testgetRequestStatus() {
		req.setRequestStatus("Pending");
		assertEquals("Pending", req.getRequestStatus());
	}

	@Test
	void testsetRequestStatus() {
		req.setRequestStatus("Pending");
		assertEquals("Pending", req.getRequestStatus());
	}

	@Test
	void testgetCreateUser() {
		req.setCreateUser(1);
		assertEquals(1, req.getCreateUser());

	}

	@Test
	void testsetCreateUser() {
		req.setCreateUser(1);
		assertEquals(1, req.getCreateUser());

	}

	@Test
	void testgetCreateDateTime() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter.setLenient(false);
		Date date = formatter.parse("02/01/2023");

		req.setCreateDateTime(date);
		assertEquals(date, req.getCreateDateTime());
	}

	@Test
	void testsetCreateDateTime() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter.setLenient(false);
		Date date = formatter.parse("02/01/2023");
		req.setCreateDateTime(date);
		assertEquals(date, req.getCreateDateTime());
	}

	@Test
	void testgetUpdateUser() {
		req.setUpdateUser(1);
		assertEquals(1, req.getUpdateUser());

	}

	@Test
	void testsetUpdateUser() {
		req.setUpdateUser(1);
		assertEquals(1, req.getUpdateUser());
	}

	@Test
	void testgetRemark() {
		req.setRemark("Aproove");
		assertEquals("Aproove", req.getRemark());

	}

	@Test
	void testsetRemark() {
		req.setRemark("Aproove");
		assertEquals("Aproove", req.getRemark());
	}

	@Test
	void testgetEmployeeMaster() {
		req.setEmployeeMaster(null);
		assertEquals(null, req.getEmployeeMaster());

	}

	@Test
	void testsetEmployeeMaster() {
		req.setEmployeeMaster(null);
		assertEquals(null, req.getEmployeeMaster());
	}
}