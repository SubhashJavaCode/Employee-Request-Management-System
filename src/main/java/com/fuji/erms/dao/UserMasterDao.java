package com.fuji.erms.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fuji.erms.model.UserMaster;

/**
 * 
 * @author Subhash In this class extending CrudRepository and declaring methods
 *         for fetching and saving the data.
 */

@Repository
public interface UserMasterDao extends CrudRepository<UserMaster, Integer> {

	@SuppressWarnings("unchecked")
	public UserMaster save(UserMaster newUser);

	@Query(value = "SELECT * FROM mst_user  WHERE employee_id =?1", nativeQuery = true)
	public UserMaster loadUserMasterByUsername(int userId);

}
