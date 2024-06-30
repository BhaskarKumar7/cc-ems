package com.codeclause.ems.service.impl;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.codeclause.ems.dto.ApiResponseDto;
import com.codeclause.ems.dto.AttendanceDto;
import com.codeclause.ems.dto.AttendanceResponseDto;
import com.codeclause.ems.entity.AttendanceEntity;
import com.codeclause.ems.entity.EmployeeEntity;
import com.codeclause.ems.exception.InvalidInputException;
import com.codeclause.ems.repository.AttendanceRepository;
import com.codeclause.ems.repository.EmployeeRepository;
import com.codeclause.ems.service.AttendanceService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {
	
	private final AttendanceRepository attendanceRepository;
	private final EmployeeRepository employeeRepository;
	private static final String SUPER_ADMIN = "superadmin";
	
	@Override
	public AttendanceResponseDto saveOrUpdateAttendance(AttendanceDto attendanceDto) {
		
		if(attendanceDto.getInTimeHours() == null || attendanceDto.getInTimeMinutes() == null
				|| attendanceDto.getOutTimeHours() == null || attendanceDto.getOutTimeMinutes() == null)	{
			throw new InvalidInputException("In time and out time should not be empty!");
		}
		
		AttendanceEntity entity = attendanceRepository.save(convertDtoToEntity(attendanceDto));
		if(entity.getAttendanceId() == null)	{
			throw new RuntimeException("Not able to add attendance!");
		}
		
		AttendanceResponseDto response = new AttendanceResponseDto();
		response.setAttendanceId(entity.getAttendanceId());
		response.setStatusCode("200");
		response.setIndex(attendanceDto.getRowIndex());
		response.setNoOfHours(entity.getNoOfHours());
		if(null != attendanceDto.getAttendanceId() && attendanceDto.getAttendanceId() > 0l)	{
			response.setMessage("Attendance updated successfully!");	
		}
		else	{
			response.setMessage("Attendance submitted successfully!");
		}
		return response;
	}

	private AttendanceEntity convertDtoToEntity(AttendanceDto dto)	{
		AttendanceEntity entity = null;
		if(dto != null)	{
			entity = new AttendanceEntity();
			entity.setCreatedBy(SUPER_ADMIN);
			entity.setCreatedTime(LocalDateTime.now());
			entity.setModifiedBy(SUPER_ADMIN);
			entity.setModifiedTime(LocalDateTime.now());
			entity.setEmployee(employeeRepository.findById(dto.getEmployeeId()).get());
			entity.setInTimeHours(dto.getInTimeHours());
			entity.setInTimeMinutes(dto.getInTimeMinutes());
			entity.setOutTimeHours(dto.getOutTimeHours());
			entity.setOutTimeMinutes(dto.getOutTimeMinutes());
			LocalTime inTime = LocalTime.of(dto.getInTimeHours(), dto.getInTimeMinutes());
			LocalTime outTime = LocalTime.of(dto.getOutTimeHours(), dto.getOutTimeMinutes());
			entity.setNoOfHours(getFractionalHoursBetween(inTime, outTime));
			entity.setSelectedDate(LocalDate.of(dto.getYear(), dto.getMonth(), dto.getDay()));
			if(null != dto.getAttendanceId() && dto.getAttendanceId() > 0l) {
				entity.setAttendanceId(dto.getAttendanceId());	
			}
		}
		return entity;
	}
	
	private static double getFractionalHoursBetween(LocalTime start, LocalTime end) {
        if (end.isBefore(start)) {
            end = end.plusHours(24); // Assume end time is on the next day
        }
        Duration duration = Duration.between(start, end);
        return duration.toMinutes() / 60.0;
    }

	@Override
	public List<AttendanceDto> getMonthlyAttendanceOfEmployee(Integer month, Integer year,Long empId) {
		if(employeeRepository.findById(empId).isEmpty())	{
			throw new InvalidInputException("employee id " + empId + " is invalid!");
		}
		if(month > LocalDate.now().getMonthValue())	{
			throw new InvalidInputException("Future month is not allowed!");
		}
		byte index = -1;
		Map<String, AttendanceEntity> attendanceMap = new HashMap<>();
		LocalDate firstDay = LocalDate.of(year, month, 1);
		LocalDate lastDay = firstDay.withDayOfMonth(firstDay.lengthOfMonth());
		List<AttendanceDto> dtoList = new ArrayList<>();
		EmployeeEntity employee = employeeRepository.findById(empId).get();
		List<AttendanceEntity> attendanceList = 
	    attendanceRepository.findAllByEmployee_EmployeeIdAndSelectedDateGreaterThanEqualAndSelectedDateLessThanEqual(empId,firstDay,lastDay);
		
		if(!attendanceList.isEmpty())	{
			attendanceList.stream()
			.filter(emp -> emp.getIsDeleted() != null && !emp.getIsDeleted())
			.forEach(attendance -> attendanceMap.put(empId + "," + attendance.getSelectedDate(), attendance));
		}
		
		for(LocalDate currentDate = firstDay; !currentDate.isAfter(lastDay); currentDate = currentDate.plusDays(1))	{
			index++;
			AttendanceDto attendanceDto = new AttendanceDto();
			AttendanceEntity attendanceEntity = attendanceMap.get(empId + "," + currentDate);
			if(attendanceEntity != null)	{
				attendanceDto.setInTimeHours(attendanceEntity.getInTimeHours());
				attendanceDto.setInTimeMinutes(attendanceEntity.getInTimeMinutes());
				attendanceDto.setOutTimeHours(attendanceEntity.getOutTimeHours());
				attendanceDto.setOutTimeMinutes(attendanceEntity.getOutTimeMinutes());
				attendanceDto.setAttendanceId(attendanceEntity.getAttendanceId());
				attendanceDto.setNoOfHours(attendanceEntity.getNoOfHours());
			}
			attendanceDto.setEmpName(employee.getFirstName() +" "+ employee.getLastName());
			attendanceDto.setEmpMail(employee.getEmail());
			attendanceDto.setEmployeeId(employee.getEmployeeId());
			attendanceDto.setDayName(currentDate.getDayOfWeek().name());
			attendanceDto.setYear(year);
			attendanceDto.setDay(currentDate.getDayOfMonth());
			attendanceDto.setMonth(month);
			attendanceDto.setRowIndex(index);
			if(currentDate.getDayOfWeek().compareTo(DayOfWeek.SATURDAY) == 0 ||
					currentDate.getDayOfWeek().compareTo(DayOfWeek.SUNDAY) == 0)	{
				attendanceDto.setRowDisabled(true);	
			}
			dtoList.add(attendanceDto);
		}
		return dtoList;
	}

	@Override
	public ApiResponseDto removeAttendanceById(Long id) {
		log.info("-------AttendanceServiceImpl.removeAttendanceById------");
		Integer rowsAffected = attendanceRepository.updateDeleteFlag(true, id);
		log.info("rows affeted---> {}",rowsAffected);
		return new ApiResponseDto("Attendance removed successfully!", "200");
	}
}
