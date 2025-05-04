package com.fuji.erms.service;

import java.util.List;
import java.util.Optional;
import com.fuji.erms.exceptions.EmployeeMasterException;
import com.fuji.erms.model.EmployeeMaster;

public interface EmployeeMasterService {

	public List<EmployeeMaster> getAll() throws EmployeeMasterException;

	public EmployeeMaster updateEmployee(EmployeeMaster employeemaster)
			throws EmployeeMasterException;

	public Optional<EmployeeMaster> getEmployeeById(Integer employeeId)
			throws EmployeeMasterException;

	public void deleteEmployeeByEmployeeId(Integer employeeId)
			throws EmployeeMasterException;

	public List<EmployeeMaster> getManagers();

}
