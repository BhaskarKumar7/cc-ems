package com.codeclause.ems.entity;

import java.math.BigDecimal;

import com.codeclause.ems.constants.AppConstants;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = AppConstants.EMS_SCHEMA)
@Setter
@Getter
public class EmployeePayrollEntity extends TimeStampEntity {

	@Id
	@SequenceGenerator(schema = AppConstants.EMS_SCHEMA,initialValue = 1,allocationSize = 1,
	sequenceName = "seq_emp_payroll_entity",name = "emp_payroll_entity_seq_generator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "emp_payroll_entity_seq_generator")
	private Long employeePayrollId;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private EmployeeEntity employee;
	private BigDecimal annualSalary;
	private BigDecimal basicPay;
	private BigDecimal houseRentalAllowance;
	private BigDecimal statuatoryBonus;
	private BigDecimal medicalAllowance;
	private BigDecimal conveyanceAllowance;
	private BigDecimal specialAllowance;
	private BigDecimal professionalTax;
	private BigDecimal providentFund;
	private BigDecimal incomeTax;
	private BigDecimal netTakeHome;
	private Short payrollYear;
	private Short payrollMonth;
	private Short payrollDay;

}
