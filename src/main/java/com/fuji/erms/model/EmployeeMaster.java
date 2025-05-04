package com.fuji.erms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Subhash POJO class for Employee Master.
 */
@CrossOrigin("http://localhost:4200")
@Entity
@Table(name = "mst_employee")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeMaster implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id", nullable = false, length = 6)
	private int employeeId;

	@Column(name = "employee_name", nullable = false, length = 50)
	private String employeeName;

	@Column(name = "employee_email", nullable = false, length = 100)
	private String employeeEmail;

	@Column(name = "manager_id", nullable = false, length = 6)
	private int managerId;

	@Column(name = "fj_level", nullable = false, length = 1)
	private String fjLevel;

	@Column(name = "jlpt_level", nullable = false, length = 1)
	private String jlptLevel;

	@Column(name = "primary_skill", nullable = false, length = 50)
	private String primarySkill;

	@Column(name = "secondary_skill", length = 100)
	private String secondarySkill;

	@Column(name = "doj", nullable = false)
	private Date doj;

	@Column(name = "role", nullable = false, length = 1)
	private String role;

	@JsonIgnore
	@OneToMany(mappedBy = "employeeMaster", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	private Set<RequestTransaction> requestTransactions;

	@JsonIgnore
	@OneToOne(mappedBy = "employeeMaster", fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private UserMaster userMaster;

}
