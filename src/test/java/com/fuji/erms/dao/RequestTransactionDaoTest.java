package com.fuji.erms.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fuji.erms.model.RequestTransaction;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(MockitoJUnitRunner.class)
class RequestTransactionDaoTest {
	@Mock
	RequestTransactionDao requestTransactionDao;

	@Test
	void testSaveRequestTransaction() {
		RequestTransaction requestTransaction = new RequestTransaction(1, 27841,
				"1", "2", 27611, null, 27888, null, "FJ Level Change", null);
		when(requestTransactionDao.save(Mockito.any()))
				.thenReturn(requestTransaction);
		assertNotNull(requestTransaction);
		assertEquals(requestTransaction.getRequestId(),
				requestTransactionDao.save(requestTransaction).getRequestId());

	}

	@Test
	void testGetRequestByEmployeeId() {
		RequestTransaction requestTransaction = new RequestTransaction(1, 27841,
				"1", "2", 27611, null, 27888, null, "FJ Level Change", null);
		List<RequestTransaction> requestList = new ArrayList<>();
		requestList.add(requestTransaction);
		when(requestTransactionDao.getRequestByEmployeeId(Mockito.any()))
				.thenReturn(requestList);
		assertNotNull(requestTransaction);
		assertEquals(requestList.get(0).getRequestStatus(),
				requestTransactionDao.getRequestByEmployeeId(27841).get(0)
						.getRequestStatus());
	}

}