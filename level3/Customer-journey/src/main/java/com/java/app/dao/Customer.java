//package com.java.app.dao;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//@Entity
//public class Customer {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//	private String firstName;
//	private String lastName;
//
//	protected Customer() {
//	}
//
//	public Customer(String firstName, String lastName) {
//		this.firstName = firstName;
//		this.lastName = lastName;
//	}
//
//	@Override
//	public String toString() {
//		return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//}




package com.java.app.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "customers")
public class Customer {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	@Column(name="firstname")
	private String firstName;
	@Column(name="lastname")
	private String lastName;

	protected Customer() {
	}

	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public Customer(String id, String firstName, String lastName) {
		this.id=Long.parseLong(id);
		this.firstName = firstName;
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}

