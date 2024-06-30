package com.codeclause.ems.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codeclause.ems.entity.AttendanceEntity;

import jakarta.transaction.Transactional;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity,Long> {
	
	public AttendanceEntity findByEmployee_EmployeeIdAndSelectedDate(Long employeeId,LocalDate selectedDate);
	
	public List<AttendanceEntity> 
	findAllByEmployee_EmployeeIdAndSelectedDateGreaterThanEqualAndSelectedDateLessThanEqual(Long empId,LocalDate fromDate,LocalDate toDate);
	
	@Modifying
	@Transactional
	@Query("update AttendanceEntity ae set ae.isDeleted =:flag where ae.attendanceId =:id")
	Integer updateDeleteFlag(@Param("flag") boolean flag, @Param("id") Long id);
}
