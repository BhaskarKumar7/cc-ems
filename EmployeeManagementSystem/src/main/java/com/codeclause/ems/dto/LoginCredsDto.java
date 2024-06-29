package com.codeclause.ems.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginCredsDto {

	@NotBlank
	@Email
	private String emailId;
	@NotBlank
	private String password;
}
