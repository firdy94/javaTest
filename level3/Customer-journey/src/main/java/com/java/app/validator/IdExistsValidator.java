package com.java.app.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

public class IdExistsValidator implements ConstraintValidator<IdExists,String>{
	
	@Autowired
	private ValidatorHelper validatorHelper;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext cxt) {
		return validatorHelper.isIdExists(value, cxt);
	}
	
}