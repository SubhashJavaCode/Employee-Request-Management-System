package com.fuji.erms.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EmployeeMasterUtilTest {

	@Test
	void testChangeNameFjLevel() {
		assertEquals("FJ07", EmployeeMasterUtil.changeNameFjLevel("1"));
		assertEquals("FJ08", EmployeeMasterUtil.changeNameFjLevel("2"));
		assertEquals("FJ09", EmployeeMasterUtil.changeNameFjLevel("3"));
		assertEquals("FJ10", EmployeeMasterUtil.changeNameFjLevel("4"));
		assertEquals("FJ11", EmployeeMasterUtil.changeNameFjLevel("5"));

	}

	@Test
	void testChangeNameFjLevelReverse() {
		assertEquals("1", EmployeeMasterUtil.changeNameFjLevelReverse("FJ07"));
		assertEquals("2", EmployeeMasterUtil.changeNameFjLevelReverse("FJ08"));
		assertEquals("3", EmployeeMasterUtil.changeNameFjLevelReverse("FJ09"));
		assertEquals("4", EmployeeMasterUtil.changeNameFjLevelReverse("FJ10"));
		assertEquals("5", EmployeeMasterUtil.changeNameFjLevelReverse("FJ11"));

	}

	@Test
	void testChangeNameJlptLevel() {

		assertEquals("JLPT N1", EmployeeMasterUtil.changeNameJlptLevel("1"));
		assertEquals("JLPT N2", EmployeeMasterUtil.changeNameJlptLevel("2"));
		assertEquals("JLPT N3", EmployeeMasterUtil.changeNameJlptLevel("3"));
		assertEquals("JLPT N4", EmployeeMasterUtil.changeNameJlptLevel("4"));
		assertEquals("JLPT N5", EmployeeMasterUtil.changeNameJlptLevel("5"));
		assertEquals("NA", EmployeeMasterUtil.changeNameJlptLevel("0"));

	}

	@Test
	void testChangeNameJlptLevelReverse() {
		assertEquals("1",
				EmployeeMasterUtil.changeNameJlptLevelReverse("JLPT N1"));
		assertEquals("2",
				EmployeeMasterUtil.changeNameJlptLevelReverse("JLPT N2"));
		assertEquals("3",
				EmployeeMasterUtil.changeNameJlptLevelReverse("JLPT N3"));
		assertEquals("4",
				EmployeeMasterUtil.changeNameJlptLevelReverse("JLPT N4"));
		assertEquals("5",
				EmployeeMasterUtil.changeNameJlptLevelReverse("JLPT N5"));
		assertEquals("0", EmployeeMasterUtil.changeNameJlptLevelReverse("NA"));

	}

}