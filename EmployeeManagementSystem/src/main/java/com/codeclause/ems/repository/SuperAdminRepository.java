package com.codeclause.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeclause.ems.entity.SuperAdminEntity;

public interface SuperAdminRepository extends JpaRepository<SuperAdminEntity, Long>{

	SuperAdminEntity findByEmail(String email);
}
