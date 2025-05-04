package com.fuji.erms.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fuji.erms.dao.EmployeeMasterDao;
import com.fuji.erms.dto.EmployeeMasterDto;
import com.fuji.erms.exceptions.EmployeeMasterException;
import com.fuji.erms.exceptions.UserMasterException;
import com.fuji.erms.model.EmployeeMaster;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(MockitoJUnitRunner.class)
class EmployeeMasterServiceImplTest {
	@InjectMocks
	private EmployeeMasterServiceImpl employeeMasterServiceImpl;
	@Mock
	private EmployeeMasterDao employeeMasterDao;
	ModelMapper modelMapper = new ModelMapper();
	private static List<EmployeeMaster> empList;

	void loadData() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter.setLenient(false);
		Date date = formatter.parse("30/03/2015");
		EmployeeMasterDto employeeMaster = new EmployeeMasterDto("Subhash",
				"Subhash.Chougale@gmail.com", 12345, "1", "2", "java", "SQL", date,
				"1");
		EmployeeMasterDto employeeMasterRec2 = new EmployeeMasterDto("Vivek",
				"Vivek@gmail.com", 29961, "1", "2", "java", "SQL", date, "1");
		List<EmployeeMasterDto> listGetAll = new ArrayList<>(
				Arrays.asList(employeeMaster, employeeMasterRec2));
		empList = listGetAll.stream()
				.map(element -> modelMapper.map(element, EmployeeMaster.class))
				.collect(Collectors.toList());
	}

	@BeforeEach
	public void setup() throws ParseException {
		MockitoAnnotations.openMocks(this);
		loadData();
	}

	@Test
	void testGetAll() {
		when(employeeMasterDao.findAll()).thenReturn(empList);
		assertEquals(empList.get(0), employeeMasterServiceImpl.getAll().get(0));
	}

	@Test
	void testGetAllIException() {
		when(employeeMasterDao.findAll())
				.thenThrow(EmployeeMasterException.class);
		assertThrows(EmployeeMasterException.class, () -> {
			employeeMasterServiceImpl.getAll();

		});

	}

	@Test
	void testUpdateEmployee() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter.setLenient(false);
		Date date = formatter.parse("31/03/2015");
		EmployeeMaster employeeMaster = new EmployeeMaster(1234, "Apurva",
				"ApurvaSathe@gmail.com", 12345, "1", "2", "java", "SQL", date,
				"1", null, null);
		employeeMaster.setEmployeeName("Vivek");
		when(employeeMasterDao.save(employeeMaster)).thenReturn(employeeMaster);
		assertNotNull(employeeMaster);
		assertEquals("Vivek", employeeMasterServiceImpl
				.updateEmployee(employeeMaster).getEmployeeName());
	}

	@Test
	void testUpdateEmployeeException() {
		when(employeeMasterDao.save(Mockito.any()))
				.thenThrow(EmployeeMasterException.class);
		assertThrows(EmployeeMasterException.class, () -> {
			employeeMasterServiceImpl.updateEmployee(null);

		});

	}

	@Test
	void testGetEmployeeById() {
		Optional<EmployeeMaster> empMaster = Optional.of(empList.get(0));
		when(employeeMasterDao.findById(Mockito.anyInt()))
				.thenReturn(empMaster);
		assertNotNull(empMaster);
		assertEquals(12345, employeeMasterServiceImpl.getEmployeeById(12345)
				.get().getEmployeeId());
	}

	@Test
	void testGetEmployeeByIdException() {
		when(employeeMasterDao.findById(Mockito.any()))
				.thenThrow(EmployeeMasterException.class);
		assertThrows(EmployeeMasterException.class, () -> {
			employeeMasterServiceImpl.getEmployeeById(null);

		});

	}

	@Test
	void testDeleteEmployeeByEmployeeId() {

		Optional<EmployeeMaster> reqTransaction = Optional.of(empList.get(0));
		when(employeeMasterDao.findById(Mockito.anyInt()))
				.thenReturn(reqTransaction);
		employeeMasterServiceImpl.deleteEmployeeByEmployeeId(
				reqTransaction.get().getEmployeeId());
		verify(employeeMasterDao, times(1)).deleteEmployeeByEmployeeId(
				reqTransaction.get().getEmployeeId());

	}

	@Test
	void testGetManagers() {
		when(employeeMasterDao.getManagers()).thenReturn(empList);

		assertEquals(empList.get(0),
				employeeMasterServiceImpl.getManagers().get(0));
	}

	@Test
	void testGetManagersException() {
		when(employeeMasterDao.getManagers()).thenReturn(empList);

		when(employeeMasterDao.getManagers())
				.thenThrow(UserMasterException.class);
		assertThrows(UserMasterException.class, () -> {
			employeeMasterServiceImpl.getManagers();

		});
	}

}