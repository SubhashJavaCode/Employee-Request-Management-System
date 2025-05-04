package com.fuji.erms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author HrushalG User Master DTO is an object that carries the data between
 *         controller.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserMasterDto {

	private int userId;
	private String userName;
	private String password;
	private String userRole;
}
