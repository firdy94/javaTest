//package com.java.app.dao;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class CustomerRepoService {
//
//	@Autowired
//	private CustomerRepository mCustomerRepository;
//	
//	public Optional<Customer> getCustomerById(String id) {
//		return Optional.ofNullable(mCustomerRepository.findById(Long.parseLong(id)));
//	}
//	
//	public Optional<Customer> addOrUpdateCustomer(Customer customer) {
//		if(customer.getId()!=null) {
//			if(mCustomerRepository.existsById(customer.getId())) {
//				mCustomerRepository.deleteById(customer.getId());
//				return Optional.ofNullable(
//						mCustomerRepository.save(customer));
//			}
//			return Optional.empty();
////			Customer customerWithoutId = new Customer(customer.getFirstName(), customer.getLastName());
////			mCustomerRepository.save(customerWithoutId);
//		}
//		mCustomerRepository.save(customer);
//		return Optional.ofNullable(mCustomerRepository.save(customer));
//
////		return Optional.ofNullable(customer);
//
//	}
//}


package com.java.app.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerRepoService {

	@Autowired
	private CustomerRepository mCustomerRepository;
	
	public Optional<Customer> getCustomerById(String id) {
		return mCustomerRepository.findById(Long.parseLong(id));
	}
	
	public Optional<Customer> addOrUpdateCustomer(Customer customer) {
		if(customer.getId()!=null) {
			if(mCustomerRepository.existsById(customer.getId())) {
				return Optional.ofNullable(
						mCustomerRepository.save(customer));
			}
			return Optional.empty();
//			Customer customerWithoutId = new Customer(customer.getFirstName(), customer.getLastName());
//			mCustomerRepository.save(customerWithoutId);
		}
		System.out.println(customer.toString());
		Customer customerWithoutId = new Customer(customer.getFirstName(), customer.getLastName());
		System.out.println(customerWithoutId.toString());
//		mCustomerRepository.save(customerWithoutId);
		return Optional.ofNullable(mCustomerRepository.save(customerWithoutId));

//		return Optional.ofNullable(customer);

	}
	
	public boolean deleteCustomer(String id) {
		if (mCustomerRepository.existsById(Long.parseLong(id))) {
			mCustomerRepository.deleteById(Long.parseLong(id));
			return true;
		}
		return false;
	}
}

