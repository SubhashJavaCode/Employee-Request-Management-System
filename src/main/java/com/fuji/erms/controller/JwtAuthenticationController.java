package com.fuji.erms.controller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fuji.erms.dto.UserMasterDto;
import com.fuji.erms.exceptions.JwtException;
import com.fuji.erms.model.JwtRequest;
import com.fuji.erms.model.JwtResponse;
import com.fuji.erms.model.UserMaster;
import com.fuji.erms.serviceimpl.JwtUserDetailsServiceImpl;
import com.fuji.erms.util.JwtTokenUtil;

/**
 * @author Subhash
 *
 */

@RestController
@CrossOrigin("http://localhost:4200")
public class JwtAuthenticationController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(JwtAuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsServiceImpl jwtUserDetailsService;

	ModelMapper modelMapper = new ModelMapper();

	public JwtAuthenticationController(
			AuthenticationManager authenticationManager2,
			JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl2,
			JwtTokenUtil jwtUtil2) {
		this.authenticationManager = authenticationManager2;
		this.jwtUserDetailsService = jwtUserDetailsServiceImpl2;
		this.jwtTokenUtil = jwtUtil2;
	}

	/**
	 * @author Subhash
	 * @param authenticationRequest
	 * @return token
	 * @throws Exception
	 *             : Invalid Credentials This method is used for authentication
	 *             of user which is present in database with respective
	 *             credentials.
	 */
	@PostMapping(value = "/authenticate")
	public ResponseEntity<JwtResponse> createAuthenticationToken(
			@RequestBody JwtRequest authenticationRequest) throws JwtException {

		LOGGER.info(
				"JwtAuthenticationController: Authtenticate method :Started");

		Integer validateUser = jwtUserDetailsService.loadUserRoleByUserId(
				authenticationRequest.getUserId(),
				authenticationRequest.getUserRole());

		LOGGER.info(
				"JwtAuthenticationController: Authtenticate method :Role Validated");
		if (validateUser == 1) {
			LOGGER.info(
					"JwtAuthenticationController: Authtenticate method :Started");

			authenticate(String.valueOf(authenticationRequest.getUserId()),
					authenticationRequest.getPassword());

			final UserDetails userDetails = jwtUserDetailsService
					.loadUserByUsername(
							String.valueOf(authenticationRequest.getUserId()));
			UserMaster userMaster = jwtUserDetailsService
					.loadUserMasterByUsername(
							authenticationRequest.getUserId());

			LOGGER.info(
					"JwtAuthenticationController: Authtenticate method :generate token method :Started");

			final String token = jwtTokenUtil.generateToken(userDetails);

			LOGGER.info(
					"JwtAuthenticationController: Authtenticate method :Token generated");

			return ResponseEntity.ok(new JwtResponse(token, userMaster,
					jwtTokenUtil.getExpirationDateFromToken(token),
					"LOGIN SUCCESSFUL"));

		} else {

			switch (validateUser) {
			case 2:
				return ResponseEntity.ok(
						new JwtResponse(null, null, null, "Invalid User Role"));

			case 3:
				return ResponseEntity.ok(
						new JwtResponse(null, null, null, "Invalid User Id"));

			default:
				return ResponseEntity.ok(
						new JwtResponse(null, null, null, "Invalid Password"));

			}
		}
	}

	/**
	 * 
	 * @author Subhash
	 * 
	 * @param user
	 * 
	 * @return user
	 * 
	 * @throws Exception
	 *             This method is for register user in database with encrypted
	 *             password.
	 */
	@PostMapping(value = "/register")
	public ResponseEntity<UserMaster> saveUser(
			@RequestBody UserMasterDto userMasterDto) throws JwtException {

		UserMaster user = modelMapper.map(userMasterDto, UserMaster.class);
		LOGGER.info("JwtAuthenticationController: Register method :Started");
		return ResponseEntity.ok(jwtUserDetailsService.save(user));
	}

	/**
	 * @author Subhash
	 * @param username
	 * @param password
	 * @throws Exception
	 */

	private void authenticate(String username, String password)
			throws JwtException {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username,
							password));
		} catch (DisabledException e) {
			throw new JwtException("USER_DISABLED" + e);
		} catch (BadCredentialsException e) {
			throw new JwtException("INVALID_CREDENTIALS" + e);

		}
	}

}