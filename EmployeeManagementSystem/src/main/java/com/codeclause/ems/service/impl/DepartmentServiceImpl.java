package com.codeclause.ems.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.codeclause.ems.dto.DepartmentDto;
import com.codeclause.ems.entity.DepartmentEntity;
import com.codeclause.ems.repository.DepartmentRepository;
import com.codeclause.ems.service.DepartmentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository deptRepo;

	@Override
	public List<DepartmentDto> fetchAllDepartments() {
		return convertToDtos(deptRepo.findAll());
	}

	private List<DepartmentDto> convertToDtos(List<DepartmentEntity> entities) {
		List<DepartmentDto> dtoList = new ArrayList<>();
		entities.forEach(entity -> {
			dtoList.add(new DepartmentDto(entity.getDepartmentId(), entity.getDepartmentName()));
		});
		return dtoList;
	}
}
