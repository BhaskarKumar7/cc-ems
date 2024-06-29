package com.codeclause.ems.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankDetailsDto {
	
	private Long bankDetailsId;
	@NotBlank
	private String bankName;
	@NotBlank
	private String ifscCode;
	@NotBlank
	private String bankBranch;
	@NotBlank
	private String bankCity;
	@NotBlank
	private String bankState;
	@NotBlank
	private String bankDistrict;
	@NotBlank
	private String bankAccountNumber;
}
