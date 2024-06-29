package com.codeclause.ems.service;

import java.util.List;

import com.codeclause.ems.dto.JobRoleDto;

public interface JobRoleService {

	public List<JobRoleDto> findRolesByDeptName(String deptName);
	public List<JobRoleDto> findRolesByDeptId(Long id);
	public List<JobRoleDto> findAllRoles();
}
