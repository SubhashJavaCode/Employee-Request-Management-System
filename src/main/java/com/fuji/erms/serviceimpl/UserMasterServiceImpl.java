package com.fuji.erms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuji.erms.dao.UserMasterDao;
import com.fuji.erms.exceptions.UserMasterException;
import com.fuji.erms.model.UserMaster;
import com.fuji.erms.service.UserMasterService;
@Service
public class UserMasterServiceImpl implements UserMasterService {

	@Autowired
	UserMasterDao userMasterDao;

	@Override
	public List<UserMaster> getAll() throws UserMasterException {

		try {

			return (List<UserMaster>) userMasterDao.findAll();
		} catch (Exception e) {
			throw new UserMasterException("No Content"+ e);
		}
	}
}
