package com.codeclause.ems.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbsenceDto {
	private Long absenceId;
	private Long employeeId;
	private Short fromYear;
	private Short fromDay;
	private Short fromMonth;
	private Short toYear;
	private Short toDay;
	private Short toMonth;
	private Integer noOfDays;
	private String absenceType;
}
