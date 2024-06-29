package com.codeclause.ems.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.codeclause.ems.dto.JobRoleDto;
import com.codeclause.ems.entity.JobRoleEntity;
import com.codeclause.ems.repository.JobRoleRepository;
import com.codeclause.ems.service.JobRoleService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JobRoleServiceImpl implements JobRoleService {

	private final JobRoleRepository jobRoleRepository;

	@Override
	public List<JobRoleDto> findRolesByDeptName(String deptName) {
		return convertEntitiesToDto(jobRoleRepository.findByDepartment_DepartmentName(deptName));
	}

	@Override
	public List<JobRoleDto> findRolesByDeptId(Long id) {
		return convertEntitiesToDto(jobRoleRepository.findByDepartment_DepartmentId(id));
	}

	private List<JobRoleDto> convertEntitiesToDto(List<JobRoleEntity> entities) {
		List<JobRoleDto> rolesList = new ArrayList<>();
		entities.forEach(entity -> {
			rolesList.add(new JobRoleDto(entity.getJobroleId(), entity.getRoleTitle()));
		});
		return rolesList;
	}

	@Override
	public List<JobRoleDto> findAllRoles() {
		return convertEntitiesToDto(jobRoleRepository.findAll());
	}

}
