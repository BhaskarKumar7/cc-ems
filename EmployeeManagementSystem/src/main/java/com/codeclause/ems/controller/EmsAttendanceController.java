package com.codeclause.ems.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.codeclause.ems.dto.AbsenceDto;
import com.codeclause.ems.dto.ApiResponseDto;
import com.codeclause.ems.dto.AttendanceDto;
import com.codeclause.ems.dto.AttendanceResponseDto;
import com.codeclause.ems.enums.AbsenceTypeEnum;
import com.codeclause.ems.exception.InvalidInputException;
import com.codeclause.ems.service.AbsenceService;
import com.codeclause.ems.service.AttendanceService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ems/attendance/")
@AllArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {RequestMethod.POST,RequestMethod.GET,RequestMethod.OPTIONS})
public class EmsAttendanceController {

	private final AttendanceService attendanceService;
	private final AbsenceService absenceService;
	
	@PostMapping("addForDay")
	public ResponseEntity<AttendanceResponseDto> saveAttendance(@RequestBody AttendanceDto attendanceDto)	{
		log.info("==== start of EmsAttendanceController.saveAttendance ====");
		return new ResponseEntity<>(attendanceService.saveOrUpdateAttendance(attendanceDto),HttpStatus.OK);
	}
	
	@GetMapping("employeeId/{empId}/month/{month}/year/{year}")
	public ResponseEntity<List<AttendanceDto>> fetchAttendanceForMonth
				(@PathVariable Integer month,@PathVariable Long empId,@PathVariable Integer year)	{
		return new ResponseEntity<>(attendanceService
				.getMonthlyAttendanceOfEmployee(month, year, empId),HttpStatus.OK);
	}
	
	@PostMapping("/removeAttendance")
	public ResponseEntity<ApiResponseDto> removeAttendance(@RequestBody Map<String, Long> reqPayload)	{
		Long attId = reqPayload.get("attendanceId");
		if(null == attId || attId <= 0)	{
			throw new InvalidInputException("Cannot remove attendance. Not able to identify!");
		}
		return new ResponseEntity<>(attendanceService.removeAttendanceById(attId),HttpStatus.OK);
	}
	
	@GetMapping("/absenceTypes")
	public ResponseEntity<List<String>> fetchAbsenceTypes()	{
		List<String> absenceTypes = new ArrayList<>();
		for(AbsenceTypeEnum type : AbsenceTypeEnum.values())	{
			absenceTypes.add(type.name());
		}
		return new ResponseEntity<>(absenceTypes,HttpStatus.OK);
	}
	
	@PostMapping("/absence/add")
	public ResponseEntity<ApiResponseDto> addAbsenceOfEmployee(@RequestBody AbsenceDto absenceDto)	{
		return new ResponseEntity<>(absenceService.saveAbsence(absenceDto),HttpStatus.OK);
	}
}
