package com.codeclause.ems.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.codeclause.ems.dto.ApiResponseDto;
import com.codeclause.ems.dto.PayrollDto;
import com.codeclause.ems.entity.EmployeeEntity;
import com.codeclause.ems.entity.EmployeePayrollEntity;
import com.codeclause.ems.exception.InvalidEmailException;
import com.codeclause.ems.exception.PayrollAlreadyAddedException;
import com.codeclause.ems.repository.EmployeePayrollRepository;
import com.codeclause.ems.repository.EmployeeRepository;
import com.codeclause.ems.service.EmployeePayrollService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeePayrollServiceImpl implements EmployeePayrollService {

	public static final String EMAIL_VERIFICATION = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
	private static final String SUPER_ADMIN = "superadmin";
	
	private final EmployeeRepository employeeRepo;
	private final EmployeePayrollRepository employeePayrollRepo;
	
	@Override
	public PayrollDto calculatePayroll(String email) {
		PayrollDto payrollDto = new PayrollDto();
		if(!email.matches(EMAIL_VERIFICATION))	{
			throw new InvalidEmailException("Entered email is invalid!");
		}
		EmployeeEntity employee = employeeRepo.findByEmail(email);
		if(employee == null)	{
			throw new InvalidEmailException("No employee found with this email!");
		}
		payrollDto.setEmployeeId(employee.getEmployeeId());
		payrollDto.setAnnualSalary(employee.getCtc());
		payrollDto.setBasicPay(calculateBasicSalary(employee.getCtc()));
		payrollDto.setConveyanceAllowance(new BigDecimal("1500"));
		payrollDto.setMedicalAllowance(new BigDecimal("1500"));
		payrollDto.setStatuatoryBonus(calculateStatuatoryBonusAmt());
		payrollDto.setHouseRentalAllowance(calculateHRAAmount(payrollDto.getBasicPay()));
		payrollDto.setSpecialAllowance(calculateSpecialAllowance(employee.getCtc(),payrollDto));
		
		payrollDto.setProfessionalTax(new BigDecimal("200"));
		payrollDto.setProvidentFund(calculatePFAmount(payrollDto.getBasicPay()));
		payrollDto.setIncomeTax(calculateTDSAmount(employee.getCtc()));
		
		payrollDto.setTotalEarnings(calculateTotalEarnings(payrollDto));
		payrollDto.setTotalDeductions(calculateTotalDeductions(payrollDto));
		payrollDto.setNetTakeHome(calculateNetTakeHome(payrollDto));
		
		return payrollDto;
	}
	
	private BigDecimal calculateTotalEarnings(PayrollDto dto) {
		log.info("=============== calculateTotalEarnings ================");
		BigDecimal totalEarnings = new BigDecimal("0");
		totalEarnings = totalEarnings.add(dto.getBasicPay());
		totalEarnings = totalEarnings.add(dto.getHouseRentalAllowance());
		totalEarnings = totalEarnings.add(dto.getStatuatoryBonus());
		totalEarnings = totalEarnings.add(dto.getMedicalAllowance());
		totalEarnings = totalEarnings.add(dto.getConveyanceAllowance());
		totalEarnings = totalEarnings.add(dto.getSpecialAllowance());
		log.info("total earnings----> {}",totalEarnings);
		return totalEarnings;
	}
	
	private BigDecimal calculateTotalDeductions(PayrollDto dto) {
		log.info("=============== calculateTotalDeductions ================");
		BigDecimal totalDeductions = new BigDecimal("0.0");
		totalDeductions = totalDeductions.add(dto.getProfessionalTax());
		totalDeductions = totalDeductions.add(dto.getProvidentFund());
		totalDeductions = totalDeductions.add(dto.getIncomeTax());
		log.info("total deductions----> {}",totalDeductions);
		return totalDeductions;
	}
	
	private BigDecimal calculateNetTakeHome(PayrollDto dto) {
		log.info("=============== calculateNetTakeHome ================");
		BigDecimal takeHome = dto.getTotalEarnings()
				.subtract(dto.getTotalDeductions());
		log.info("net takehome----> {}",takeHome);
		return takeHome;
	}
	
	private BigDecimal calculateBasicSalary(BigDecimal ctc)	{
		log.info("=============== calculateBasicSalary ================");
		log.info("annual ctc-----> {}",ctc);
		BigDecimal percentage = new BigDecimal("45");
		BigDecimal decimalPercentage = percentage.divide(new BigDecimal("100"));
		log.info("percentage-----> {}",decimalPercentage);
		BigDecimal annualBasicSalary = ctc.multiply(decimalPercentage);
		log.info("annual basic pay---> {}",annualBasicSalary);
		BigDecimal monthlyBasicSalary = annualBasicSalary
				.divide(new BigDecimal("12")).setScale(2, RoundingMode.HALF_UP);
		log.info("monthly basic pay--> {}",monthlyBasicSalary);
		return monthlyBasicSalary;
	}
	
	private BigDecimal calculatePFAmount(BigDecimal basicSalary)	{
		log.info("=============== calculatePFAmount ================");
		BigDecimal percentage = new BigDecimal("12");
		BigDecimal decimalPercentage = percentage.divide(new BigDecimal("100"));
		log.info("decimalPercentage-----> {}",decimalPercentage);
		BigDecimal pfAmount = basicSalary.multiply(decimalPercentage);
		log.info("pf amount---> {}",pfAmount);
		return pfAmount;
	}
	
	private BigDecimal calculateTDSAmount(BigDecimal annualCtc)	{
		log.info("=============== calculateTDSAmount ================");
		BigDecimal monthlySalary = annualCtc.divide(new BigDecimal("12"),2,RoundingMode.HALF_UP);
		BigDecimal percentage = new BigDecimal("11.5");
		BigDecimal decimalPercent = percentage.divide(new BigDecimal("100"));
		log.info("decimalPercent-----> {}",decimalPercent);
		BigDecimal tdsAmount = monthlySalary.multiply(decimalPercent);
		log.info("tds amount----> {}",tdsAmount);
		return tdsAmount;
	}
	
	private BigDecimal calculateHRAAmount(BigDecimal monthlyBasicPay)	{
		log.info("=============== calculateHRAAmount ================");
		BigDecimal percentage = new BigDecimal("50");
		BigDecimal decimalPercent = percentage.divide(new BigDecimal("100"));
		log.info("decimalPercent-----> {}",decimalPercent);
		BigDecimal hraAmount = monthlyBasicPay.multiply(decimalPercent);
		log.info("hra amount----> {}",hraAmount);
		return hraAmount;
	}
	
	private BigDecimal calculateStatuatoryBonusAmt()	{
		log.info("=============== calculateStatuatoryBonusAmt ================");
		BigDecimal percentage = new BigDecimal("8.33");
		BigDecimal decimalPercentage = percentage.divide(new BigDecimal("100"));
		log.info("decimalPercentage-----> {}",decimalPercentage);
		BigDecimal statuatoryBonus = new BigDecimal("7000")
				.multiply(decimalPercentage);
		log.info("statuator bonus----> {}",statuatoryBonus);
		return statuatoryBonus;
	}
	
	private BigDecimal calculateSpecialAllowance(BigDecimal annualCtc,PayrollDto payrollDto)	{
		log.info("=============== calculateSpecialAllowance ================");
		log.info("annual ctc---> {}",annualCtc);
		BigDecimal monthlySalary = annualCtc.divide(new BigDecimal("12"),2,RoundingMode.HALF_UP);
		log.info("monthly salary---> {}",monthlySalary);
		BigDecimal earningAmt = new BigDecimal("0.0");
		BigDecimal specialAllowance = new BigDecimal("0.0");
		earningAmt = earningAmt.add(payrollDto.getBasicPay());
		earningAmt = earningAmt.add(payrollDto.getHouseRentalAllowance());
		earningAmt = earningAmt.add(payrollDto.getStatuatoryBonus());
		earningAmt = earningAmt.add(payrollDto.getMedicalAllowance());
		earningAmt = earningAmt.add(payrollDto.getConveyanceAllowance());
		if(monthlySalary.compareTo(earningAmt)	> 0)	{
			specialAllowance = monthlySalary.subtract(earningAmt);
		}
		log.info("special allowance---> {}",specialAllowance);
		return specialAllowance;
	}

	@Override
	public ApiResponseDto addPayroll(PayrollDto payrollDto) {
		log.info(" <--- Payroll service addPayroll method ---> ");
		if(isPayrollAlreadyAdded(payrollDto))	{
			throw new PayrollAlreadyAddedException("Payroll already added!");
		}
		EmployeePayrollEntity entity = employeePayrollRepo.save(convertDtoToEntity(payrollDto));
		if(entity.getEmployeePayrollId() != null)	{
			return new ApiResponseDto("Payroll details added successfully!", "200");
		}
		return new ApiResponseDto("Payroll details not saved", "500");
	}
	
	private boolean isPayrollAlreadyAdded(PayrollDto payrollDto)	{
		log.info("payroll year---> {}",payrollDto.getPayrollYear());
		log.info("payroll month---> {}",payrollDto.getPayrollMonth());
		return employeePayrollRepo.findByEmployee_EmployeeIdAndPayrollYearAndPayrollMonth
		(payrollDto.getEmployeeId(), payrollDto.getPayrollYear(), payrollDto.getPayrollMonth()) != null;
	}
	
	private EmployeePayrollEntity convertDtoToEntity(PayrollDto payrollDto)	{
		EmployeePayrollEntity entity = null;
		if(payrollDto != null) {
			entity = new EmployeePayrollEntity();
			entity.setAnnualSalary(payrollDto.getAnnualSalary());
			entity.setBasicPay(payrollDto.getBasicPay());
			entity.setConveyanceAllowance(payrollDto.getConveyanceAllowance());
			entity.setCreatedBy(SUPER_ADMIN);
			entity.setCreatedTime(LocalDateTime.now());
			entity.setHouseRentalAllowance(payrollDto.getHouseRentalAllowance());
			entity.setIncomeTax(payrollDto.getIncomeTax());
			entity.setMedicalAllowance(payrollDto.getMedicalAllowance());
			entity.setModifiedBy(SUPER_ADMIN);
			entity.setModifiedTime(LocalDateTime.now());
			entity.setNetTakeHome(payrollDto.getNetTakeHome());
			entity.setProfessionalTax(payrollDto.getProfessionalTax());
			entity.setProvidentFund(payrollDto.getProvidentFund());
			entity.setSpecialAllowance(payrollDto.getSpecialAllowance());
			entity.setStatuatoryBonus(payrollDto.getStatuatoryBonus());
			entity.setEmployee(employeeRepo.findById(payrollDto.getEmployeeId()).get());
			entity.setPayrollYear(payrollDto.getPayrollYear());
			entity.setPayrollMonth(payrollDto.getPayrollMonth());
			entity.setPayrollDay(payrollDto.getPayrollDay());
		}
		return entity;
	}

	@Override
	public List<PayrollDto> fetchEmployeePayrollDetails(Long empId, Short year) {
		List<PayrollDto> dtoList = new ArrayList<>();
		List<EmployeePayrollEntity> payrolls =  employeePayrollRepo
				.findByEmployee_EmployeeIdAndPayrollYearOrderByPayrollMonth(empId, year);
		if(!payrolls.isEmpty())	{
			payrolls.forEach(payroll -> {
				PayrollDto dto = new PayrollDto();
				dto.setPayrollId(payroll.getEmployeePayrollId());
				dto.setNetTakeHome(payroll.getNetTakeHome());
				dto.setPayrollMonth(payroll.getPayrollMonth());
				dto.setPayrollMonthName(Month.of(payroll.getPayrollMonth()).name());
				EmployeeEntity emp = payroll.getEmployee();
				if(emp != null)	{
					dto.setEmployeeId(emp.getEmployeeId());
					dto.setEmpName(emp.getFirstName() + " " + emp.getLastName());
					dto.setEmpEmail(emp.getEmail());
					if(emp.getJobRole() != null)	{
						dto.setEmpJobRole(emp.getJobRole().getRoleTitle());	
					}
				}
				dtoList.add(dto);
			});
		}
		return dtoList;
	}
}
