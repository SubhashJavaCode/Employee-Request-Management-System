package com.fuji.erms.daoimpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import com.fuji.erms.dto.RequestTransactionDto;
import com.fuji.erms.util.RequestTransactionUtil;

public class RequestTransactionDaoImpl extends JdbcDaoSupport {

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RequestTransactionDaoImpl.class);

	public List<RequestTransactionDto> getAllRequestByFilter(String querry)
			throws NumberFormatException, NullPointerException {
		LOGGER.info(
				"RequestTransactionDaoImpl: getAllRequestByFilter( ) : Started");

		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(querry);

		List<RequestTransactionDto> requestTransactionDtoList = new ArrayList<>();
		for (Map<String, Object> rs : rows) {
			RequestTransactionDto requestTransaction = new RequestTransactionDto();

			requestTransaction.setRequestId((Integer) rs.get("request_id"));
			requestTransaction.setRequestType(RequestTransactionUtil
					.changeNameType((String) rs.get("request_type")));
			requestTransaction.setRequestStatus(RequestTransactionUtil
					.changeNameStatus((String) rs.get("request_status")));
			requestTransaction.setEmployeeId((Integer) rs.get("employee_id"));
			requestTransaction
					.setEmployeeName((String) rs.get("employee_name"));
			requestTransaction.setRequestBy((String) rs.get("request_by"));
			requestTransaction
					.setCreateDateTime((Date) rs.get("create_datetime"));
			requestTransaction.setRemark(RequestTransactionUtil
					.changeNameRemark((String) rs.get("remark"),
							((String) rs.get("request_type")),
							((String) rs.get("new_manager")),
							((String) rs.get("fj_level")),
							((String) rs.get("jlpt_level")),
							((String) rs.get("manager_name"))));

			requestTransactionDtoList.add(requestTransaction);
		}
		return requestTransactionDtoList;
	}

}
