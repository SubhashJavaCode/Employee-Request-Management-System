package com.fuji.erms.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

class EmployeeMasterDtoTest {
	EmployeeMasterDto empdto = new EmployeeMasterDto();

	@Test
	void testgetEmployeeName() {
		empdto.setEmployeeName("Apurva");
		assertEquals("Apurva", empdto.getEmployeeName());

	}

	@Test
	void testsetEmployeeName() {
		empdto.setEmployeeName("Apurva");
		assertEquals("Apurva", empdto.getEmployeeName());
	}

	@Test
	void testgetEmployeeEmail() {
		empdto.setEmployeeEmail("apurva@fujitsu,com");
		assertEquals("apurva@fujitsu,com", empdto.getEmployeeEmail());

	}

	@Test
	void setEmployeeEmail() {
		empdto.setEmployeeEmail("apurva@fujitsu,com");
		assertEquals("apurva@fujitsu,com", empdto.getEmployeeEmail());
	}

	@Test
	void getManagerId() {
		empdto.setManagerId(1);
		assertEquals(1, empdto.getManagerId());
	}

	@Test
	void setManagerId() {
		empdto.setManagerId(1);
		assertEquals(1, empdto.getManagerId());
	}

	@Test
	void getFjLevel() {
		empdto.setFjLevel("FJ08");
		assertEquals("FJ08", empdto.getFjLevel());

	}

	@Test
	void setFjLevel() {
		empdto.setFjLevel("FJ08");
		assertEquals("FJ08", empdto.getFjLevel());
	}

	@Test
	void getJlptLevel() {
		empdto.setJlptLevel("N3");
		assertEquals("N3", empdto.getJlptLevel());
	}

	@Test
	void setJlptLevel() {
		empdto.setJlptLevel("N3");
		assertEquals("N3", empdto.getJlptLevel());
	}

	@Test
	void getPrimarySkill() {
		empdto.setPrimarySkill("Java");
		assertEquals("Java", empdto.getPrimarySkill());
	}

	@Test
	void setPrimarySkill() {
		empdto.setPrimarySkill("Java");
		assertEquals("Java", empdto.getPrimarySkill());
	}

	@Test
	void testgetSecondarySkill() {
		empdto.setSecondarySkill("Japanese");
		assertEquals("Japanese", empdto.getSecondarySkill());
	}

	@Test
	void setSecondarySkill() {
		empdto.setSecondarySkill("Japanese");
		assertEquals("Japanese", empdto.getSecondarySkill());
	}

	@Test
	void getDoj() throws ParseException {

		empdto.setDoj(null);
		assertEquals(null, empdto.getDoj());
	}

	@Test
	void setDoj() {
		empdto.setDoj(null);
		assertEquals(null, empdto.getDoj());
	}

	@Test
	void getRole() {
		empdto.setRole("Manager");
		assertEquals("Manager", empdto.getRole());
	}

	@Test
	void setRole() {
		empdto.setRole("Manager");
		assertEquals("Manager", empdto.getRole());
	}

	@Test
	void testNoArgsConstructor() {
		assertNotNull(empdto);
	}

	@Test
	void testAllArgsConstructor() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter.setLenient(false);
		Date date = formatter.parse("02/01/2023");
		EmployeeMasterDto empdto = new EmployeeMasterDto("Apurva",
				"apurva@Fujitsu.com", 27888, "FJ08", "N3", "Java", "Sql", date,
				"LM");
		assertNotNull(empdto);
		assertEquals("Apurva", empdto.getEmployeeName());
		assertEquals("apurva@Fujitsu.com", empdto.getEmployeeEmail());
		assertEquals(27888, empdto.getManagerId());
		assertEquals("FJ08", empdto.getFjLevel());
		assertEquals("N3", empdto.getJlptLevel());
		assertEquals("Java", empdto.getPrimarySkill());
		assertEquals("Sql", empdto.getSecondarySkill());
		assertEquals(date, empdto.getDoj());
		assertEquals("LM", empdto.getRole());

	}

}