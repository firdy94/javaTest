package com.java.app.controller;


import java.lang.invoke.MethodHandles;
import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.app.dao.entity.Customer;
import com.java.app.validator.CustomerExists;
import com.java.app.validator.IdExists;
import com.java.app.validator.ValidCustomer;
import com.java.app.dao.CustomerRepoService;

@RestController
@RequestMapping(path= "/1/customer", produces=MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CustomerController {
	private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	
	@Autowired
	CustomerRepoService customerRepoSvc;
	
	@Autowired
	ObjectMapper mapper;

	
//	@GetMapping 
//	public ResponseEntity<String> testCall(){
//		return ResponseEntity.ok("Hello");
//	}

	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") @NotBlank @IdExists String pId) {
		logger.info("Id entered>>>>>>>: {}",pId);		
		Optional<Customer> result = customerRepoSvc.getCustomerById(pId);
		return ResponseEntity.ok(result.get());
	}

	@PutMapping(value = "/")
	@ResponseBody
	public ResponseEntity<Customer> updateCustomer(@RequestBody @CustomerExists Customer pCustomer) {		
		Optional<Customer> customer = customerRepoSvc.addOrUpdateCustomer(pCustomer);
		logger.info("Customer updated >>>>>>>: {}",pCustomer.getId().toString());
		return ResponseEntity.ok(customer.get());
	}
	
	@PostMapping(value = "/")
	@ResponseBody
	public ResponseEntity<Customer> addCustomer(@RequestBody @ValidCustomer Customer pCustomer) {		
		Optional<Customer> customer = customerRepoSvc.addOrUpdateCustomer(pCustomer);
		logger.info("Customer added >>>>>>>: {}",pCustomer.getId().toString());
		return ResponseEntity.ok(customer.get());
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") @NotBlank @IdExists String pId){
		customerRepoSvc.deleteCustomer(pId);
		logger.info("Customer added >>>>>>>: {}",pId);
		return ResponseEntity.ok("Customer record deleted");
	}

}

