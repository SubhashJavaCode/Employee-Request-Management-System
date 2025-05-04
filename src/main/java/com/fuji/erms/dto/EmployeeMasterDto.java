package com.fuji.erms.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author SatheA Employee Master DTO is an object that carries the data between
 *         controller.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeMasterDto {
	private String employeeName;
	private String employeeEmail;
	private int managerId;
	private String fjLevel;
	private String jlptLevel;
	private String primarySkill;
	private String secondarySkill;
	private Date doj;
	private String role;

}
