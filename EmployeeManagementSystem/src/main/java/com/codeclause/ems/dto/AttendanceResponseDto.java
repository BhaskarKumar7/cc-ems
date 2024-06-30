package com.codeclause.ems.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceResponseDto {
	private String message;
	private String statusCode;
	private Long attendanceId;
	private Byte index;
	private Double noOfHours;
}
