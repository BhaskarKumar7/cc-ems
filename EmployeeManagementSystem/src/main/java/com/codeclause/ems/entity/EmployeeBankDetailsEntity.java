package com.codeclause.ems.entity;

import com.codeclause.ems.constants.AppConstants;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = AppConstants.EMS_SCHEMA)
@Getter
@Setter
public class EmployeeBankDetailsEntity extends TimeStampEntity {

	@Id
	@SequenceGenerator(schema = AppConstants.EMS_SCHEMA,initialValue = 1,allocationSize = 1,
	sequenceName = "seq_emp_bank_details_entity",name = "emp_bank_details_entity_seq_generator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "emp_bank_details_entity_seq_generator")
	private Long bankDetailsId;
	private String bankName;
	private String ifscCode;
	private String bankBranch;
	private String bankCity;
	private String bankState;
	private String bankDistrict;
	private String bankAccountNumber;
}
