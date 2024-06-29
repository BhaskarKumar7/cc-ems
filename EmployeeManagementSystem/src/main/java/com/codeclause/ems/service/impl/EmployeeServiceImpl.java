package com.codeclause.ems.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.codeclause.ems.dto.AddressDto;
import com.codeclause.ems.dto.ApiResponseDto;
import com.codeclause.ems.dto.BankDetailsDto;
import com.codeclause.ems.dto.DepartmentDto;
import com.codeclause.ems.dto.EmployeeDto;
import com.codeclause.ems.dto.JobRoleDto;
import com.codeclause.ems.entity.AddressEntity;
import com.codeclause.ems.entity.DepartmentEntity;
import com.codeclause.ems.entity.EmployeeBankDetailsEntity;
import com.codeclause.ems.entity.EmployeeEntity;
import com.codeclause.ems.entity.JobRoleEntity;
import com.codeclause.ems.repository.DepartmentRepository;
import com.codeclause.ems.repository.EmployeeRepository;
import com.codeclause.ems.repository.JobRoleRepository;
import com.codeclause.ems.service.EmployeeService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final DepartmentRepository departmentRepository;
	private final JobRoleRepository jobRoleRepository;

	private static final String CREATED_BY = "superadmin";

	@Override
	public ApiResponseDto saveEmployee(EmployeeDto employeeDto) {
		employeeRepository.save(dtoToEntity(employeeDto));
		return new ApiResponseDto("Employee created successfully!", HttpStatus.OK.name());
	}

	private EmployeeEntity dtoToEntity(EmployeeDto dto) {
		EmployeeEntity entity = null;
		if (dto != null) {
			entity = new EmployeeEntity();
			entity.setFirstName(dto.getFirstName());
			entity.setLastName(dto.getLastName());
			entity.setEmail(dto.getEmail());
			entity.setMobileNumber(dto.getMobileNumber());
			entity.setDateOfBirth(dto.getDay() + "-" + dto.getMonth() + "-" + dto.getYear());
			entity.setGender(dto.getGender());
			entity.setPanNumber(dto.getPanNumber());
			entity.setUniversalAccountNumber(dto.getUniversalAccountNumber());
			entity.setHiredDate(LocalDate.now());
			entity.setCreatedBy(CREATED_BY);
			entity.setCreatedTime(LocalDateTime.now());
			entity.setModifiedBy(CREATED_BY);
			entity.setModifiedTime(LocalDateTime.now());
			entity.setCtc(dto.getCtc());
			if (dto.getDepartment() != null) {
				entity.setDepartment(departmentRepository.findById(dto.getDepartment().getDeptId()).get());
			}
			if (dto.getJobRole() != null) {
				entity.setJobRole(jobRoleRepository.findById(dto.getJobRole().getRoleId()).get());
			}
			if (dto.getAddress() != null) {
				AddressDto addressDto = dto.getAddress();
				AddressEntity addressEntity = new AddressEntity();
				addressEntity.setAddressLine(addressDto.getAddressLine());
				addressEntity.setCity(addressDto.getCity());
				addressEntity.setCountry(addressDto.getCountry());
				addressEntity.setCreatedBy(CREATED_BY);
				addressEntity.setCreatedTime(LocalDateTime.now());
				addressEntity.setModifiedBy(CREATED_BY);
				addressEntity.setModifiedTime(LocalDateTime.now());
				addressEntity.setPincode(addressDto.getPincode());
				addressEntity.setState(addressDto.getState());
				entity.setAddress(addressEntity);
			}
			if (dto.getBankDetails() != null) {
				BankDetailsDto bankDto = dto.getBankDetails();
				EmployeeBankDetailsEntity bankEntity = new EmployeeBankDetailsEntity();
				bankEntity.setBankAccountNumber(bankDto.getBankAccountNumber());
				bankEntity.setBankBranch(bankDto.getBankBranch());
				bankEntity.setBankCity(bankDto.getBankBranch());
				bankEntity.setBankDistrict(bankDto.getBankDistrict());
				bankEntity.setBankName(bankDto.getBankName());
				bankEntity.setBankState(bankDto.getBankState());
				bankEntity.setIfscCode(bankDto.getIfscCode());
				bankEntity.setCreatedBy(CREATED_BY);
				bankEntity.setCreatedTime(LocalDateTime.now());
				bankEntity.setModifiedBy(CREATED_BY);
				bankEntity.setModifiedTime(LocalDateTime.now());
				entity.setBankDetails(bankEntity);
			}
		}
		return entity;
	}

	@Override
	public boolean doesEmailExists(String email) {
		return employeeRepository.findByEmail(email) != null;
	}

	@Override
	public boolean doesMobileNoExists(String mobileNo) {
		return employeeRepository.findByMobileNumber(mobileNo) != null;
	}

	@Override
	public boolean doesUANExists(String uan) {
		return employeeRepository.findByUniversalAccountNumber(uan) != null;
	}

	@Override
	public boolean doesPANExists(String pan) {
		return employeeRepository.findByPanNumber(pan) != null;
	}

	@Override
	public List<EmployeeDto> getAllEmployyesIdAndNames() {
		List<EmployeeDto> empDtoList = new ArrayList<>();
		List<EmployeeEntity> empList = employeeRepository.findAllEmployeesByFlag(false);
		if (!empList.isEmpty()) {
			empList.forEach(emp -> {
				EmployeeDto dto = new EmployeeDto();
				dto.setEmployeeId(emp.getEmployeeId());
				dto.setFirstName(emp.getFirstName());
				dto.setLastName(emp.getLastName());
				empDtoList.add(dto);
			});
		}
		return empDtoList;
	}

	@Override
	public EmployeeDto getEmployeeById(Long id) {
		EmployeeEntity entity = employeeRepository.findById(id).get();
		return convertEntityToDto(entity);
	}

	private EmployeeDto convertEntityToDto(EmployeeEntity entity) {
		EmployeeDto empDto = new EmployeeDto();
		if (entity != null) {
			if (entity.getAddress() != null) {
				AddressEntity addressEntity = entity.getAddress();
				AddressDto addressDto = new AddressDto();
				addressDto.setAddressId(addressEntity.getAddressId());
				addressDto.setAddressLine(addressEntity.getAddressLine());
				addressDto.setCity(addressEntity.getCity());
				addressDto.setCountry(addressEntity.getCountry());
				addressDto.setPincode(addressEntity.getPincode());
				addressDto.setState(addressEntity.getState());
				empDto.setAddress(addressDto);
			}
			if (entity.getBankDetails() != null) {
				EmployeeBankDetailsEntity bankEntity = entity.getBankDetails();
				BankDetailsDto bankDto = new BankDetailsDto();
				bankDto.setBankAccountNumber(bankEntity.getBankAccountNumber());
				bankDto.setBankBranch(bankEntity.getBankBranch());
				bankDto.setBankCity(bankEntity.getBankCity());
				bankDto.setBankDetailsId(bankEntity.getBankDetailsId());
				bankDto.setBankDistrict(bankEntity.getBankBranch());
				bankDto.setBankName(bankEntity.getBankName());
				bankDto.setIfscCode(bankEntity.getIfscCode());
				bankDto.setBankState(bankEntity.getBankState());
				empDto.setBankDetails(bankDto);
			}
			if (entity.getDepartment() != null) {
				DepartmentEntity deptEntity = entity.getDepartment();
				DepartmentDto deptDto = new DepartmentDto(deptEntity.getDepartmentId(), deptEntity.getDepartmentName());
				empDto.setDepartment(deptDto);
			}
			if (entity.getJobRole() != null) {
				JobRoleEntity jobRoleEntity = entity.getJobRole();
				JobRoleDto jobDto = new JobRoleDto(jobRoleEntity.getJobroleId(), jobRoleEntity.getRoleTitle());
				empDto.setJobRole(jobDto);
			}
			empDto.setEmail(entity.getEmail());
			empDto.setEmployeeId(entity.getEmployeeId());
			empDto.setFirstName(entity.getFirstName());
			empDto.setGender(entity.getGender());
			empDto.setLastName(entity.getLastName());
			empDto.setMobileNumber(entity.getMobileNumber());
			empDto.setPanNumber(entity.getPanNumber());
			empDto.setUniversalAccountNumber(entity.getUniversalAccountNumber());
			empDto.setCtc(entity.getCtc());
			empDto.setEmployeeId(entity.getEmployeeId());
			if (entity.getDateOfBirth() != null) {
				empDto.setDay(Short.valueOf(entity.getDateOfBirth().split("-")[0]));
				empDto.setMonth(Short.valueOf(entity.getDateOfBirth().split("-")[1]));
				empDto.setYear(Short.valueOf(entity.getDateOfBirth().split("-")[2]));
			}
			LocalDate hiredDate = entity.getHiredDate();
			if (null != hiredDate) {
				empDto.setHiredDate(hiredDate.toString());
			}
		}
		return empDto;
	}

	@Override
	public ApiResponseDto updateEmployee(EmployeeDto employeeDto) {
		EmployeeEntity entity = employeeRepository.findById(employeeDto.getEmployeeId()).get();
		entity.setFirstName(employeeDto.getFirstName());
		entity.setLastName(employeeDto.getLastName());
		entity.setGender(employeeDto.getGender());
		if(employeeDto.getCtc().compareTo(new BigDecimal("100000")) > 0)	{
			entity.setCtc(employeeDto.getCtc());	
		}
		if (employeeDto.getDay() > 0 && employeeDto.getMonth() > 0 && employeeDto.getYear() > 0) {
			entity.setDateOfBirth(employeeDto.getDay() + "-" + employeeDto.getMonth() + "-" + employeeDto.getYear());
		}
		if (employeeDto.getAddress() != null) {
			AddressEntity addrsEntity = entity.getAddress();
			addrsEntity.setAddressLine(employeeDto.getAddress().getAddressLine());
			addrsEntity.setCity(employeeDto.getAddress().getCity());
			addrsEntity.setCountry(employeeDto.getAddress().getCountry());
			addrsEntity.setModifiedBy(CREATED_BY);
			addrsEntity.setModifiedTime(LocalDateTime.now());
			addrsEntity.setPincode(employeeDto.getAddress().getPincode());
			entity.setAddress(addrsEntity);
		}
		if (employeeDto.getBankDetails() != null) {
			EmployeeBankDetailsEntity bankEntity = entity.getBankDetails();
			bankEntity.setBankAccountNumber(employeeDto.getBankDetails().getBankAccountNumber());
			bankEntity.setBankBranch(employeeDto.getBankDetails().getBankBranch());
			bankEntity.setBankName(employeeDto.getBankDetails().getBankName());
			bankEntity.setIfscCode(employeeDto.getBankDetails().getIfscCode());
			bankEntity.setBankCity(employeeDto.getBankDetails().getBankCity());
			bankEntity.setBankState(employeeDto.getBankDetails().getBankState());
			bankEntity.setBankDistrict(employeeDto.getBankDetails().getBankDistrict());
		}
		if (employeeDto.getDepartment() != null) {
			entity.setDepartment(departmentRepository.findById(employeeDto.getDepartment().getDeptId()).get());
		}
		if (employeeDto.getJobRole() != null) {
			entity.setJobRole(jobRoleRepository.findById(employeeDto.getJobRole().getRoleId()).get());
		}
		employeeRepository.save(entity);
		return new ApiResponseDto("Employee details modified successfully!", HttpStatus.OK.name());
	}

	@Override
	public List<EmployeeDto> deleteAnEmployee(Long id) {
 		Integer rowsEffected = employeeRepository.updateDeleteFlag(id, true);
 		log.info("employee deleted ? ------> {}",rowsEffected);
 		return getAllEmployyesIdAndNames();
	}

	@Override
	public List<EmployeeDto> listEmployeeDetails() {
		List<EmployeeDto> entityDtoList = new ArrayList<>();
 		List<EmployeeEntity> entityList = employeeRepository.findAllEmployeesByFlag(false);
 		entityList.forEach(entity-> {
 			entity.setAddress(null);
 			entity.setBankDetails(null);
 			entity.setDepartment(null);
 			entity.setJobRole(null);
 			entityDtoList.add(convertEntityToDto(entity));
 		});
		return entityDtoList;
	}
}
