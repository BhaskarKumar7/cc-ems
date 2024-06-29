package com.codeclause.ems.dto;

import java.math.BigDecimal;

import com.codeclause.ems.annotation.UniqueEmail;
import com.codeclause.ems.annotation.UniqueMobile;
import com.codeclause.ems.annotation.UniquePAN;
import com.codeclause.ems.annotation.UniqueUAN;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
	
	private Long employeeId;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@Min(value = 1)
	@Max(value = 31)
	private Short day;
	@Min(value = 1)
	@Max(value = 12)
	private Short month;
	@Min(value = 1920)
	private Short year;
	@NotBlank
	private String gender;
	@Email
	@UniqueEmail
	private String email;
	@UniqueMobile
	@Pattern(regexp = "^[0-9]{10}$",message = "Contact phone should be a 10 digit number")
	private String mobileNumber;
	@UniquePAN
	@Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$",message = "PAN is not valid")
	private String panNumber;
	@UniqueUAN
	@Pattern(regexp = "^[0-9]{12}$",message = "UAN should be a 12 digit number")
	private String universalAccountNumber;
	private String hiredDate;
	private DepartmentDto department;
	@Valid
	private AddressDto address;
	private JobRoleDto jobRole;
	@Valid
	private BankDetailsDto bankDetails;
	@Min(value = 100000)
	private BigDecimal ctc;
}
