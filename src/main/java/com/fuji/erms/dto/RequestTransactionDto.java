package com.fuji.erms.dto;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Subhash Request transaction DTO is an object that carries data
 *         between controller
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestTransactionDto {

	private Integer requestId;
	private Integer employeeId;
	private String requestType;
	private String employeeName;
	private String requestStatus;
	private Integer createUser;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	@Temporal(TemporalType.DATE)
	private Date createDateTime;
	private Integer updateUser;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	@Temporal(TemporalType.DATE)
	private Date updateDateTime;
	private String remark;
	private String requestBy;

}
