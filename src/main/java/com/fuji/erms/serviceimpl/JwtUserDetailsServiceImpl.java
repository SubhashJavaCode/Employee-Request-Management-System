package com.fuji.erms.serviceimpl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fuji.erms.dao.UserMasterDao;
import com.fuji.erms.model.UserMaster;

/**
 * @author HrushalG This class is used as Service which implements
 *         UserDetailsService methods
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserMasterDao userMasterDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	private static final Logger logger = LoggerFactory
			.getLogger(JwtUserDetailsServiceImpl.class);

	/**
	 * @author HrushalG
	 * @param username
	 * @return UserDetails
	 * @exception UsernameNotFoundException
	 *                This method is used to fetch User data from database using
	 *                username as argument.
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		logger.info(
				"JwtUserDetailsService:  loadUserByUsername method :Started");
	//	int username1 = Integer.parseInt(username);
		UserMaster user = userMasterDao.loadUserMasterByUsername( Integer.parseInt(username));
		if (user != null) {

			if (String.valueOf(user.getUserId()).equals(username)) {
				logger.info(
						"JwtUserDetailsService:  loadUserByUsername method :Username Matched");
				try {
					return new User(String.valueOf(user.getUserId()),
							user.getPassword(), new ArrayList<>());

				} catch (Exception e) {
					throw new BadCredentialsException(username);
				}

			} else {
				logger.info(
						"JwtUserDetailsService:  loadUserByUsername method :User not found with username: {} ",
						username);
				throw new UsernameNotFoundException(
						"User not found with username: " + username);
			}
		} else {
			logger.info("User is null");
			throw new UsernameNotFoundException("User is null ");

		}

	}

	/**
	 * @author HrushalG
	 * @param userMaster
	 * @return UserMaster This method is used to Save User data into database
	 *         using userMaster as argument.
	 */
	public UserMaster save(UserMaster userMaster) {
		logger.info("JwtUserDetailsService:  save method :Started");
		UserMaster newUser = new UserMaster();
		newUser.setUserId(userMaster.getUserId());
		newUser.setUserName(userMaster.getUserName());
		newUser.setUserRole(userMaster.getUserRole());
		newUser.setPassword(bcryptEncoder.encode(userMaster.getPassword()));
		logger.info("JwtUserDetailsService:  save method :User Saved");
		return userMasterDao.save(newUser);
	}

	/**
	 * @author HrushalG
	 * @param userId
	 * @param userRole
	 * @return boolean This method is used to fetch User data from database
	 *         using userId and userRole as argument.
	 */
	public Integer loadUserRoleByUserId(int userId, String userRole) {
		logger.info(
				"JwtUserDetailsService:  loadUserRoleByUserId method :Started");
		UserMaster user = userMasterDao.loadUserMasterByUsername(userId);
		if (user != null) {

			if (user.getUserRole().equals(userRole)) {

				logger.info(
						"JwtUserDetailsService:  loadUserRoleByUserId method :End():");
				return 1;
			} else {
				logger.info(
						"JwtUserDetailsService:  loadUserRoleByUserId method :User role is WRONG: {}",
						userRole);
				return 2;
			
			}
		} else {
			logger.info(
					"JwtUserDetailsService:  loadUserRoleByUserId method :User not found with userId");
			
			return 3;

		}

	}

	public UserMaster loadUserMasterByUsername(int userId) {
		return userMasterDao.loadUserMasterByUsername(userId);

	}

}