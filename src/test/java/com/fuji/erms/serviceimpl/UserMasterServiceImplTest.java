package com.fuji.erms.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fuji.erms.dao.UserMasterDao;
import com.fuji.erms.model.UserMaster;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(MockitoJUnitRunner.class)
class UserMasterServiceImplTest {

	@InjectMocks
	private UserMasterServiceImpl userMasterServiceImpl;
	@Mock
	private UserMasterDao userMasterDao;
	ModelMapper modelMapper = new ModelMapper();
	private List<UserMaster> userList;

	void loadData() {
		UserMaster userMaster1 = new UserMaster(1, "33320", "Subhash@123", "1",
				null);
		UserMaster userMaster2 = new UserMaster(2, "29961", "Hrushal@123", "1",
				null);
		userList = new ArrayList<>(Arrays.asList(userMaster1, userMaster2));

	}

	@BeforeEach
	public void setup() throws ParseException {
		MockitoAnnotations.openMocks(this);
		loadData();
	}

	@Test
	void testGetAll() {
		when(userMasterDao.findAll()).thenReturn(userList);
		assertEquals(userList.get(0), userMasterServiceImpl.getAll().get(0));

	}

}