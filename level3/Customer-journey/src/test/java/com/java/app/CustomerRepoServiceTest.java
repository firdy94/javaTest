package com.java.app;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



import com.java.app.dao.Customer;
import com.java.app.dao.CustomerRepoService;

class CustomerRepoServiceTest{

	CustomerRepoService customerRepoSvc = new CustomerRepoService();

	@Test
	void testGetCustomer() {
		Optional<Customer> customerPos =customerRepoSvc.getCustomerById("1");
		Optional<Customer> customerNeg =customerRepoSvc.getCustomerById("100");
		assertEquals(new Customer("1", "John", "Kennedy"), customerPos.get());
		assertEquals(Optional.empty(), customerNeg.get());
		assertThrows(IllegalArgumentException.class, ()-> customerRepoSvc.getCustomerById(null));
		
	}
	@Test
	void testAddOrUpdateCustomer() {
		//updateTests
		Customer customerUpdatePos = new Customer("1", "Jonathan", "Hamfield");
		customerRepoSvc.addOrUpdateCustomer(customerUpdatePos);		
		Customer customerUpdated = customerRepoSvc.getCustomerById("1").get();
		assertEquals(customerUpdatePos, customerUpdated);
		
		//Customer customerUpdateNeg = new Customer("100", "Jonathan", "Hamfield");
		//assert TODO write negative test for index out of bounds updating 
		
		//add tests
		Customer customerAddPos = new Customer("Jonathan", "Hamfield");
		customerRepoSvc.addOrUpdateCustomer(customerAddPos);		
		Customer customerAdded = customerRepoSvc.getCustomerById("7").get();
		assertEquals(customerAddPos, customerAdded);
		
		//TODO add negative test	
		
		assertThrows(IllegalArgumentException.class, ()-> customerRepoSvc.addOrUpdateCustomer(null));
	}
	
	@Test
	void testDeleteCustomer(){
		assertEquals(true, customerRepoSvc.deleteCustomer("1"));
		assertEquals(false, customerRepoSvc.deleteCustomer("100"));
		assertThrows(IllegalArgumentException.class, ()-> customerRepoSvc.deleteCustomer(null));

		


		
	}
 }