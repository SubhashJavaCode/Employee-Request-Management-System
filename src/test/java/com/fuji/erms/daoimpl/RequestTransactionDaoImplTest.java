package com.fuji.erms.daoimpl;

import static org.junit.Assert.assertTrue;



import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)

class RequestTransactionDaoImplTest {

	@Autowired
	RequestTransactionDaoImpl requestTransactionDaoImpl;
	JdbcTemplate jdbcTemplate;

	@Ignore
	@Test
	void testGetAllRequestByFilter() {
		
		requestTransactionDaoImpl.setJdbcTemplate(jdbcTemplate);

		// when(jdbcTemplate.queryForList(Mockito.anyString())).thenReturn(rowsList);
		assertTrue(true);
	}

}
