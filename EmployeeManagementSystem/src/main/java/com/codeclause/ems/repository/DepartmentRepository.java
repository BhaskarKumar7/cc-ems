package com.codeclause.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeclause.ems.entity.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

}
