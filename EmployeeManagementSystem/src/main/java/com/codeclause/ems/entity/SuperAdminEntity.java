package com.codeclause.ems.entity;

import com.codeclause.ems.constants.AppConstants;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = AppConstants.EMS_SCHEMA)
@Getter
@Setter
public class SuperAdminEntity {

	@Id
	private Long superAdminId;
	private String email;
	private String password;
	private String name;
}
