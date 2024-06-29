package com.codeclause.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeclause.ems.entity.EmployeePayrollEntity;

public interface EmployeePayrollRepository extends JpaRepository<EmployeePayrollEntity, Long> {

	public EmployeePayrollEntity findByEmployee_EmployeeIdAndPayrollYearAndPayrollMonth(Long employeeId,
			Short payrollYear, Short payrollMonth);

	public List<EmployeePayrollEntity> findByEmployee_EmployeeIdAndPayrollYearOrderByPayrollMonth(Long employeeId,
			Short payrollYear);
}
