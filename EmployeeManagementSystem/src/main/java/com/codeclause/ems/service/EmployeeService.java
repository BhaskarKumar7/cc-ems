package com.codeclause.ems.service;

import java.util.List;

import com.codeclause.ems.dto.ApiResponseDto;
import com.codeclause.ems.dto.EmployeeDto;

public interface EmployeeService {
	public ApiResponseDto saveEmployee(EmployeeDto employeeDto);
	public ApiResponseDto updateEmployee(EmployeeDto employeeDto);
	public boolean doesEmailExists(String email);
	public boolean doesMobileNoExists(String mobileNo);
	public boolean doesUANExists(String uan);
	public boolean doesPANExists(String pan);
	public List<EmployeeDto> getAllEmployyesIdAndNames();
	public EmployeeDto getEmployeeById(Long id);
	public List<EmployeeDto> deleteAnEmployee(Long id);
	public List<EmployeeDto> listEmployeeDetails();
}
