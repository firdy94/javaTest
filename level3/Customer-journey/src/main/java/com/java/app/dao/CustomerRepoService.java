package com.java.app.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.app.dao.entity.Customer;

@Service
public class CustomerRepoService {
	
	@Autowired
	private CustomerRepository mCustomerRepository;
	
	public Optional<Customer> getCustomerById(String id) {
		return mCustomerRepository.findById(Long.parseLong(id));
	}
	
	public Optional<Customer> addOrUpdateCustomer(Customer customer) {
		return Optional.ofNullable(mCustomerRepository.save(customer));
	}
	
	public void deleteCustomer(String id) {
			mCustomerRepository.deleteById(Long.parseLong(id));
	}
}

