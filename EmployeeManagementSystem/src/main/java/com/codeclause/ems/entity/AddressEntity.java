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
public class AddressEntity extends TimeStampEntity {

	@Id
	@SequenceGenerator(schema = AppConstants.EMS_SCHEMA,initialValue = 1,allocationSize = 1,
	sequenceName = "seq_address_entity",name = "address_entity_seq_generator")
	@GeneratedValue( strategy = GenerationType.SEQUENCE,generator = "address_entity_seq_generator")
	private Long addressId;
	private String country;
	private String state;
	private String city;
	private String pincode;
	private String addressLine;
	
}
