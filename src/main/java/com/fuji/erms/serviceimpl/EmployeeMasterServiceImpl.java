package com.fuji.erms.serviceimpl;

import java.util.*;

import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fuji.erms.dao.EmployeeMasterDao;
import com.fuji.erms.exceptions.EmployeeMasterException;
import com.fuji.erms.exceptions.UserMasterException;
import com.fuji.erms.model.EmployeeMaster;
import com.fuji.erms.service.EmployeeMasterService;

/**
 * @author Subhash This class is used for implements service class and contains
 *         all the logical codes
 */
@Service
public class EmployeeMasterServiceImpl implements EmployeeMasterService {

	@Autowired
	EmployeeMasterDao employeedao;

	/**
	 * @author Subhash This method is used to fetch all the data from employee
	 *         master table.
	 * @throws Exception
	 */
	@Override
	public List<EmployeeMaster> getAll() throws EmployeeMasterException {
		try {

			return employeedao.findAll();
		} catch (JDBCException e) {
			throw new EmployeeMasterException("JDBC Exception " + e);
		} catch (Exception e) {
			throw new EmployeeMasterException("No Content" + e);
		}
	}

	/**
	 * @author Subhash This method is used for to Update the data by employee Id
	 *         from employee master table
	 * @throws Exception
	 */
	@Override
	public EmployeeMaster updateEmployee(EmployeeMaster employeemaster)
			throws EmployeeMasterException {
		try {
			return employeedao.save(employeemaster);
		} catch (Exception e) {
			throw new EmployeeMasterException("No Content" + e);
		}
	}

	/**
	 * @author Subhash This method is used for fetch the request by employee Id
	 *         from employee master table
	 * @throws Exception
	 */
	@Override
	public Optional<EmployeeMaster> getEmployeeById(Integer employeeId)
			throws EmployeeMasterException {
		try {

			return employeedao.findById(employeeId);
		} catch (Exception e) {
			throw new EmployeeMasterException("No content present" + e);

		}
	}

	/**
	 * @author Subhash This method is used for delete the employee request by
	 *         employee Id
	 * @throws Exception
	 */
	@Override
	public void deleteEmployeeByEmployeeId(Integer employeeId)
			throws EmployeeMasterException {
		try {
			employeedao.deleteEmployeeByEmployeeId(employeeId);
		} catch (Exception e) {
			throw new EmployeeMasterException("No content found" + e);
		}

	}

	@Override
	public List<EmployeeMaster> getManagers() {
		try {

			return (List<EmployeeMaster>) employeedao.getManagers();
		} catch (Exception e) {
			throw new UserMasterException("No Content" + e);
		}
	}

}
