package com.fuji.erms.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import com.fuji.erms.constants.RequestManagementConstants;
import com.fuji.erms.dao.EmployeeMasterDao;
import com.fuji.erms.dto.RequestTransactionDto;
import com.fuji.erms.model.EmployeeMaster;
import com.fuji.erms.model.RequestTransaction;
import com.fuji.erms.service.EmployeeMasterService;
import com.fuji.erms.service.RequestTransactionService;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class EmployeeMasterControllerTest {

	@InjectMocks
	EmployeeMasterController employeeMasterController;
	@InjectMocks
	RequestTransactionController requestTransactionController;

	@Mock
	EmployeeMasterService employeeMasterService;

	@Mock
	RequestTransactionService requestTransactionService;

	@Mock
	private EmployeeMasterDao employeeMasterDao;
	ModelMapper modelMapper = new ModelMapper();
	private static RequestTransactionDto req;
	private static RequestTransaction requestTransaction;
	private static EmployeeMaster employeeMasterRec2;
	private static EmployeeMaster employeeMaster;
	private static List<EmployeeMaster> empList;

	void loadData() throws ParseException {

		req = new RequestTransactionDto(1, 33320, "1", "Subhash", "1", 29911,
				null, 33320, null, "hjhj", "hrushal");

		requestTransaction = new RequestTransaction(1, 123, "2", "1", 12345,
				null, 567, null, "567", null);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
		formatter.setLenient(false);
		Date date = formatter.parse("2020-01-09");

		employeeMaster = new EmployeeMaster(123, "Hrushal",
				"Hrushal.Gade@gmail.com", 12345, "1", "2", "java", "SQL", date,
				"1", null, null);
		employeeMasterRec2 = new EmployeeMaster(567, "Vivek", "Vivek@gmail.com",
				29961, "1", "2", "java", "SQL", date, "1", null, null);
		List<EmployeeMaster> listGetAll = new ArrayList<>(
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
	void testGetAll() throws ParseException {

		when(employeeMasterService.getAll()).thenReturn(empList);
		assertNotNull(empList);
		assertEquals(2, employeeMasterController.getAll().getBody().size());

	}

	@Test
	void testGetAllnull() {
		List<EmployeeMaster> newEmpMaster = new ArrayList<>();

		when(employeeMasterService.getAll()).thenReturn(newEmpMaster);
		assertNotNull(newEmpMaster);
		assertEquals(204,
				employeeMasterController.getAll().getStatusCodeValue());

	}

	@Test
	void testGetAllReturnException() throws ParseException {

		when(employeeMasterService.getAll()).thenReturn(null);
		assertNull(null);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
				employeeMasterController.getAll().getStatusCode());

	}

	@Test
	void testDeleteEmployeeByEmployeeId() {

		Optional<EmployeeMaster> employeeMaster1 = Optional.of(employeeMaster);

		when(employeeMasterService.getEmployeeById(12345))
				.thenReturn(employeeMaster1);
		assertNotNull(employeeMaster1);
		assertEquals(HttpStatus.OK, employeeMasterController
				.deleteEmployeeByEmployeeId(12345).getStatusCode());
	}

	@Test
	void DeleteEmployeeByEmployeeIdIsNull() {

		when(employeeMasterService.getEmployeeById(12345)).thenReturn(null);
		assertEquals(HttpStatus.NO_CONTENT, employeeMasterController
				.deleteEmployeeByEmployeeId(null).getStatusCode());
	}

	@Test
	void DeleteEmployeeByEmployeeIdException() {
		when(employeeMasterService.getEmployeeById(12345)).thenReturn(null);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, employeeMasterController
				.deleteEmployeeByEmployeeId(12345).getStatusCode());
	}

	@Test
	void testGetEmployeeById() {

		Optional<EmployeeMaster> employeeMaster1 = Optional.of(employeeMaster);

		when(employeeMasterService.getEmployeeById(Mockito.any()))
				.thenReturn(employeeMaster1);
		assertNotNull(employeeMaster1);
		assertEquals(employeeMaster1.get().getEmployeeName(),
				employeeMasterController.getEmployeeById("12345").getBody()
						.getEmployeeName());
	}

	@Test
	void testGetEmployeeByIdReturnNull() {

		when(employeeMasterService.getEmployeeById(null)).thenReturn(null);
		assertEquals(HttpStatus.NO_CONTENT, employeeMasterController
				.getEmployeeById("12345").getStatusCode());

	}

	@Test
	void testGetEmployeeByIdReturnEmpty() {

		when(employeeMasterService.getEmployeeById(Mockito.any()))
				.thenReturn(null);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, employeeMasterController
				.getEmployeeById("1234").getStatusCode());

	}

	@Test
	void testUpdateEmployeeInfo() {

		Optional<RequestTransaction> requestTransaction1 = Optional
				.of(requestTransaction);

		RequestTransaction requestobj = requestTransaction1.get();
		requestobj.setRequestStatus(RequestManagementConstants.APPROVE);
		requestobj.setUpdateUser(requestTransaction1.get().getUpdateUser());
		requestobj.setUpdateDateTime(
				requestTransaction1.get().getUpdateDateTime());

		Optional<EmployeeMaster> employeeMaster1 = Optional
				.of(employeeMasterRec2);
		when(requestTransactionService.getRequestById(Mockito.anyInt()))
				.thenReturn(requestTransaction1);
		when(requestTransactionService.updateStatus(Mockito.any()))
				.thenReturn(requestTransaction);
		when(employeeMasterService.getEmployeeById(Mockito.anyInt()))
				.thenReturn(employeeMaster1);
		when(employeeMasterService.getEmployeeById(Mockito.anyInt()))
				.thenReturn(employeeMaster1);
		when(employeeMasterService.updateEmployee(Mockito.any()))
				.thenReturn(employeeMasterRec2);
		assertEquals(567, employeeMasterController.updateEmployeeInfo(req, 1)
				.getBody().getEmployeeId());
	}

	@Test
	void testUpdateEmployeeInfoFJLevelChange() {

		Optional<RequestTransaction> requestTransaction1 = Optional
				.of(requestTransaction);
		RequestTransaction requestobj = requestTransaction1.get();
		requestobj.setRequestStatus(RequestManagementConstants.APPROVE);
		requestobj.setUpdateUser(requestTransaction1.get().getUpdateUser());
		requestobj.setUpdateDateTime(
				requestTransaction1.get().getUpdateDateTime());

		Optional<EmployeeMaster> employeeMaster1 = Optional
				.of(employeeMasterRec2);
		when(requestTransactionService.getRequestById(Mockito.anyInt()))
				.thenReturn(requestTransaction1);
		when(requestTransactionService.updateStatus(Mockito.any()))
				.thenReturn(requestTransaction);
		when(employeeMasterService.getEmployeeById(Mockito.anyInt()))
				.thenReturn(employeeMaster1);
		when(employeeMasterService.getEmployeeById(Mockito.anyInt()))
				.thenReturn(employeeMaster1);
		when(employeeMasterService.updateEmployee(Mockito.any()))
				.thenReturn(employeeMasterRec2);
		assertEquals(567, employeeMasterController.updateEmployeeInfo(req, 1)
				.getBody().getEmployeeId());
	}

	@Test
	void testUpdateEmployeeInfoJLPTLevelChange() {

		Optional<RequestTransaction> requestTransaction1 = Optional
				.of(requestTransaction);

		RequestTransaction requestobj = requestTransaction1.get();
		requestobj.setRequestStatus(RequestManagementConstants.APPROVE);
		requestobj.setUpdateUser(requestTransaction1.get().getUpdateUser());
		requestobj.setUpdateDateTime(
				requestTransaction1.get().getUpdateDateTime());

		Optional<EmployeeMaster> employeeMaster1 = Optional
				.of(employeeMasterRec2);
		when(requestTransactionService.getRequestById(Mockito.anyInt()))
				.thenReturn(requestTransaction1);
		when(requestTransactionService.updateStatus(Mockito.any()))
				.thenReturn(requestTransaction);
		when(employeeMasterService.getEmployeeById(Mockito.anyInt()))
				.thenReturn(employeeMaster1);
		when(employeeMasterService.getEmployeeById(Mockito.anyInt()))
				.thenReturn(employeeMaster1);
		when(employeeMasterService.updateEmployee(Mockito.any()))
				.thenReturn(employeeMasterRec2);
		assertEquals(567, employeeMasterController.updateEmployeeInfo(req, 1)
				.getBody().getEmployeeId());
	}

	@Test
	void testUpdateEmployeeInfoIsNull() {

		when(requestTransactionService.getRequestById(null)).thenReturn(null);

		assertEquals(HttpStatus.NO_CONTENT, employeeMasterController
				.updateEmployeeInfo(req, 1).getStatusCode());

	}

	@Test
	void testUpdateEmployeeInfoError() {

		when(requestTransactionService.getRequestById(Mockito.anyInt()))
				.thenReturn(null);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, employeeMasterController
				.updateEmployeeInfo(req, 1).getStatusCode());
	}

	@Test
	void testUpdateEmployeeStatusAsReject() {

		Optional<RequestTransaction> requestTransaction1 = Optional
				.of(requestTransaction);

		RequestTransaction requestobj = requestTransaction1.get();
		requestobj.setRequestStatus(RequestManagementConstants.REJECT);
		requestobj.setUpdateUser(requestTransaction1.get().getCreateUser());
		requestobj.setUpdateDateTime(
				requestTransaction1.get().getUpdateDateTime());

		when(requestTransactionService.getRequestById(1))
				.thenReturn(requestTransaction1);
		when(requestTransactionService.updateStatus(requestobj))
				.thenReturn(requestTransaction);
		assertEquals(1, employeeMasterController
				.updateEmployeeStatusAsReject(req, 1).getBody().getRequestId());
		assertEquals(requestobj.getUpdateUser(),
				employeeMasterController.updateEmployeeStatusAsReject(req, 1)
						.getBody().getUpdateUser());
	}

	@Test
	void testUpdateEmployeeStatusAsRejectIsNull() {

		Optional<RequestTransaction> requestTransaction1 = Optional
				.of(requestTransaction);

		when(requestTransactionService.getRequestById(null))
				.thenReturn(requestTransaction1);

		assertNotNull(requestTransaction1);
		assertEquals(204, employeeMasterController
				.updateEmployeeStatusAsReject(req, 1).getStatusCodeValue());

	}

	@Test
	void testGetAllManagers() {

		when(employeeMasterService.getManagers()).thenReturn(empList);
		assertNotNull(empList);
		assertEquals(empList.get(0).getEmployeeId(), employeeMasterController
				.getAllManagers().getBody().get(0).getEmployeeId());

	}

	@Test
	void testGet() {
		List<EmployeeMaster> newEmpMaster = new ArrayList<>();

		when(employeeMasterService.getManagers()).thenReturn(newEmpMaster);
		assertNotNull(newEmpMaster);
		assertEquals(204,
				employeeMasterController.getAllManagers().getStatusCodeValue());

	}

	@Test
	void testGetAllManagersException() {

		when(employeeMasterService.getManagers()).thenReturn(null);
		assertNull(employeeMasterController.getAllManagers().getBody());

	}

}