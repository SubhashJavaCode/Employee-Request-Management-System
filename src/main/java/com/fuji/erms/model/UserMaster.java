package com.fuji.erms.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author HrushalG POJO class for userMaster
 *
 */
@SuppressWarnings("serial")
@CrossOrigin("http://localhost:4200")
@Entity
@Table(name = "mst_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserMaster implements Serializable {

	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "employee_id", nullable = false, length = 6)
	private int userId;
	@Column(name = "user_name", nullable = false, length = 50)
	private String userName;
	@JsonIgnore
	@Column(name = "password", nullable = false, length = 300)
	private String password;
	@Column(name = "user_role", nullable = false, length = 1)
	private String userRole;
	@JsonBackReference
	@OneToOne
	@MapsId
	@JoinColumn(name = "employee_id")
	private EmployeeMaster employeeMaster;

}
