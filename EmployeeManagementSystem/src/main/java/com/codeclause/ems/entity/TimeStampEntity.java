package com.codeclause.ems.entity;

import java.time.LocalDateTime;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class TimeStampEntity {

	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
	private String createdBy;
	private String modifiedBy;
}
