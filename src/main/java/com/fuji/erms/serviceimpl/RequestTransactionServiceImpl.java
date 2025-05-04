package com.fuji.erms.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuji.erms.dao.RequestTransactionDao;
import com.fuji.erms.daoimpl.RequestTransactionDaoImpl;
import com.fuji.erms.dto.RequestTransactionDto;
import com.fuji.erms.exceptions.UserMasterException;
import com.fuji.erms.model.RequestTransaction;
import com.fuji.erms.service.RequestTransactionService;

/**
 * @author Subhash This class is used for implements service class and
 *         contains all the logical codes
 *
 */
@Service
public class RequestTransactionServiceImpl
		implements RequestTransactionService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RequestTransactionServiceImpl.class);

	@Autowired
	RequestTransactionDao requestTransactionDao;

	@Autowired
	RequestTransactionDaoImpl requestTransactionDaoImpl;

	@Override
	public RequestTransaction createRequest(
			RequestTransaction requesttransaction) throws UserMasterException {
		try {
			return requestTransactionDao.save(requesttransaction);
		} catch (Exception e) {
			throw new UserMasterException("No content returned" + e);
		}
	}

	/**
	 * @author Subhash This method is used for Get all list of request
	 * @throws Exception
	 */
	@Override
	public List<RequestTransaction> getAll() throws UserMasterException {
		try {
			return requestTransactionDao.findAll();
		} catch (Exception e) {
			throw new UserMasterException("No data found" + e);
		}

	}

	/**
	 * @author Subhash This method is used for get request by Id
	 * @throws Exception
	 */
	@Override
	public Optional<RequestTransaction> getRequestById(Integer requestId)
			throws UserMasterException {
		try {
			return requestTransactionDao.findById(requestId);
		} catch (Exception e) {
			throw new UserMasterException("No content  Exception " + e);
		}
	}

	/**
	 * @author Subhash This method is used for delete request by request Id
	 * @throws Exception
	 */
	@Override
	public void deleteRequestById(Integer requestId)
			throws UserMasterException {
		try {
			requestTransactionDao.deleteRequestById(requestId);
		} catch (Exception e) {
			throw new UserMasterException("No content found : " + e);
		}
	}

	/**
	 * @author Subhash This method is used for update Request Status
	 * @throws Exception
	 */
	@Override
	public RequestTransaction updateStatus(
			RequestTransaction requestTransaction) throws UserMasterException {
		try {
			return requestTransactionDao.save(requestTransaction);
		} catch (Exception e) {
			throw new UserMasterException("No content is present" + e);

		}

	}

	/**
	 * @author Subhash
	 * @param Integer
	 *            requestId, String requestType, String requestStatus, Integer
	 *            employeeId, String employeeName, Integer createUser, Date
	 *            createDateTime, String remark, String requestBy
	 * @return List<RequestTransactionDto> This method is implemented for filter
	 *         the requests and get output list of requests.
	 */

	@Override
	public List<RequestTransactionDto> filterRequests(Integer requestId,
			String requestType, String requestStatus, Integer employeeId,
			String employeeName, String createDateTime, String remark,
			String requestBy) {
		LOGGER.info(
				"RequestTransaction Rest controller Implementation- filterRequests method:Start()");

		StringBuilder sb = new StringBuilder();
		sb.append(
				 "WITH table3 as (WITH table2 as (WITH table1 as (SELECT t.request_id,t.request_type,t.request_status,t.employee_id,t.create_user, t.create_datetime,t.remark, m.employee_name AS request_by FROM trn_request AS t INNER JOIN mst_employee AS m ON (t.create_user=m.employee_id)) SELECT d.request_id,d.request_type,d.request_status,d.employee_id,d.create_user, d.create_datetime,d.remark, d.request_by, e.fj_level as fj_level, e.jlpt_level as jlpt_level,e.employee_name as employee_name, e.manager_id as manager_id FROM table1 AS d LEFT JOIN mst_employee AS e ON (d.employee_id=e.employee_id)) SELECT  p.request_id,p.request_type,p.request_status,p.employee_id,p.employee_name,p.create_user, p.create_datetime,p.remark, p.request_by,p.fj_level,p.jlpt_level, p.manager_id, employee1.employee_name AS new_manager FROM table2 AS p LEFT JOIN mst_employee AS employee1 ON (CAST(p.remark AS int)=employee1.employee_id)) SELECT f.request_id,f.request_type,f.request_status,f.employee_id,f.employee_name,f.create_user, f.create_datetime,f.remark, f.request_by,f.fj_level,f.jlpt_level, f.new_manager , emp.employee_name AS manager_name FROM table3 AS f LEFT JOIN mst_employee AS emp ON (f.manager_id =emp.employee_id) WHERE ");

		if ((requestId == null)
				&& (requestType == null || requestType.equals("4"))
				&& (requestStatus == null || requestStatus.equals("3"))
				&& (employeeId == null)
				&& (employeeName == null || employeeName.equals(""))
				&& (createDateTime == null || createDateTime.equals(""))
				&& (remark == null || remark.equals(""))
				&& (requestBy == null || requestBy.equals(""))) {
			sb.setLength(0);
			sb.append( "WITH table3 as (WITH table2 as (WITH table1 as (SELECT t.request_id,t.request_type,t.request_status,t.employee_id,t.create_user, t.create_datetime,t.remark, m.employee_name AS request_by FROM trn_request AS t INNER JOIN mst_employee AS m ON (t.create_user=m.employee_id)) SELECT d.request_id,d.request_type,d.request_status,d.employee_id,d.create_user, d.create_datetime,d.remark, d.request_by, e.fj_level as fj_level, e.jlpt_level as jlpt_level, e.employee_name as employee_name,e.manager_id as manager_id FROM table1 AS d LEFT JOIN mst_employee AS e ON (d.employee_id=e.employee_id)) SELECT  p.request_id,p.request_type,p.request_status,p.employee_id,p.employee_name,p.create_user, p.create_datetime,p.remark, p.request_by,p.fj_level,p.jlpt_level, p.manager_id, employee1.employee_name AS new_manager FROM table2 AS p LEFT JOIN mst_employee AS employee1 ON (CAST(p.remark AS int)=employee1.employee_id)) SELECT f.request_id,f.request_type,f.request_status,f.employee_id,f.employee_name,f.create_user, f.create_datetime,f.remark, f.request_by,f.fj_level,f.jlpt_level, f.new_manager , emp.employee_name AS manager_name FROM table3 AS f LEFT JOIN mst_employee AS emp ON (f.manager_id =emp.employee_id) ");

		} else {
			LOGGER.info("Continue to filter..");

			if (requestId != null)

			{

				LOGGER.info("requestId is not null");
				sb.append("request_id=" + requestId + " AND ");

			} else {

				LOGGER.info("requestId is null");
			}

			if (requestType != null && !requestType.equals("4")) {

				LOGGER.info("requestType is not null");
				sb.append("request_type='" + requestType + "' AND ");

			} else {

				LOGGER.info("requestType is null");

			}

			if (requestStatus != null && !requestStatus.equals("3")) {

				LOGGER.info("requestStatus is not null");
				sb.append("request_status='" + requestStatus + "' AND ");

			} else {
				LOGGER.info("requestStatus is null");

			}

			if (employeeId != null) {

				LOGGER.info("employeeId is not null");
				sb.append("f.employee_id=" + employeeId + " AND ");

			} else {
				LOGGER.info("employeeId is null");

			}

			if (employeeName != null && !employeeName.equals("")) {

				LOGGER.info("employeeName is not null");

				sb.append("UPPER(f.employee_name) LIKE UPPER(" + "\'%"
						+ employeeName + "%\'" + ") AND ");

			} else {
				LOGGER.info("employeeName is null");

			}

			if (requestBy != null && !requestBy.equals("")) {

				LOGGER.info("requestBy is not null");
				sb.append("UPPER(request_by) LIKE UPPER(" + "\'%" + requestBy
						+ "%\'" + ") AND ");

			} else {
				LOGGER.info("requestBy is null");

			}

			if (createDateTime != null && !createDateTime.equals("")) {

				LOGGER.info("createDateTime is not null");
				sb.append("create_datetime='" + createDateTime + "' AND ");

			} else {
				LOGGER.info("createDateTime is null");

			}

			if (remark != null && !remark.equals("")) {

				LOGGER.info("remark is not null");
				sb.append("UPPER(remark) LIKE UPPER(" + "\'%" + remark + "%\'"
						+ ") AND ");

			} else {
				LOGGER.info("remark is null");

			}
			sb.setLength(sb.length() - 4);
		}

		sb.append("ORDER BY f.request_id DESC");
		String myQuery = sb.toString();
		LOGGER.info("Query: {}", myQuery);
		List<RequestTransactionDto> requestList = null;

		try {

			requestList = requestTransactionDaoImpl
					.getAllRequestByFilter(myQuery);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return requestList;
	}

}
