package com.fuji.erms.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import com.fuji.erms.dto.UserMasterDto;
import com.fuji.erms.exceptions.JwtException;
import com.fuji.erms.model.JwtRequest;
import com.fuji.erms.model.UserMaster;
import com.fuji.erms.serviceimpl.JwtUserDetailsServiceImpl;
import com.fuji.erms.util.JwtTokenUtil;

class JwtAuthenticationControllerTest {

	@InjectMocks
	private JwtAuthenticationController jwtController;

	@Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private JwtTokenUtil jwtUtil;

	@Mock
	private JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

	UserMaster userMaster;
	UserDetails userDetails;

	ModelMapper modelMapper = new ModelMapper();

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		jwtController = new JwtAuthenticationController(authenticationManager,
				jwtUserDetailsServiceImpl, jwtUtil);
	}

	@Test
	void testCreateAuthenticationToken() {
		UserMaster user = new UserMaster(33320, "Subhash", "Subhash@123", "1",
				null);
		UserDetails loadUser = jwtUserDetailsServiceImpl
				.loadUserByUsername("Gaurav");
		when(jwtUserDetailsServiceImpl.loadUserRoleByUserId(33320, "1"))
				.thenReturn(1);
		UserDetails userDetails = new User(String.valueOf(user.getUserId()),
				user.getPassword(), new ArrayList<>());
		JwtRequest jwtRequest = new JwtRequest(33320, "Subhash@123", "1");
		Mockito.when(jwtUserDetailsServiceImpl
				.loadUserByUsername(user.getUserName()))
				.thenReturn(userDetails);
		Mockito.when(jwtUtil.generateToken(loadUser)).thenReturn("token");
		ResponseEntity<?> login = jwtController
				.createAuthenticationToken(jwtRequest);
		assertNotNull(userDetails);
		assertEquals(200, login.getStatusCodeValue());
	}

	@Test
	void testCreateAuthenticationToken2() {
		UserMaster user = new UserMaster(33320, "Subhash", "Subhash@123", "1",
				null);
		UserDetails loadUser = jwtUserDetailsServiceImpl
				.loadUserByUsername("Gaurav");
		when(jwtUserDetailsServiceImpl.loadUserRoleByUserId(33320, "1"))
				.thenReturn(2);
		UserDetails userDetails = new User(String.valueOf(user.getUserId()),
				user.getPassword(), new ArrayList<>());
		JwtRequest jwtRequest = new JwtRequest(33320, "Subhash@123", "1");
		Mockito.when(jwtUserDetailsServiceImpl
				.loadUserByUsername(user.getUserName()))
				.thenReturn(userDetails);
		Mockito.when(jwtUtil.generateToken(loadUser)).thenReturn("token");
		ResponseEntity<?> login = jwtController
				.createAuthenticationToken(jwtRequest);
		assertNotNull(userDetails);
		assertEquals(200, login.getStatusCodeValue());
	}

	@Test
	void testCreateAuthenticationToken3() {
		UserMaster user = new UserMaster(29961, "Hrushal", "Hrushal@123", "1",
				null);
		UserDetails loadUser = jwtUserDetailsServiceImpl
				.loadUserByUsername("Gaurav");
		when(jwtUserDetailsServiceImpl.loadUserRoleByUserId(29961, "1"))
				.thenReturn(3);
		UserDetails userDetails = new User(String.valueOf(user.getUserId()),
				user.getPassword(), new ArrayList<>());
		JwtRequest jwtRequest = new JwtRequest(29961, "Hrushal@123", "1");
		Mockito.when(jwtUserDetailsServiceImpl
				.loadUserByUsername(user.getUserName()))
				.thenReturn(userDetails);
		Mockito.when(jwtUtil.generateToken(loadUser)).thenReturn("token");
		ResponseEntity<?> login = jwtController
				.createAuthenticationToken(jwtRequest);
		assertNotNull(userDetails);
		assertEquals(200, login.getStatusCodeValue());
	}

	@Test
	void testCreateAuthenticationToken4() {
		UserMaster user = new UserMaster(33320, "Subhash", "Subhash@123", "1",
				null);
		UserDetails loadUser = jwtUserDetailsServiceImpl
				.loadUserByUsername("Gaurav");
		when(jwtUserDetailsServiceImpl.loadUserRoleByUserId(33320, "1"))
				.thenReturn(4);
		UserDetails userDetails = new User(String.valueOf(user.getUserId()),
				user.getPassword(), new ArrayList<>());
		JwtRequest jwtRequest = new JwtRequest(33320, "Subhash@123", "1");
		Mockito.when(jwtUserDetailsServiceImpl
				.loadUserByUsername(user.getUserName()))
				.thenReturn(userDetails);
		Mockito.when(jwtUtil.generateToken(loadUser)).thenReturn("token");
		ResponseEntity<?> login = jwtController
				.createAuthenticationToken(jwtRequest);
		assertNotNull(userDetails);
		assertEquals(200, login.getStatusCodeValue());
	}
	

	@Test
	void testLoadUserByUsernameReturningNull() throws JwtException {

		when(authenticationManager.authenticate(Mockito.any()))
				.thenThrow(new DisabledException("ERROR"));
		JwtRequest jwtRequest = new JwtRequest(33320, "Subhash@123", "1");
		assertNotNull(jwtRequest);	
		jwtController.createAuthenticationToken(jwtRequest);

	}

	@Test
	void testLoadUserByUsernameWrong() throws JwtException {

		Mockito.when(authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken("ABC", "ABC")))
				.thenThrow(BadCredentialsException.class);
	}

	@Test
	void testSaveUser() {

		UserMasterDto userMasterDto = new UserMasterDto(33320, "Subhash",
				"Subhash@1234", "1");
		UserMaster userMaster = modelMapper.map(userMasterDto,
				UserMaster.class);
		jwtUserDetailsServiceImpl.save(userMaster);

		when(jwtUserDetailsServiceImpl.save(userMaster)).thenReturn(userMaster);
		assertNotNull(userMaster);
		assertEquals(33320,
				jwtUserDetailsServiceImpl.save(userMaster).getUserId());
		ResponseEntity<?> saveUser = jwtController.saveUser(userMasterDto);
		assertNotNull(userMaster);
		assertEquals(200, saveUser.getStatusCodeValue());
	}

}