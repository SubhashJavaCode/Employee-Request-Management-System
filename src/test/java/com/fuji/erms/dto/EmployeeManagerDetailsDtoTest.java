package com.fuji.erms.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

class EmployeeManagerDetailsDtoTest {

	EmployeeManagerDetailsDto emddto = new EmployeeManagerDetailsDto();

	@Test
	void testgetEmployeeName() {
		emddto.setEmployeeName("Kavita");
		assertEquals("Kavita", emddto.getEmployeeName());

	}

	@Test
	void testsetEmployeeName() {
		emddto.setEmployeeName("Kavita");
		assertEquals("Kavita", emddto.getEmployeeName());
	}

	@Test
	void testgetManagerId() {
		emddto.setManagerId(29981);
		assertEquals(29981, emddto.getManagerId());

	}

	@Test
	void testsetManagerId() {
		emddto.setManagerId(29981);
		assertEquals(29981, emddto.getManagerId());
	}

	@Test
	void testgetFjLevel() {
		emddto.setFjLevel("FJ08");
		assertEquals("FJ08", emddto.getFjLevel());

	}

	@Test
	void testsetFjLevel() {
		emddto.setFjLevel("FJ08");
		assertEquals("FJ08", emddto.getFjLevel());
	}

	@Test
	void testgetJlptLevel() {
		emddto.setJlptLevel("N4");
		assertEquals("N4", emddto.getJlptLevel());
	}

	@Test
	void testsetJlptLevel() {
		emddto.setJlptLevel("N4");
		assertEquals("N4", emddto.getJlptLevel());
	}

	@Test
	void testgetManagerName() {
		emddto.setManagerName("Naina");
		assertEquals("Naina", emddto.getManagerName());
	}

	@Test
	void testsetManagerName() {
		emddto.setManagerName("Naina");
		assertEquals("Naina", emddto.getManagerName());
	}

	@Test
	void testAllArgsConstructor() {
		EmployeeManagerDetailsDto empmandto = new EmployeeManagerDetailsDto(
				"Kavita", 29981, "FJ08", "N4", "Naina");
		assertNotNull(empmandto);
		assertEquals("Kavita", empmandto.getEmployeeName());
		assertEquals(29981, empmandto.getManagerId());
		assertEquals("FJ08", empmandto.getFjLevel());
		assertEquals("N4", empmandto.getJlptLevel());
		assertEquals("Naina", empmandto.getManagerName());

	}

}
