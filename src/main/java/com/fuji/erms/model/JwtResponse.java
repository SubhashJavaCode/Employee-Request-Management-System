package com.fuji.erms.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author HrushalG This class is for JWTtoken
 */
@Getter
@Setter
@AllArgsConstructor

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	UserMaster userMaster;
	@JsonFormat(timezone = "Asia/Kolkata")
	private Date expiryTokenTime;
	private String loginFailed;

}