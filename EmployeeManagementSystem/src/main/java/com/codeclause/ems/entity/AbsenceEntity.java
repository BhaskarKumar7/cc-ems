package com.codeclause.ems.entity;

import java.time.LocalDateTime;

import com.codeclause.ems.constants.AppConstants;
import com.codeclause.ems.enums.AbsenceTypeEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class AbsenceEntity extends TimeStampEntity {

	@Id
	@SequenceGenerator(schema = AppConstants.EMS_SCHEMA,initialValue = 1,allocationSize = 1,
	sequenceName = "seq_absence_entity",name = "absence_entity_seq_generator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "absence_entity_seq_generator")
	private Long absenceId;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private EmployeeEntity employee;
	private LocalDateTime fromDate;
	private LocalDateTime toDate;
	private Integer noOfDays;
	@Enumerated(EnumType.STRING)
	private AbsenceTypeEnum absenceType;
}
