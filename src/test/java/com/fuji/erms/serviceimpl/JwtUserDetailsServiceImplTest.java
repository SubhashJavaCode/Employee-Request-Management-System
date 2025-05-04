package com.fuji.erms.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Collection;
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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.fuji.erms.dao.UserMasterDao;
import com.fuji.erms.dto.UserMasterDto;
import com.fuji.erms.model.UserMaster;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(MockitoJUnitRunner.class)
class JwtUserDetailsServiceImplTest {

	@InjectMocks
	private JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;
	@Mock
	private UserMasterDao userMasterDao;

	@Mock
	private PasswordEncoder bcryptEncoder;
	ModelMapper modelMapper = new ModelMapper();

	private UserDetails userDetails = new User("33320", "Subhash@1234",
			(Collection<? extends GrantedAuthority>) new ArrayList<GrantedAuthority>());

	private static UserMasterDto userMasterDto;

	void loadData() {
		userMasterDto = new UserMasterDto(12, "33320", "Subhash@1234", "1");
	}

	@BeforeEach
	void setUp() throws Exception {

		MockitoAnnotations.openMocks(this);
		loadData();
	}

	@Test
	void testLoadUserByUsername() {

		UserMaster userMaster = modelMapper.map(userMasterDto,
				UserMaster.class);
		Mockito.when(userMasterDao.loadUserMasterByUsername(12))
				.thenReturn(userMaster);
		jwtUserDetailsServiceImpl.loadUserByUsername("12");

	}

	@Test
	void testLoadUserByUsername2() throws UsernameNotFoundException {

		UserMaster userMaster = modelMapper.map(userMasterDto,
				UserMaster.class);

		Mockito.when(userMasterDao.loadUserMasterByUsername(Mockito.anyInt()))
				.thenReturn(userMaster);
		assertNotNull(userMaster);
		assertNotEquals(userDetails.getUsername(), jwtUserDetailsServiceImpl
				.loadUserByUsername("12").getUsername());

	}

	@Test
	void testLoadUserByUsername3() {
		try {
			when(userMasterDao.loadUserMasterByUsername(Mockito.anyInt()))
					.thenThrow(new BadCredentialsException(
							"Could not found credentials,Please Try Again Later"));
			jwtUserDetailsServiceImpl.loadUserByUsername("dwvsc");
		} catch (Exception e) {
			assertFalse(e instanceof BadCredentialsException);
		}
	}

	@Test
	void testSave() {

		UserMaster userMaster = modelMapper.map(userMasterDto,
				UserMaster.class);
		when(userMasterDao.save(Mockito.any())).thenReturn(userMaster);
		when(bcryptEncoder.encode(Mockito.any()))
				.thenReturn(userMaster.getPassword());

		assertEquals("33320",
				jwtUserDetailsServiceImpl.save(userMaster).getUserName());

	}

	@Test
	void testLoadUserRoleByUserId() {

		UserMaster userMaster = modelMapper.map(userMasterDto,
				UserMaster.class);
		Mockito.when(userMasterDao.loadUserMasterByUsername(12))
				.thenReturn(userMaster);
		assertNotNull(userMaster);
		assertEquals(1,
				jwtUserDetailsServiceImpl.loadUserRoleByUserId(12, "1"));
	}

	@Test
	void testLoadUserRoleByUserId2() {

		UserMaster userMaster = modelMapper.map(userMasterDto,
				UserMaster.class);
		Mockito.when(userMasterDao.loadUserMasterByUsername(12))
				.thenReturn(userMaster);
		assertNotNull(userMaster);
		assertNotEquals(1,
				jwtUserDetailsServiceImpl.loadUserRoleByUserId(12, "2"));
	}

	@Test
	void testLoadUserRoleByUserId3() {

		UserMaster userMaster = modelMapper.map(userMasterDto,
				UserMaster.class);
		Mockito.when(userMasterDao.loadUserMasterByUsername(12))
				.thenReturn(userMaster);
		assertNotNull(userMaster);
		assertNotEquals(1,
				jwtUserDetailsServiceImpl.loadUserRoleByUserId(1, "2"));
	}

	@Test
	void testLoadUserMasterByUsername() {
		UserMaster userMaster = modelMapper.map(userMasterDto,
				UserMaster.class);
		Mockito.when(userMasterDao.loadUserMasterByUsername(Mockito.anyInt()))
				.thenReturn(userMaster);
		assertNotNull(userMaster);
		assertEquals(userMaster.getUserName(), jwtUserDetailsServiceImpl
				.loadUserMasterByUsername(12).getUserName());
	}

}