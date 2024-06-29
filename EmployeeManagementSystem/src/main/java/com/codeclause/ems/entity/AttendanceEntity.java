package com.codeclause.ems.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.codeclause.ems.constants.AppConstants;

import jakarta.persistence.CascadeType;
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
public class AttendanceEntity extends TimeStampEntity {

	@Id
	@SequenceGenerator(schema = AppConstants.EMS_SCHEMA,initialValue = 1,allocationSize = 1,
	sequenceName = "seq_attendance_entity",name = "attendance_entity_seq_generator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "attendance_entity_seq_generator")
	private Long attendanceId;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private EmployeeEntity employee;
	private LocalDate selectedDate;
	private Integer noOfHours;
	private LocalDateTime inTime;
	private LocalDateTime outTime;
}
