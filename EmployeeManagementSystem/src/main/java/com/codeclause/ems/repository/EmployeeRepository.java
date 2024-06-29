package com.codeclause.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codeclause.ems.entity.EmployeeEntity;

import jakarta.transaction.Transactional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
	@Modifying
	@Transactional
	@Query("update EmployeeEntity ee set ee.isDeleted = :flag where ee.employeeId = :id")
	Integer updateDeleteFlag(@Param("id") Long empId,@Param("flag") boolean status);
	EmployeeEntity findByEmail(String email);
	EmployeeEntity findByMobileNumber(String mobileNumber);
	EmployeeEntity findByPanNumber(String panNumber);
	EmployeeEntity findByUniversalAccountNumber(String universalAccountNumber);
	@Query("select e from EmployeeEntity e where e.isDeleted =:flag")
	List<EmployeeEntity> findAllEmployeesByFlag(@Param("flag") boolean isDeleted);
}
