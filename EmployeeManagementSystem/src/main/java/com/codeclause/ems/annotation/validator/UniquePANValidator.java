package com.codeclause.ems.annotation.validator;

import org.springframework.stereotype.Component;

import com.codeclause.ems.annotation.UniquePAN;
import com.codeclause.ems.service.EmployeeService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UniquePANValidator implements ConstraintValidator<UniquePAN, String> {

	private final EmployeeService empService;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !empService.doesPANExists(value);
	}

}
