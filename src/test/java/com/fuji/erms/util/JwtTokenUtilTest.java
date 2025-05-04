package com.fuji.erms.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

class JwtTokenUtilTest {

	JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

	@Mock
	Claims claims;

	private UserDetails userDetails = new User("33320", "Subhash@1234",
			(Collection<? extends GrantedAuthority>) new ArrayList<GrantedAuthority>());

	@BeforeEach
	void setUp() throws Exception {

	}

	@Test
	void testGetUsernameFromToken() {
		String token = jwtTokenUtil
				.doGenerateToken(new HashMap<String, Object>(), "33320");
		String name = jwtTokenUtil.getUsernameFromToken(token);
		assertEquals("33320", name);

	}

	@Test
	void testGetExpirationDateFromToken() {
		String token = jwtTokenUtil
				.doGenerateToken(new HashMap<String, Object>(), "33320");
		Date expirationTime = jwtTokenUtil.getExpirationDateFromToken(token);
		assertNotNull(expirationTime);
	}

	@Test
	void testGetClaimFromToken() {
		String generateToken = jwtTokenUtil.generateToken(userDetails);
		assertNotNull(generateToken);
	}

	@Test
	void testGenerateToken() {
		String token = jwtTokenUtil
				.doGenerateToken(new HashMap<String, Object>(), "33320");
		assertNotNull(token);
	}

	@Test
	void testValidateToken() {
		String token = jwtTokenUtil
				.doGenerateToken(new HashMap<String, Object>(), "33320");
		Boolean validation = jwtTokenUtil.validateToken(token, userDetails);
		assertEquals(true, validation);
	}

}