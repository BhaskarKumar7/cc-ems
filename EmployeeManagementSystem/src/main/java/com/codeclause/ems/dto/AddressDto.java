package com.codeclause.ems.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

	private Long addressId;
	@NotBlank
	private String country;
	@NotBlank
	private String state;
	@NotBlank
	private String city;
	@NotBlank
	private String pincode;
	@NotBlank
	private String addressLine;

}
