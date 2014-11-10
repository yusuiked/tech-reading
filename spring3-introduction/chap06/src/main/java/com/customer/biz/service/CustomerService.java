package com.customer.biz.service;

import com.customer.biz.domain.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    Customer findById(int id) throws DataNotFoundException;

    Customer register(Customer customer);

    void update(Customer customer) throws DataNotFoundException;

    void delete(int id) throws DataNotFoundException;

    boolean isFreeEmailCustomer(Customer customer);
}
