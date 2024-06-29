package com.codeclause.ems.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayrollDto {
	private Long payrollId;
	private Long employeeId;
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
	private BigDecimal totalEarnings;
	private BigDecimal totalDeductions;
	private Short payrollYear;
	private Short payrollMonth;
	private Short payrollDay;
	private String empName;
	private String empJobRole;
	private String empEmail;
	private String payrollMonthName;
}
