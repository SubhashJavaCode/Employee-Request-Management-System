package com.fuji.erms.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.web.bind.annotation.CrossOrigin;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author MurtadakV POJO class for Request Transaction.
 */
@CrossOrigin("http://localhost:4200")
@Entity
@Table(name = "trn_request")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestTransaction implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "request_id", nullable = false, length = 5)
	private int requestId;
	@Column(name = "employee_id", nullable = false, length = 6)
	private int employeeId;
	@Column(name = "request_type", nullable = false, length = 1)
	private String requestType;
	@Column(name = "request_status", nullable = false, length = 1)
	private String requestStatus;
	@Column(name = "create_user", nullable = false, length = 6)
	private int createUser;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	@Temporal(TemporalType.DATE)
	@Column(name = "create_datetime")
	private Date createDateTime;
	@Column(name = "update_user", length = 6)
	private int updateUser;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	@Temporal(TemporalType.DATE)
	@Column(name = "update_datetime")
	private Date updateDateTime;
	@Column(name = "remark")
	private String remark;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id", referencedColumnName = "employee_id", insertable = false, updatable = false)
	private EmployeeMaster employeeMaster;
}