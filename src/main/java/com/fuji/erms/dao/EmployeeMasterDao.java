package com.fuji.erms.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fuji.erms.model.EmployeeMaster;

@Repository
public interface EmployeeMasterDao
		extends JpaRepository<EmployeeMaster, Integer> {

	@SuppressWarnings("unchecked")
	EmployeeMaster save(EmployeeMaster employeemaster);

	public List<EmployeeMaster> getEmployeeByEmployeeName(String employeeName);

	public Optional<EmployeeMaster> getManagerByemployeeId(Integer employeeId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM EmployeeMaster e WHERE (e.employeeId=?1)")
	public void deleteEmployeeByEmployeeId(Integer employeeId);

	@Transactional
	@Modifying
	@Query(value = "SELECT e FROM EmployeeMaster e WHERE (e.role='1' OR e.role='2')")
	List<EmployeeMaster> getManagers();

}
