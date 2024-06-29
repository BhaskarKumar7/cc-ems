package com.codeclause.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeclause.ems.entity.JobRoleEntity;

public interface JobRoleRepository extends JpaRepository<JobRoleEntity, Long> {

	List<JobRoleEntity> findByDepartment_DepartmentName(String departmentName);
	List<JobRoleEntity> findByDepartment_DepartmentId(Long departmentId);
}
