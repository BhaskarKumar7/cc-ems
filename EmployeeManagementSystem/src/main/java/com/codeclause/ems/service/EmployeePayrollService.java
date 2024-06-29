package com.codeclause.ems.service;

import java.util.List;

import com.codeclause.ems.dto.ApiResponseDto;
import com.codeclause.ems.dto.PayrollDto;

public interface EmployeePayrollService {
	public PayrollDto calculatePayroll(String email);
	public ApiResponseDto addPayroll(PayrollDto payrollDto);
	public List<PayrollDto> fetchEmployeePayrollDetails(Long empId,Short year);
}
