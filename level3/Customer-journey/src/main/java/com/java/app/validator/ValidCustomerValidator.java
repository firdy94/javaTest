package com.java.app.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.java.app.dao.entity.Customer;

public class ValidCustomerValidator implements ConstraintValidator<ValidCustomer,Object>{
	
	@Autowired
	private ValidatorHelper validatorHelper;
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext cxt) {
		return validatorHelper.isCustomerCorrect((Customer) value, cxt);
	}
	
}