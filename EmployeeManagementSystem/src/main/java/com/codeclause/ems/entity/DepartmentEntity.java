package com.codeclause.ems.entity;

import java.util.List;

import com.codeclause.ems.constants.AppConstants;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = AppConstants.EMS_SCHEMA)
@Getter
@Setter
public class DepartmentEntity extends TimeStampEntity {

	@Id
	@SequenceGenerator(schema = AppConstants.EMS_SCHEMA,initialValue = 1,allocationSize = 1,
	sequenceName = "seq_department_entity",name = "department_entity_seq_generator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "department_entity_seq_generator")
	private Long departmentId;
	private String departmentName;
	@OneToMany(mappedBy = "department",fetch = FetchType.LAZY)
	private List<JobRoleEntity> jobRoles;
}
