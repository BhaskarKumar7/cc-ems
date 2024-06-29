package com.codeclause.ems.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.codeclause.ems.constants.AppConstants;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = AppConstants.EMS_SCHEMA)
@Getter
@Setter
public class EmployeeEntity extends TimeStampEntity {

	@Id
	@SequenceGenerator(schema = AppConstants.EMS_SCHEMA,allocationSize = 1,initialValue = 1,
	sequenceName = "seq_employee_id",name = "employee_seq_generator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "employee_seq_generator")
	private Long employeeId;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String gender;
	private String email;
	private String mobileNumber;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private AddressEntity address;
	private LocalDate hiredDate;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id")
	private DepartmentEntity department;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "jobrole_id")
	private JobRoleEntity jobRole;
	private String panNumber;
	private String universalAccountNumber;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "bank_details_id")
	private EmployeeBankDetailsEntity bankDetails;
	@OneToMany(mappedBy = "employee")
	private List<EmployeePayrollEntity> employeePayroll;
	@OneToMany(mappedBy = "employee")
	private List<AttendanceEntity> attendance;
	@OneToMany(mappedBy = "employee")
	private List<AbsenceEntity> absence;
	private Boolean isDeleted;
	private BigDecimal ctc;
}
