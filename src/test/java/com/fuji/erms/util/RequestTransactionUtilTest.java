package com.fuji.erms.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RequestTransactionUtilTest {

	@Test
	void testChangeNameType() {
		assertEquals("Manager Change",RequestTransactionUtil.changeNameType("1"));
		assertEquals("FJ Level Change",RequestTransactionUtil.changeNameType("2"));
		assertEquals("JLPT Level Update",RequestTransactionUtil.changeNameType("3"));
	assertNull(RequestTransactionUtil.changeNameType(null));
	}

	@Test
	void testChangeNameStatus() {
		assertEquals("Pending",RequestTransactionUtil.changeNameStatus("0"));
		assertEquals("Approved",RequestTransactionUtil.changeNameStatus("1"));
		assertEquals("Rejected",RequestTransactionUtil.changeNameStatus("2"));
		assertNull(RequestTransactionUtil.changeNameStatus(null));
	}

	@Test
	void testChangeNameRemark() {
		assertEquals("29915","29915",RequestTransactionUtil.changeNameRemark("1","1","29915","Fj10","JLPT N4","29915"));
	 assertEquals("FJ09","FJ09",RequestTransactionUtil.changeNameRemark("1","2","29915","Fj10","JLPT N4","29915"));
		assertEquals("N4","N4",RequestTransactionUtil.changeNameRemark("1","3","29915","Fj10","JLPT N4","29915"));
		assertNull(RequestTransactionUtil.changeNameRemark(null, null, null, null, null, null));
	}

}