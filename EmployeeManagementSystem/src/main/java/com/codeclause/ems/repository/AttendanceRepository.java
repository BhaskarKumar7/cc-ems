package com.codeclause.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeclause.ems.entity.AttendanceEntity;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity,Long> {

}
