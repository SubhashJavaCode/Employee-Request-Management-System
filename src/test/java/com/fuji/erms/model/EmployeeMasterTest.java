package com.fuji.erms.model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

class EmployeeMasterTest {
	EmployeeMaster emp = new EmployeeMaster();

	@Test
	void testNoArgsConstructor() {
		assertNotNull(emp);

	}

	@Test
	void testAllArgsConstructor() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter.setLenient(false);
		Date date = formatter.parse("02/01/2023");
		EmployeeMaster emp = new EmployeeMaster(27841, "Apurva",
				"apurva@Fujitsu.com", 27888, "1", "2", "Java", "Sql", date, "1",
				null, null);
		assertNotNull(emp);
		assertEquals(27841, emp.getEmployeeId());
		assertEquals("Apurva", emp.getEmployeeName());
		assertEquals("apurva@Fujitsu.com", emp.getEmployeeEmail());
		assertEquals(27888, emp.getManagerId());
		assertEquals("1", emp.getFjLevel());
		assertEquals("2", emp.getJlptLevel());
		assertEquals("Java", emp.getPrimarySkill());
		assertEquals("Sql", emp.getSecondarySkill());

	}

	@Test
	void testGetEmployeeId() {
		emp.setEmployeeId(29920);
		assertEquals(29920, emp.getEmployeeId());
	}

	@Test
	void testSetEmployeeId() {
		emp.setEmployeeId(29920);
		assertEquals(29920, emp.getEmployeeId());
	}

	@Test
	void testgetEmployeeName() {
		emp.setEmployeeName("Sanket");
		assertEquals("Sanket", emp.getEmployeeName());

	}

	@Test
	void testsetEmployeeName() {
		emp.setEmployeeName("Sanket");
		assertEquals("Sanket", emp.getEmployeeName());
	}

	@Test
	void testgetEmployeeEmail() {
		emp.setEmployeeEmail("sanket@fujitsu.com");
		assertEquals("sanket@fujitsu.com", emp.getEmployeeEmail());

	}

	@Test
	void testsetEmployeeEmail() {
		emp.setEmployeeEmail("sanket@fujitsu.com");
		assertEquals("sanket@fujitsu.com", emp.getEmployeeEmail());
	}

	@Test
	void testgetManagerId() {
		emp.setManagerId(29981);
		assertEquals(29981, emp.getManagerId());

	}

	@Test
	void testsetManagerId() {
		emp.setManagerId(29981);
		assertEquals(29981, emp.getManagerId());
	}

	@Test
	void testgetFjLevel() {
		emp.setFjLevel("FJ07");
		assertEquals("FJ07", emp.getFjLevel());

	}

	@Test
	void testsetFjLevel() {
		emp.setFjLevel("FJ07");
		assertEquals("FJ07", emp.getFjLevel());

	}

	@Test
	void testgetJlptLevel() {
		emp.setJlptLevel("N5");
		assertEquals("N5", emp.getJlptLevel());

	}

	@Test
	void testsetJlptLevel() {
		emp.setJlptLevel("N5");
		assertEquals("N5", emp.getJlptLevel());
	}

	@Test
	void testgetPrimarySkill() {
		emp.setPrimarySkill("Java");
		assertEquals("Java", emp.getPrimarySkill());

	}

	@Test
	void testsetPrimarySkill() {
		emp.setPrimarySkill("Java");
		assertEquals("Java", emp.getPrimarySkill());
	}

	@Test
	void testgetSecondarySkill() {
		emp.setSecondarySkill("Japanese");
		assertEquals("Japanese", emp.getSecondarySkill());
	}

	@Test
	void testsetSecondarySkill() {
		emp.setSecondarySkill("Japanese");
		assertEquals("Japanese", emp.getSecondarySkill());
	}

	@Test
	void testgetDoj() {
		emp.setDoj(null);
		assertEquals(null, emp.getDoj());

	}

	@Test
	public void testsetDoj() {
		emp.setDoj(null);
		assertEquals(null, emp.getDoj());
	}

	@Test
	void testgetRole() {
		emp.setRole("Manager");
		assertEquals("Manager", emp.getRole());
	}

	@Test
	void testsetRole() {
		emp.setRole("Manager");
		assertEquals("Manager", emp.getRole());
	}
}