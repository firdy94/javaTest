package com.java.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

public class CustomerRepoService {

	@Autowired
	private CustomerRepository mCustomerRepository;
	
	public Optional<Customer> getCustomerById(String id) {
		return Optional.ofNullable(mCustomerRepository.findById(Long.parseLong(id)));
	}
	
	public Optional<Customer> addOrUpdateCustomer(Customer customer) {
		if(customer.getId()!=null) {
			if(mCustomerRepository.existsById(customer.getId())) {
				mCustomerRepository.deleteById(customer.getId());
				return Optional.ofNullable(
						mCustomerRepository.save(customer));
			}
			return Optional.empty();
//			Customer customerWithoutId = new Customer(customer.getFirstName(), customer.getLastName());
//			mCustomerRepository.save(customerWithoutId);
		}
		mCustomerRepository.save(customer);
		return Optional.ofNullable(mCustomerRepository.save(customer));

//		return Optional.ofNullable(customer);

	}
}
