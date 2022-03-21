package com.java.app.validator;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.app.dao.CustomerRepoService;
import com.java.app.dao.entity.Customer;

@Component
public class ValidatorHelper{
	
	private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Autowired
	private CustomerRepoService customerRepoSvc;
	
	
	public boolean isIdExists(String id, ConstraintValidatorContext cxt) {
		
		
		Optional<Customer> customer = customerRepoSvc.getCustomerById(id);
		
		if(customer.isEmpty()) {
			logger.error("Id {} does not exist", id);
			cxt.disableDefaultConstraintViolation();
			cxt.buildConstraintViolationWithTemplate("Id %s does not exist".formatted(id)).addConstraintViolation();
			return false;
		}
		return true;
		
		
	}
	
	public boolean isCustomerExists(Customer customer, ConstraintValidatorContext cxt ) {
		
		if (customer.getId()==null) {
			logger.error("Id cannot be null");
			cxt.disableDefaultConstraintViolation();
			cxt.buildConstraintViolationWithTemplate("Id cannot be null").addConstraintViolation();
			return false;
		}
		
		Optional<Customer> customerExisting = customerRepoSvc.getCustomerById(customer.getId().toString());
		
		if(customerExisting.isEmpty()) {
			logger.error("Id {} does not exist", customer.getId().toString());
			cxt.disableDefaultConstraintViolation();
			cxt.buildConstraintViolationWithTemplate("Id %s does not exist".formatted(customer.getId().toString())).addConstraintViolation();
			return false;
		}
		else if ((customer.getFirstName()==null) || (customer.getLastName()==null)) {
			logger.error("Name fields cannot be null");
			cxt.disableDefaultConstraintViolation();
			cxt.buildConstraintViolationWithTemplate("Name fields not accepted").addConstraintViolation();
			return false;	
		}
		else if((customer.getFirstName().length()<3) || (customer.getLastName().length()<3)) {
			logger.error("Name fields firstName: {} and lastName: {} cannot be less than 3 characters", customer.getFirstName(), customer.getLastName());
			cxt.disableDefaultConstraintViolation();
			cxt.buildConstraintViolationWithTemplate("FirstName: %s, LastName: %s is too short".formatted(customer.getFirstName(),customer.getLastName())).addConstraintViolation();
			return false;
		}
		return true;
		
	}
	
	public boolean isCustomerValid(Customer customer,  ConstraintValidatorContext cxt) {
		if(customer.getId()!=null) {
			logger.error("Id field {} must be null", customer.getId().toString());
			cxt.disableDefaultConstraintViolation();
			cxt.buildConstraintViolationWithTemplate("Id %s is not accepted".formatted(customer.getId().toString())).addConstraintViolation();
			return false;
		}
		else if ((customer.getFirstName()==null) || (customer.getLastName()==null)) {
			logger.error("Name fields cannot be null");
			cxt.disableDefaultConstraintViolation();
			cxt.buildConstraintViolationWithTemplate("Name fields not accepted").addConstraintViolation();
			return false;	
		}
		else if((customer.getFirstName().length()<3) || (customer.getLastName().length()<3)) {
			logger.error("Name fields firstName: {} and lastName: {} cannot be less than 3 characters", customer.getFirstName(), customer.getLastName());
			cxt.disableDefaultConstraintViolation();
			cxt.buildConstraintViolationWithTemplate("FirstName: %s, LastName: %s is too short".formatted(customer.getFirstName(),customer.getLastName())).addConstraintViolation();
			return false;
		}
		return true;
	}
	

	
}