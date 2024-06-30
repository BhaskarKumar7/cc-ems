package com.codeclause.ems.service;

import java.util.List;

import com.codeclause.ems.dto.ApiResponseDto;
import com.codeclause.ems.dto.AttendanceDto;
import com.codeclause.ems.dto.AttendanceResponseDto;

public interface AttendanceService {
	public AttendanceResponseDto saveOrUpdateAttendance(AttendanceDto attendanceDto);
	public List<AttendanceDto> getMonthlyAttendanceOfEmployee(Integer month,Integer year,Long empId);
	public ApiResponseDto removeAttendanceById(Long id);
}
