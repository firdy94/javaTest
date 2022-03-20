//package com.java.app.controller;
//
//import java.util.Optional;
//
//import javax.validation.constraints.NotBlank;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.java.dao.Customer;
//import com.java.dao.CustomerRepoService;;
//
//@RestController
//@RequestMapping("/1/customer")
//@Validated
//public class CustomerController {
//	
//	@Autowired
//	CustomerRepoService customerRepoSvc;
//
//	@GetMapping(value = "/{id}")
//	@ResponseBody
//	public ResponseEntity<Customer> getCustomer(@PathVariable("id") @NotBlank String pId) {
//		
//		Optional<Customer> result = customerRepoSvc.getCustomerById(pId);
//		if (result.isPresent()) {
//			return ResponseEntity.ok(result.get());
//		}
//		return ResponseEntity.notFound().build();
//	}
//
//	@PutMapping(value = "/")
//	@ResponseBody
//	public ResponseEntity<Customer> addOrUpdateCustomer(Customer pCustomer) {
//		
//		Optional<Customer> customer = customerRepoSvc.addOrUpdateCustomer(pCustomer);
//		
//		if (customer.isEmpty()) {
//			return ResponseEntity.badRequest().build();
//		}
//		return ResponseEntity.ok(customer.get());
//		// complete this method
//	}
//
//}




package com.java.app.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

import javax.validation.constraints.NotBlank;

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
import com.java.app.dao.Customer;
import com.java.app.dao.CustomerRepoService;

@RestController
@RequestMapping(path= "/1/customer", produces=MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CustomerController {
	
	@Autowired
	CustomerRepoService customerRepoSvc;
	
//	@GetMapping 
//	public ResponseEntity<String> testCall(){
//		return ResponseEntity.ok("Hello");
//	}

	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<com.java.app.dao.Customer> getCustomer(@PathVariable("id") @NotBlank String pId) {
		System.out.println(pId);
		
		Optional<Customer> result = customerRepoSvc.getCustomerById(pId);
		
		if (result.isPresent()) {
			return ResponseEntity.ok(result.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping(value = "/")
	@ResponseBody
	public ResponseEntity<Customer> updateCustomer(@RequestBody String jsonResp) {
		ObjectMapper mapper = new ObjectMapper();
		
		
		Customer pCustomer;
		try {
			pCustomer = mapper.readValue( new ByteArrayInputStream(jsonResp.getBytes()), Customer.class);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		
		System.out.println(pCustomer.toString());
		
		Optional<Customer> customer = customerRepoSvc.addOrUpdateCustomer(pCustomer);
		
		if (customer.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(customer.get());
	}
	
	@PostMapping(value = "/")
	@ResponseBody
	public ResponseEntity<Customer> addCustomer(@RequestBody String jsonResp) {
		ObjectMapper mapper = new ObjectMapper();
		
		Customer pCustomer;
		try {
			pCustomer = mapper.readValue( new ByteArrayInputStream(jsonResp.getBytes()), Customer.class);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		
		System.out.println(pCustomer.toString());
		
		Optional<Customer> customer = customerRepoSvc.addOrUpdateCustomer(pCustomer);
		
		if (customer.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(customer.get());
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") @NotBlank String pId){
		
		boolean result = customerRepoSvc.deleteCustomer(pId);
		
		if (result) {
			return ResponseEntity.ok("Customer record deleted");
		}
		return ResponseEntity.badRequest().build();
		
		
		
	}

}

