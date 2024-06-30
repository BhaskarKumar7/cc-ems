package com.codeclause.ems.service.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codeclause.ems.dto.AbsenceDto;
import com.codeclause.ems.dto.ApiResponseDto;
import com.codeclause.ems.entity.AbsenceEntity;
import com.codeclause.ems.entity.AttendanceEntity;
import com.codeclause.ems.entity.EmployeeEntity;
import com.codeclause.ems.enums.AbsenceTypeEnum;
import com.codeclause.ems.exception.InvalidInputException;
import com.codeclause.ems.repository.AbsenceRepository;
import com.codeclause.ems.repository.AttendanceRepository;
import com.codeclause.ems.repository.EmployeeRepository;
import com.codeclause.ems.service.AbsenceService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AbsenceServiceImpl implements AbsenceService {

	private final AbsenceRepository absenceRepository;
	private final EmployeeRepository empRepo;
	private final AttendanceRepository attendanceRepository;
	
	private static final String SUPER_ADMIN = "superadmin";
	
	@Override
	public ApiResponseDto saveAbsence(AbsenceDto absenceDto) {
		Optional<EmployeeEntity> entityOptional = empRepo.findById(absenceDto.getEmployeeId());
		if(!entityOptional.isPresent())	{
			throw new InvalidInputException("employee id is invalid!");
		}
		AbsenceEntity entity = convertDtoToEntity(absenceDto, entityOptional.get());
		List<AttendanceEntity> attendaceList = attendanceRepository.
		findAllByEmployee_EmployeeIdAndSelectedDateGreaterThanEqualAndSelectedDateLessThanEqual
		(entity.getEmployee().getEmployeeId(), entity.getFromDate().toLocalDate(), entity.getToDate().toLocalDate());
		if(!attendaceList.isEmpty())	{
			throw new InvalidInputException("Attendance already added between this date range. please check!");
		}
		entity = absenceRepository.save(entity);
		if(entity.getAbsenceId() == null) {
			throw new RuntimeException("Not able to process your request!");
		}
		return new ApiResponseDto("Absence request added successfully!", "200");
	}

	private AbsenceEntity convertDtoToEntity(AbsenceDto absenceDto,EmployeeEntity employee) {
		AbsenceEntity entity = null;
		if(absenceDto != null) {
			LocalDate fromDate = LocalDate.of(absenceDto.getFromYear(), absenceDto.getFromMonth(), absenceDto.getFromDay());
			LocalDate toDate = LocalDate.of(absenceDto.getToYear(), absenceDto.getToMonth(), absenceDto.getToDay());
			if(fromDate.compareTo(toDate) > 0)	{
				throw new InvalidInputException("Fromdate is greater than toDate. please check!");
			}
			entity = new AbsenceEntity();
			entity.setAbsenceType(AbsenceTypeEnum.valueOf(absenceDto.getAbsenceType()));
			entity.setCreatedBy(SUPER_ADMIN);
			entity.setCreatedTime(LocalDateTime.now());
			entity.setModifiedBy(SUPER_ADMIN);
			entity.setModifiedTime(LocalDateTime.now());
			entity.setEmployee(employee);
			entity.setFromDate(LocalDateTime.of(fromDate, LocalTime.of(00, 00, 00)));
			entity.setToDate(LocalDateTime.of(toDate, LocalTime.of(23, 59, 59)));
			entity.setNoOfDays((int)Duration.between(toDate, fromDate).toDays());
		}
		return entity;
	}
}
