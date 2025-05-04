package com.fuji.erms.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fuji.erms.constants.RequestManagementConstants;
import com.fuji.erms.exceptions.EmployeeMasterException;

/**
 * 
 * @author HrushalG This class is contains Util methods for EmployeeMaster
 *         controller
 *
 */
public final class EmployeeMasterUtil {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(EmployeeMasterUtil.class);

	public EmployeeMasterUtil() {
		throw new EmployeeMasterException("Can not be instantiated");
	}
	/**
	 * @author HrushalG
	 * @param constantVar
	 * @return String This method converts constant to String name
	 */
	public static String changeNameFjLevel(String constantVar) {
		if (constantVar != null) {

			if (constantVar.equals(RequestManagementConstants.FJ07)) {
				return "FJ07";

			} else if (constantVar.equals(RequestManagementConstants.FJ08)) {
				return "FJ08";

			} else if (constantVar.equals(RequestManagementConstants.FJ09)) {
				return "FJ09";

			} else if (constantVar.equals(RequestManagementConstants.FJ10)) {
				return "FJ10";

			} else {

				return "FJ11";

			}
		} else {
			LOGGER.error("Create Request:REMARK (NEW FJ LEVEL): null");
			return null;
		}

	}

	/**
	 * @author HrushalG
	 * @param constantVar
	 * @return String This method converts String name to constant
	 */
	public static String changeNameFjLevelReverse(String constantVar) {

		if (constantVar.equals("FJ07")) {
			return RequestManagementConstants.FJ07;

		} else if (constantVar.equals("FJ08")) {
			return RequestManagementConstants.FJ08;

		} else if (constantVar.equals("FJ09")) {
			return RequestManagementConstants.FJ09;

		} else if (constantVar.equals("FJ10")) {
			return RequestManagementConstants.FJ10;

		} else {
			return RequestManagementConstants.FJ11;

		}

	}

	/**
	 * @author HrushalG
	 * @param constantVar
	 * @return String This method converts Constant to String
	 */
	public static String changeNameJlptLevel(String constantVar) {

		if (constantVar != null) {
			if (constantVar.equals(RequestManagementConstants.JLPT_LEVEL_N1)) {
				return "JLPT N1";

			} else if (constantVar
					.equals(RequestManagementConstants.JLPT_LEVEL_N2)) {
				return "JLPT N2";

			} else if (constantVar
					.equals(RequestManagementConstants.JLPT_LEVEL_N3)) {
				return "JLPT N3";

			} else if (constantVar
					.equals(RequestManagementConstants.JLPT_LEVEL_N4)) {
				return "JLPT N4";

			} else if (constantVar
					.equals(RequestManagementConstants.JLPT_LEVEL_N5)) {
				return "JLPT N5";

			} else {
				return "NA";

			}
		} else {
			LOGGER.error("Create Request:REMARK (NEW JLPT LEVEL): null");
			return null;
		}

	}

	/**
	 * @author HrushalG
	 * @param constantVar
	 * @return String This method converts String name to constant
	 */

	public static String changeNameJlptLevelReverse(String constantVar) {

		if (constantVar.equals("JLPT N1")) {
			return RequestManagementConstants.JLPT_LEVEL_N1;

		} else if (constantVar.equals("JLPT N2")) {
			return RequestManagementConstants.JLPT_LEVEL_N2;

		} else if (constantVar.equals("JLPT N3")) {
			return RequestManagementConstants.JLPT_LEVEL_N3;

		} else if (constantVar.equals("JLPT N4")) {
			return RequestManagementConstants.JLPT_LEVEL_N4;

		} else if (constantVar.equals("JLPT N5")) {
			return RequestManagementConstants.JLPT_LEVEL_N5;

		} else {
			return RequestManagementConstants.JLPT_LEVEL_NA;

		}

	}

}
