package com.codeclause.ems.service;

import com.codeclause.ems.dto.AbsenceDto;
import com.codeclause.ems.dto.ApiResponseDto;

public interface AbsenceService {
	public ApiResponseDto saveAbsence(AbsenceDto absenceDto);
}
