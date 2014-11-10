package com.customer.biz.service;

import com.customer.biz.domain.Customer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class MockCustomerService implements CustomerService {

    private Map<Integer, Customer> customerMap = new LinkedHashMap<>();

    private int nextId = 1;

    private boolean exists(int id) {
        return customerMap.containsKey(id);
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer findById(int id) throws DataNotFoundException {
        if (!exists(id)) {
            throw new DataNotFoundException();
        }
        return customerMap.get(id);
    }

    @Override
    public Customer register(Customer customer) {
        customer.setId(nextId++);
        customerMap.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public void update(Customer customer) throws DataNotFoundException {
        if (!exists(customer.getId())) {
            throw new DataNotFoundException();
        }
        customerMap.put(customer.getId(), customer);

    }

    @Override
    public void delete(int id) throws DataNotFoundException {
        if (!exists(id)) {
            throw new DataNotFoundException();
        }
        customerMap.remove(id);

    }

    @Override
    public boolean isFreeEmailCustomer(Customer customer) {
        return customer.isFreeEmail();
    }

    @PostConstruct
    public void initCustomer() {
        nextId = 1;
        register(new Customer("太郎", "東京都新宿区", "taro@aa.bb.cc"));
        register(new Customer("次郎", "東京都豊島区", "jiro@aa.bb.cc"));
        register(new Customer("三郎", "東京都板橋区", "sabu@aa.bb.cc"));
    }
}
