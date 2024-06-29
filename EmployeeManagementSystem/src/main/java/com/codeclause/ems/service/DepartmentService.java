package com.codeclause.ems.service;

import java.util.List;

import com.codeclause.ems.dto.DepartmentDto;

public interface DepartmentService {
	
	public List<DepartmentDto> fetchAllDepartments();
}
