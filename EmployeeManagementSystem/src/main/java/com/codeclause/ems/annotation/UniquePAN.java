package com.codeclause.ems.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.codeclause.ems.annotation.validator.UniquePANValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniquePANValidator.class)
public @interface UniquePAN {
	String message() default "PAN already exists";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
