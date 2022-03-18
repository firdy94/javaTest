package com.java.controller;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.dao.Customer;
import com.java.dao.CustomerRepoService;;

@RestController
@RequestMapping("/1/customer")
@Validated
public class CustomerController {
	
	@Autowired
	CustomerRepoService customerRepoSvc;

	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") @NotBlank String pId) {
		
		Optional<Customer> result = customerRepoSvc.getCustomerById(pId);
		if (result.isPresent()) {
			return ResponseEntity.ok(result.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping(value = "/")
	@ResponseBody
	public ResponseEntity<Customer> addOrUpdateCustomer(Customer pCustomer) {
		
		Optional<Customer> customer = customerRepoSvc.addOrUpdateCustomer(pCustomer);
		
		if (customer.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(customer.get());
		// complete this method
	}

}
