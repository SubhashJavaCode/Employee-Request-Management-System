package com.fuji.erms.util;

import java.sql.SQLException;

import org.springframework.security.web.firewall.RequestRejectedException;

import com.fuji.erms.constants.RequestManagementConstants;

/**
 * 
 * @author Subhash This class contains Util methods for Request Transaction
 *         class
 */

public class RequestTransactionUtil {
	private RequestTransactionUtil() {
		throw new RequestRejectedException("Can not be instantiated");
	}

	/**
	 * @author Subhash
	 * @param constantVar
	 * @return String This method used for convert Constant to String Name..
	 */
	public static String changeNameType(String constantVar) {
		if (constantVar != null) {

			if (constantVar.equals(RequestManagementConstants.MANAGER_CHANGE)) {
				return ("Manager Change");

			} else if (constantVar
					.equals(RequestManagementConstants.FJ_LEVEL_CHANGE)) {
				return ("FJ Level Change");
			} else {
				return ("JLPT Level Update");
			}
		} else {
			return null;
		}
	}

	/**
	 * @author Subhash
	 * @param constantVar
	 * @return String This method used for convert Constant to String Name..
	 */
	public static String changeNameStatus(String constantVar) {
		if (constantVar != null) {

			if (constantVar.equals(RequestManagementConstants.PENDING)) {
				return ("Pending");

			} else if (constantVar.equals(RequestManagementConstants.APPROVE)) {
				return ("Approved");
			} else {
				return ("Rejected");
			}
		} else {
			return null;
		}

	}

	/**
	 * @author Subhash
	 * @param constantVar
	 * @param newManager
	 * @param managerId 
	 * @param jlptLevel 
	 * @param fjLevel 
	 * @return String This method used for convert Constant to String Name..
	 * @throws SQLException
	 * @throws NullPointerException
	 * @throws NumberFormatException
	 */
	public static String changeNameRemark(String constantVar,
			String requestType, String newManager, String fjLevel, String jlptLevel, String managerName)
			throws NumberFormatException, NullPointerException {

		if (constantVar != null) {
			if (requestType.equals(RequestManagementConstants.MANAGER_CHANGE)) {

				return ("Current Manager: "+managerName+ "\nNew Manager: " + "(" + constantVar + ") "
						+ newManager);
			} else if (requestType
					.equals(RequestManagementConstants.FJ_LEVEL_CHANGE)) {

				return ("Current FJ Level: "+EmployeeMasterUtil.changeNameFjLevel(fjLevel)
						+ "\nNew FJ Level: " + "(" + constantVar + ") "
						+ EmployeeMasterUtil.changeNameFjLevel(constantVar));
			} else {
				return ("Current JLPT Level: "+EmployeeMasterUtil.changeNameJlptLevel(jlptLevel)
						+ "\nNew JLPT Level: " + "(" + constantVar + ") "
						+ EmployeeMasterUtil.changeNameJlptLevel(constantVar));
			}

		} else {

			return null;

		}
	}

}
