package com.fuji.erms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author HrushalG This DTO class is for returning required information for
 *         Create Request Page.
 *
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeManagerDetailsDto {
	private String employeeName;

	private int managerId;
	private String fjLevel;
	private String jlptLevel;
	private String managerName;

}
