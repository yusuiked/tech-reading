package com.customer.web.controller;

import com.customer.biz.domain.Customer;
import com.customer.biz.service.CustomerService;
import com.customer.biz.service.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class CustomerListController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customer", method = GET)
    public String showAllCustomers(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customer/list";
    }

    @RequestMapping(value = "/", method = GET)
    public String home() {
        return "forward:/customer";
    }

    @RequestMapping(value = "/customer/{customerId}", method = GET)
    public String showCustomerDetail(@PathVariable int customerId, Model model) throws DataNotFoundException {
        Customer customer = customerService.findById(customerId);
        model.addAttribute("customer", customer);
        return "customer/detail";
    }
}
