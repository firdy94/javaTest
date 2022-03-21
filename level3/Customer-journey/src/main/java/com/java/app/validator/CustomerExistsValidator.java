package com.java.app.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.java.app.dao.entity.Customer;

public class CustomerExistsValidator implements ConstraintValidator<CustomerExists,Customer>{
	
	@Autowired
	private ValidatorHelper validatorHelper;
	
	@Override
	public boolean isValid(Customer value, ConstraintValidatorContext cxt) {
		return validatorHelper.isCustomerExists(value, cxt);
	}
	
}