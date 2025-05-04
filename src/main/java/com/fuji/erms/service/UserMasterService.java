package com.fuji.erms.service;

import java.util.List;

import com.fuji.erms.exceptions.UserMasterException;
import com.fuji.erms.model.UserMaster;

public interface UserMasterService {

	List<UserMaster> getAll() throws UserMasterException;

}
