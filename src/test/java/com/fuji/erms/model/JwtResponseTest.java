package com.fuji.erms.model;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

class JwtResponseTest {
	private static JwtResponse jwtResponse;

	@BeforeEach
	public void setup() throws ParseException {
		MockitoAnnotations.openMocks(this);
		loadData();

	}

	void loadData() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter.setLenient(false);
		Date date = formatter.parse("02/01/2023");
		EmployeeMaster employeeMaster = new EmployeeMaster(1234, "Apurva",
				"ApurvaSathe@gmail.com", 12345, "1", "2", "java", "SQL", null,
				"1", null, null);
		UserMaster user = new UserMaster(12345, "Subhash", "Subhash@123", "1",
				employeeMaster);
		jwtResponse = new JwtResponse("abcd123ef4ghi56", user, date, "failed");
	}

	@Test
	void getUserMaster() {
		UserMaster user = jwtResponse.getUserMaster();
		assertNotNull(user);
		assertEquals("Subhash", user.getUserName());

	}

	@Test
	void setUserMaster() {

		EmployeeMaster employeeMaster = new EmployeeMaster(1234, "Apurva",
				"ApurvaSathe@gmail.com", 12345, "1", "2", "java", "SQL", null,
				"1", null, null);
		UserMaster newUser = new UserMaster(12345, "Subhash", "Subhash@123",
				"1", employeeMaster);
		jwtResponse.setUserMaster(newUser);
		assertNotNull(newUser);
		assertEquals("Subhash", jwtResponse.getUserMaster().getUserName());

	}

	@Test
	void getExpiryTokenTime() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("02/01/2023");

		jwtResponse.setExpiryTokenTime(date);
		assertEquals(date, jwtResponse.getExpiryTokenTime());

	}

	@Test
	void setExpiryTokenTime() throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse("02/01/2023");
		jwtResponse.setExpiryTokenTime(date);
		assertEquals(date, jwtResponse.getExpiryTokenTime());

	}

	@Test
	void getLoginFailed() {
		jwtResponse.setLoginFailed("failed");
		assertEquals("failed", jwtResponse.getLoginFailed());

	}

	@Test
	void setLoginFailed() {
		jwtResponse.setLoginFailed("failed");
		assertEquals("failed", jwtResponse.getLoginFailed());

	}

	@Test
	void getJwttoken() {
		String token = jwtResponse.getJwttoken();
		assertEquals("abcd123ef4ghi56", token);
	}

}