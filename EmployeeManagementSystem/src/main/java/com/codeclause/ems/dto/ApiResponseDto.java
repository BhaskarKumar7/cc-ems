package com.codeclause.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponseDto {
	
	private String message;
	private String statusCode;
}
