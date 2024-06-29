package com.codeclause.ems.entity;

import com.codeclause.ems.constants.AppConstants;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = AppConstants.EMS_SCHEMA)
@Getter
@Setter
public class JobRoleEntity extends TimeStampEntity {

	@Id
	@SequenceGenerator(schema = AppConstants.EMS_SCHEMA,initialValue = 1,allocationSize = 1,
	sequenceName = "seq_jobrole_entity",name = "jobrole_entity_seq_generator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "jobrole_entity_seq_generator")
	private Long jobroleId;
	private String roleTitle;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id",nullable = false)
	private DepartmentEntity department;
}
