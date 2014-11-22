package sample.customer.biz.service;

import java.util.List;

import sample.customer.biz.domain.Customer;

public interface CustomerService {
    List<Customer> findAll();

    Customer findById(int id) throws DataNotFoundException;

    Customer register(Customer customer);

    void update(Customer customer) throws DataNotFoundException;

    void delete(int id) throws DataNotFoundException;

    boolean isFreeEmailCustomer(Customer customer);
}
