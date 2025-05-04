package com.fuji.erms.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

import com.fuji.erms.dto.RequestTransactionDto;
import com.fuji.erms.exceptions.RequestTransactionException;
import com.fuji.erms.model.RequestTransaction;
import com.fuji.erms.service.RequestTransactionService;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class RequestTransactionControllerTest {

	@InjectMocks
	RequestTransactionController requestTransactionController;
	@Mock
	RequestTransactionService requestTransactionService;
	ModelMapper modelMapper = new ModelMapper();
	private static List<RequestTransaction> requestList;
	private static List<RequestTransactionDto> listGetAll;
	private static RequestTransactionDto requestTransaction;
	private static RequestTransactionDto requestTransaction2;

	@BeforeEach
	public void setup() throws ParseException {
		MockitoAnnotations.openMocks(this);
		loadData();
	}

	void loadData() throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
		formatter.setLenient(false);
		Date date = formatter.parse("2020-01-09");
		requestTransaction = new RequestTransactionDto(1, 27413, "1", "Subhash",
				"0", 29911, date, 27841, date, "hjhj", "hrushal");
		requestTransaction2 = new RequestTransactionDto(1, 27413, "1", "Subhash",
				"0", 29911, date, 27841, date, "hjhj", "hrushal");
		listGetAll = new ArrayList<>(
				Arrays.asList(requestTransaction, requestTransaction2));
		requestList = listGetAll.stream().map(
				element -> modelMapper.map(element, RequestTransaction.class))
				.collect(Collectors.toList());

	}

	@Test
	void testGetAll() {

		when(requestTransactionService.getAll()).thenReturn(requestList);
		assertNotNull(requestList);
		assertEquals(2, requestTransactionController.getAll().getBody().size());

	}

	@Test
	void testGetAllReturnsNull() {

		List<RequestTransaction> newRequestTransactions = new ArrayList<>();
		when(requestTransactionService.getAll())
				.thenReturn(newRequestTransactions);
		assertNotNull(newRequestTransactions);
		assertEquals(HttpStatus.OK,
				requestTransactionController.getAll().getStatusCode());

	}

	@Test
	void testGetAllReturnsException() {

		when(requestTransactionService.getAll()).thenReturn(null);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
				requestTransactionController.getAll().getStatusCode());

	}

	@Test
	void testUpdateUser() throws ParseException {

		RequestTransaction requestTransaction2 = modelMapper
				.map(requestTransaction, RequestTransaction.class);

		when(requestTransactionService.getRequestById(Mockito.anyInt()))
				.thenReturn(Optional.of(requestTransaction2));
		when(requestTransactionService.updateStatus(Mockito.any()))
				.thenReturn(requestTransaction2);
		assertNotNull(requestTransaction2);
		assertEquals(HttpStatus.OK, requestTransactionController
				.updateUser(requestTransaction, null).getStatusCode());

	}

	@Test
	void testUpdateUserReturnsNull() throws ParseException {

		RequestTransaction requestTransaction2 = modelMapper
				.map(requestTransaction, RequestTransaction.class);

		when(requestTransactionService.getRequestById(null))
				.thenReturn(Optional.of(requestTransaction2));
		when(requestTransactionService.updateStatus(null))
				.thenReturn(requestTransaction2);
		assertNotNull(requestTransaction2);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
				requestTransactionController
						.updateUser(requestTransaction, null).getStatusCode());

	}

	@Test
	void testUpdateUserError() throws ParseException {

		RequestTransaction requestTransaction2 = modelMapper
				.map(requestTransaction, RequestTransaction.class);
		try {
			when(requestTransactionService.getRequestById(null))
					.thenThrow(new RequestTransactionException(""));
			when(requestTransactionService.updateStatus(null))
					.thenReturn(requestTransaction2);
			assertNotNull(requestTransaction2);
			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
					requestTransactionController
							.updateUser(requestTransaction, null)
							.getStatusCode());

		} catch (Exception e) {
			assertTrue(e instanceof RequestTransactionException);
		}
	}

	@Test
	void testGetRequestById() throws ParseException {

		RequestTransaction requestTransaction2 = modelMapper
				.map(requestTransaction, RequestTransaction.class);

		when(requestTransactionService.getRequestById(Mockito.anyInt()))
				.thenReturn(Optional.of(requestTransaction2));
		assertNotNull(requestTransaction2);
		assertEquals(HttpStatus.OK,
				requestTransactionController.getRequestById(1).getStatusCode());

	}

	@Test
	void testGetRequestByIdReurnsNull() {

		when(requestTransactionService.getRequestById(null))
				.thenReturn(Optional.of(new RequestTransaction()));
		assertEquals(204, requestTransactionController.getRequestById(1)
				.getStatusCodeValue());

	}

	@Test
	void testDeleteRequestById() throws ParseException {

		RequestTransaction requestTransaction2 = modelMapper
				.map(requestTransaction, RequestTransaction.class);

		when(requestTransactionService.getRequestById(Mockito.anyInt()))
				.thenReturn(Optional.of(requestTransaction2));
		requestTransactionService.deleteRequestById(Mockito.anyInt());
		assertNotNull(requestTransaction2);
		assertEquals(
				Collections.singletonMap("success",
						"Record deleted Successfully"),
				requestTransactionController.deleteRequestById(1));

	}

	@Test
	void testDeleteRequestByIdIsNull() throws ParseException {

		RequestTransaction requestTransaction2 = modelMapper
				.map(requestTransaction, RequestTransaction.class);

		when(requestTransactionService.getRequestById(null))
				.thenReturn(Optional.of(requestTransaction2));
		requestTransactionService.deleteRequestById(null);
		assertNotNull(requestTransaction2);
		assertEquals(
				Collections.singletonMap("Failed",
						"Record failed to delete. error occurs"),
				requestTransactionController.deleteRequestById(1));

	}

	@Test
	void testCreateRequest() throws ParseException {

		RequestTransaction requestTransaction2 = modelMapper
				.map(requestTransaction, RequestTransaction.class);

		when(requestTransactionService.createRequest(Mockito.any()))
				.thenReturn(requestTransaction2);
		assertNotNull(requestTransaction2);
		assertEquals(HttpStatus.OK, requestTransactionController
				.createRequest(1234, requestTransaction, null).getStatusCode());

	}

	@Test
	void testCreateRequestException() throws ParseException {

		try {
			when(requestTransactionService.createRequest(Mockito.any()))
					.thenThrow(new RequestTransactionException(""));
			requestTransactionController.createRequest(1234,
					new RequestTransactionDto(), null);
		} catch (Exception e) {
			assertTrue(e instanceof RequestTransactionException);
		}

	}

	@Test
	void testFilterRequests() {

		when(requestTransactionService.filterRequests(1, "1", "0", 27413,
				"Subhash", "2020-01-09", "hjhj", "hrushal"))
						.thenReturn(listGetAll);

		assertEquals(HttpStatus.OK,
				requestTransactionController
						.filterRequests(1, "1", "0", 27413, "Subhash",
								"2020-01-09", "hjhj", "hrushal")
						.getStatusCode());

	}

}