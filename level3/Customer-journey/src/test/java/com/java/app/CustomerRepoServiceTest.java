package com.java.app;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;




import com.java.app.dao.entity.Customer;
import com.java.app.dao.CustomerRepoService;

@SpringBootTest(classes = AccessingDataJpaApplication.class)
@Sql(scripts="classpath:schema.sql", executionPhase=BEFORE_TEST_METHOD)
@Sql(scripts="classpath:data.sql", executionPhase=BEFORE_TEST_METHOD)
class CustomerRepoServiceTest{

	
	@Autowired
	private CustomerRepoService customerRepoSvc;

	@Test
	void testCustomer() {
		Customer customerExpected = new Customer("1", "John", "Kennedy");
		Customer customerGet = customerRepoSvc.getCustomerById("1").get();
		assertEquals(customerExpected.getId(), customerGet.getId());
		assertEquals(customerExpected.getFirstName(), customerGet.getFirstName());
		assertEquals(customerExpected.getLastName(), customerGet.getLastName());

		
		
		Optional<Customer> customerNeg =customerRepoSvc.getCustomerById("100");
		assertEquals(Optional.empty(), customerNeg);

				
		assertThrows(NumberFormatException.class, ()-> customerRepoSvc.getCustomerById(null));
	}
	
	
	
	
	@Test
	void testAddOrUpdateCustomer() {
		//updateTests
		Customer customerUpdatePos = new Customer("2", "Jonathan", "Hamfield");
		customerRepoSvc.addOrUpdateCustomer(customerUpdatePos);		
		Customer customerUpdated = customerRepoSvc.getCustomerById("2").get();
		assertEquals(customerUpdatePos.getId(), customerUpdated.getId());
		assertEquals(customerUpdatePos.getFirstName(), customerUpdated.getFirstName());
		assertEquals(customerUpdatePos.getLastName(), customerUpdated.getLastName());

				
		//add tests
		Customer customerAddPos = new Customer("Jonathan", "Hamsfield");
		customerRepoSvc.addOrUpdateCustomer(customerAddPos);		
		Customer customerAdded = customerRepoSvc.getCustomerById("7").get();
		assertEquals("7", customerAdded.getId().toString());
		assertEquals(customerAddPos.getFirstName(), customerAdded.getFirstName());
		assertEquals(customerAddPos.getLastName(), customerAdded.getLastName());


				
		assertThrows(InvalidDataAccessApiUsageException.class, ()-> customerRepoSvc.addOrUpdateCustomer(null));
	}
	
	@Test
	void testDeleteCustomer(){
		customerRepoSvc.deleteCustomer("1");
		Optional<Customer> customer =customerRepoSvc.getCustomerById("1");
		assertEquals(Optional.empty(), customer);
		assertThrows(EmptyResultDataAccessException.class, ()->customerRepoSvc.deleteCustomer("100"));
		assertThrows(IllegalArgumentException.class, ()-> customerRepoSvc.deleteCustomer(null));
	}
 }