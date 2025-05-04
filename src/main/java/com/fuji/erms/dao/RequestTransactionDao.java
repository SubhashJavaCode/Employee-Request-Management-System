package com.fuji.erms.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.fuji.erms.model.RequestTransaction;

@Repository
public interface RequestTransactionDao
		extends JpaRepository<RequestTransaction, Integer> {

	static final Logger LOGGER = LoggerFactory
			.getLogger(RequestTransactionDao.class);

	@SuppressWarnings("unchecked")
	RequestTransaction save(RequestTransaction requestTransaction);

	List<RequestTransaction> getRequestByEmployeeId(Integer employeeId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM RequestTransaction e WHERE (e.requestId=?1)")
	public void deleteRequestById(Integer requestId);

}
