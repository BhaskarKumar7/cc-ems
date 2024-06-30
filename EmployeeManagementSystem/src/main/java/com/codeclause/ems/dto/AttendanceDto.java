package com.codeclause.ems.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceDto {
	private Long attendanceId;
	private Long employeeId;
	private Integer day;
	private Integer month;
	private Integer year;
	private Double noOfHours;
	private Integer inTimeHours;
	private Integer inTimeMinutes;
	private Integer outTimeHours;
	private Integer outTimeMinutes;
	private String dayName;
	private boolean rowDisabled;
	private String empName;
	private String empMail;
	private Byte rowIndex;
}
