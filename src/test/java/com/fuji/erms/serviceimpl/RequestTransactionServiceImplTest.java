package com.fuji.erms.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
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

import com.fuji.erms.constants.RequestManagementConstants;
import com.fuji.erms.dao.RequestTransactionDao;
import com.fuji.erms.daoimpl.RequestTransactionDaoImpl;
import com.fuji.erms.dto.RequestTransactionDto;
import com.fuji.erms.exceptions.UserMasterException;
import com.fuji.erms.model.RequestTransaction;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(MockitoJUnitRunner.class)
class RequestTransactionServiceImplTest {
	@InjectMocks
	private RequestTransactionServiceImpl RequestTransactionServiceImpl;
	@Mock
	private RequestTransactionDao requestTransactionDao;

	@Mock
	private RequestTransactionDaoImpl requestTransactionDaoImpl;
	ModelMapper modelMapper = new ModelMapper();
	private static List<RequestTransaction> requestList;
	private static List<RequestTransactionDto> listGetAll;

	void loadData() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
		formatter.setLenient(false);
		Date date = formatter.parse("2020-01-09");
		RequestTransactionDto requestTransaction = new RequestTransactionDto(1,
				27413, "1", "Apurva", "0", 29911, date, 27841, date, "hjhj",
				"hrushal");
		RequestTransactionDto requestTransaction2 = new RequestTransactionDto(1,
				27413, "1", "Apurva", "0", 29911, date, 27841, date, "hjhj",
				"hrushal");
		listGetAll = new ArrayList<>(
				Arrays.asList(requestTransaction, requestTransaction2));
		requestList = listGetAll.stream().map(
				element -> modelMapper.map(element, RequestTransaction.class))
				.collect(Collectors.toList());
	}

	@BeforeEach
	public void setup() throws ParseException {
		MockitoAnnotations.openMocks(this);
		loadData();
	}

	@Test
	void testCreateRequest() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
		formatter.setLenient(false);
		Date date = formatter.parse("2020-01-09");
		RequestTransactionDto requestTransaction = new RequestTransactionDto(1,
				27413, "1", "Apurva", "0", 29911, date, 27841, date, "hjhj",
				"hrushal");
		RequestTransaction requestTransaction1 = modelMapper
				.map(requestTransaction, RequestTransaction.class);
		when(requestTransactionDao.save(requestTransaction1))
				.thenReturn(requestTransaction1);
		assertNotNull(requestTransaction);
		assertEquals(27413, RequestTransactionServiceImpl
				.createRequest(requestTransaction1).getEmployeeId());
	}

	@Test
	void testCreateRequestException() {
		when(requestTransactionDao.save(Mockito.any()))
				.thenThrow(UserMasterException.class);
		assertThrows(UserMasterException.class, () -> {
			RequestTransactionServiceImpl.createRequest(null);

		});

	}

	@Test
	void testGetAll() {
		when(requestTransactionDao.findAll()).thenReturn(requestList);
		assertEquals(requestList.get(0),
				RequestTransactionServiceImpl.getAll().get(0));
	}

	@Test
	void testGetAllIException() {
		when(requestTransactionDao.findAll())
				.thenThrow(UserMasterException.class);
		assertThrows(UserMasterException.class, () -> {
			RequestTransactionServiceImpl.getAll();

		});

	}

	@Test
	void testGetRequestById() {
		Optional<RequestTransaction> reqTransaction = Optional
				.of(requestList.get(0));
		when(requestTransactionDao.findById(Mockito.anyInt()))
				.thenReturn(reqTransaction);
		assertNotNull(reqTransaction);
		assertEquals(1, RequestTransactionServiceImpl.getRequestById(1).get()
				.getRequestId());
	}

	@Test
	void testGetRequestByIdException() {
		when(requestTransactionDao.findById(Mockito.any()))
				.thenThrow(UserMasterException.class);
		assertThrows(UserMasterException.class, () -> {
			RequestTransactionServiceImpl.getRequestById(null);

		});

	}

	@Test
	void testDeleteRequestById() {
		Optional<RequestTransaction> reqTransaction = Optional
				.of(requestList.get(0));
		when(requestTransactionDao.findById(Mockito.anyInt()))
				.thenReturn(reqTransaction);
		RequestTransactionServiceImpl
				.deleteRequestById(reqTransaction.get().getRequestId());
		assertNotNull(reqTransaction);
		assertEquals(1, RequestTransactionServiceImpl.getRequestById(1).get()
				.getRequestId());
	}

	@Test
	void testUpdateStatus() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
		formatter.setLenient(false);
		Date date = formatter.parse("2020-01-09");
		RequestTransactionDto requestTransaction = new RequestTransactionDto(1,
				27413, "1", "Apurva", "0", 29911, date, 27841, date, "hjhj",
				"hrushal");
		requestTransaction.setRequestStatus(RequestManagementConstants.APPROVE);
		RequestTransaction requestTransaction1 = modelMapper
				.map(requestTransaction, RequestTransaction.class);
		when(requestTransactionDao.save(requestTransaction1))
				.thenReturn(requestTransaction1);
		assertNotNull(requestTransaction);
		assertEquals(RequestManagementConstants.APPROVE,
				RequestTransactionServiceImpl.updateStatus(requestTransaction1)
						.getRequestStatus());
	}

	@Test
	void testUpdateStatusException() {
		when(requestTransactionDao.save(Mockito.any()))
				.thenThrow(UserMasterException.class);
		assertThrows(UserMasterException.class, () -> {
			RequestTransactionServiceImpl.updateStatus(null);

		});

	}

	@Test
	void testFilterRequestsRequestId() {
		when(requestTransactionDaoImpl
				.getAllRequestByFilter(Mockito.anyString()))
						.thenReturn(listGetAll);
		assertEquals(listGetAll, RequestTransactionServiceImpl.filterRequests(1,
				null, null, null, null, null, null, null));
	}

	@Test
	void testFilterRequestsRequestType() {
		when(requestTransactionDaoImpl
				.getAllRequestByFilter(Mockito.anyString()))
						.thenReturn(listGetAll);
		assertEquals(listGetAll, RequestTransactionServiceImpl
				.filterRequests(null, "1", null, null, null, null, null, null));
	}

	@Test
	void testFilterRequestsRequestStatus() {
		when(requestTransactionDaoImpl
				.getAllRequestByFilter(Mockito.anyString()))
						.thenReturn(listGetAll);
		assertEquals(listGetAll, RequestTransactionServiceImpl
				.filterRequests(null, null, "1", null, null, null, null, null));
	}

	@Test
	void testFilterRequestsEmployeeId() {
		when(requestTransactionDaoImpl
				.getAllRequestByFilter(Mockito.anyString()))
						.thenReturn(listGetAll);
		assertEquals(listGetAll, RequestTransactionServiceImpl
				.filterRequests(null, null, null, 1, null, null, null, null));
	}

	@Test
	void testFilterRequestsEmployeeName() {
		when(requestTransactionDaoImpl
				.getAllRequestByFilter(Mockito.anyString()))
						.thenReturn(listGetAll);
		assertEquals(listGetAll, RequestTransactionServiceImpl.filterRequests(
				null, null, null, null, "Hrushal", null, null, null));
	}

	@Test
	void testFilterRequestsCreateDateTime() {
		when(requestTransactionDaoImpl
				.getAllRequestByFilter(Mockito.anyString()))
						.thenReturn(listGetAll);
		assertEquals(listGetAll, RequestTransactionServiceImpl.filterRequests(
				null, null, null, null, null, "12/09/1998", null, null));
	}

	@Test
	void testFilterRequestsRemark() {
		when(requestTransactionDaoImpl
				.getAllRequestByFilter(Mockito.anyString()))
						.thenReturn(listGetAll);
		assertEquals(listGetAll, RequestTransactionServiceImpl.filterRequests(
				null, null, null, null, null, null, "New Manager", null));
	}

	@Test
	void testFilterRequestsRequestBy() {
		when(requestTransactionDaoImpl
				.getAllRequestByFilter(Mockito.anyString()))
						.thenReturn(listGetAll);
		assertEquals(listGetAll, RequestTransactionServiceImpl.filterRequests(
				null, null, null, null, null, null, null, "Hrushal"));
	}

	@Test
	void testFilterRequestsReturnsAll() {
		when(requestTransactionDaoImpl
				.getAllRequestByFilter(Mockito.anyString()))
						.thenReturn(listGetAll);
		assertEquals(listGetAll, RequestTransactionServiceImpl.filterRequests(
				null, null, null, null, null, null, null, null));
	}
}