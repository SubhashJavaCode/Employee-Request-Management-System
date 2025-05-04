package com.fuji.erms.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author HrushalG This class is for JWTRequest with fields
 *         userId,password,userRole
 *
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;

	private int userId;
	private String password;
	private String userRole;

}