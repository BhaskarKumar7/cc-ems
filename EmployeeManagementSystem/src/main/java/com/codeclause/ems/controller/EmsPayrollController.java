package com.codeclause.ems.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codeclause.ems.dto.ApiResponseDto;
import com.codeclause.ems.dto.PayrollDto;
import com.codeclause.ems.service.EmployeePayrollService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ems/payrolls/")
@AllArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {RequestMethod.POST,RequestMethod.GET,RequestMethod.OPTIONS})
public class EmsPayrollController {

	private final EmployeePayrollService employeePayrollService;
	
	@PostMapping("create")
	public ResponseEntity<ApiResponseDto> createPayroll(@RequestBody PayrollDto payrollDto)	{
		log.info("----- inside EmsPayrollController.createPayroll --------");
		LocalDate currentDate = LocalDate.now();
		if(payrollDto.getPayrollYear().equals(Short.valueOf(currentDate.getYear()+"")) && 
				payrollDto.getPayrollMonth() > Short.valueOf(currentDate.getMonthValue()+""))	{
			throw new RuntimeException("Invalid payroll month selected!");
		}
		return new ResponseEntity<>(employeePayrollService.addPayroll(payrollDto),HttpStatus.CREATED);
	}
	
	@GetMapping("calculate/{email}")
	public ResponseEntity<PayrollDto> calculateMonthlyPayroll(@PathVariable String email)	{
		return new ResponseEntity<>(employeePayrollService.calculatePayroll(email),HttpStatus.OK);
	}
	
	@GetMapping("fetch/{empId}/year/{payrollYear}")
	public ResponseEntity<List<PayrollDto>> fetchEmployeePayrollDetails
					(@PathVariable Long empId,@PathVariable Short payrollYear)	{
		return new ResponseEntity<>(employeePayrollService
				.fetchEmployeePayrollDetails(empId, payrollYear),HttpStatus.OK);
	}
}
