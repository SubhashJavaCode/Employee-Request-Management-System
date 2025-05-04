package com.fuji.erms.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.fuji.erms.model.EmployeeMaster;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(MockitoJUnitRunner.class)
class EmployeeMasterDaoTest {
	@Mock
	EmployeeMasterDao employeeMasterDao;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(employeeMasterDao);
	}

	@Test
	void testSaveEmployeeMaster() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter.setLenient(false);
		Date date = formatter.parse("30/01/2023");
		EmployeeMaster employeeMaster = new EmployeeMaster(27841, "Apurva",
				"apurva@Fujitsu.com", 27888, "1", "2", "Java", "Sql", date, "1",
				null, null);
		when(employeeMasterDao.save(Mockito.any())).thenReturn(employeeMaster);
		assertNotNull(employeeMaster);
		assertEquals(employeeMaster.getEmployeeName(),
				employeeMasterDao.save(employeeMaster).getEmployeeName());
	}

	@Test
	void testGetEmployeeByEmployeeName() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter.setLenient(false);
		Date date = formatter.parse("02/01/2023");
		EmployeeMaster employeeMaster = new EmployeeMaster(27841, "Apurva",
				"apurva@Fujitsu.com", 27888, "1", "2", "Java", "Sql", date, "1",
				null, null);
		List<EmployeeMaster> empList = new ArrayList<>();
		empList.add(employeeMaster);
		
		when(employeeMasterDao.getEmployeeByEmployeeName(Mockito.any()))
				.thenReturn(empList);
		assertNotNull(employeeMaster);
		assertEquals(empList.get(0).getEmployeeName(), employeeMasterDao
				.getEmployeeByEmployeeName("Apurva").get(0).getEmployeeName());

	}

	@Test
	void testGetManagerByemployeeId() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter.setLenient(false);
		Date date = formatter.parse("02/01/2023");
		EmployeeMaster employeeMaster = new EmployeeMaster(27841, "Apurva",
				"apurva@Fujitsu.com", 27841, "1", "2", "Java", "Sql", date, "1",
				null, null);
		Optional<EmployeeMaster> employeeMaster1 = Optional.of(employeeMaster);

		when(employeeMasterDao.getManagerByemployeeId(Mockito.any()))
				.thenReturn(employeeMaster1);
		assertNotNull(employeeMaster);
		assertEquals(employeeMaster1.get().getEmployeeId(), employeeMasterDao
				.getManagerByemployeeId(27841).get().getManagerId());

	}
	
	@Test
	void testGetManagers() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter.setLenient(false);
		Date date = formatter.parse("02/01/2023");
		EmployeeMaster employeeMaster = new EmployeeMaster(27841, "Apurva",
				"apurva@Fujitsu.com", 27888, "1", "2", "Java", "Sql", date, "1",
				null, null);
		List<EmployeeMaster> empList = new ArrayList<>();
		empList.add(employeeMaster);
		when(employeeMasterDao.getManagers()).thenReturn(empList);
		assertNotNull(employeeMaster);
		assertEquals(empList, employeeMasterDao.getManagers());

	}
}