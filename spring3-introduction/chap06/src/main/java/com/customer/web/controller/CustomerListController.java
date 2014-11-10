package com.customer.web.controller;

import com.customer.biz.domain.Customer;
import com.customer.biz.service.CustomerService;
import com.customer.biz.service.DataNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class CustomerListController {

    public static final Logger LOG = LoggerFactory.getLogger(CustomerListController.class);

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

    // 引数に例外オブジェクトをもらう場合はアノテーションでの指定は不要
//    @ExceptionHandler(DataNotFoundException.class)
    @ExceptionHandler
    public String handleException(DataNotFoundException e) {
        LOG.warn("Customer is not found", e);
        return "customer/notfound";
    }

    // 最も子供の例外クラスが優先で実行される
    @ExceptionHandler
    public String handleException(Exception e) {
        LOG.warn("Exception is threw", e);
        return "customer/list";
    }
}
