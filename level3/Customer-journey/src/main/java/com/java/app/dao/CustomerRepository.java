//package com.java.app.dao;
//
//import java.util.List;
//import org.springframework.data.repository.CrudRepository;
//
//public interface CustomerRepository extends CrudRepository<Customer, Long> {
//	List<Customer> findByLastName(String lastName);
//
//	Customer findById(long id);
//}

package com.java.app.dao;

import java.util.List;
//import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.app.dao.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	List<Customer> findByLastName(String lastName);

//	Customer findById(long id);
}
