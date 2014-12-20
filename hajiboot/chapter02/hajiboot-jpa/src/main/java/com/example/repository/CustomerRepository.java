package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	@Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")
	List<Customer> findAllOrderByName();
}
