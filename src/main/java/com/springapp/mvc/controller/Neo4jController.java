package com.springapp.mvc.controller;

import com.springapp.mvc.model.neo4j.Customer;
import com.springapp.mvc.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Hasitha on 6/10/2015.
 */

@Controller
public class Neo4jController{

    @Qualifier("customerService")
    @Autowired
    private CustomerService customerService;

    private static final Logger logger = LoggerFactory.getLogger(Neo4jController.class);
    @RequestMapping(value = "neo4j/{message:.+}", method = RequestMethod.GET)
    public void customerDetails(){
        Customer customer = createCustomer();
        createCustomer(customerService, customer);
        System.out.println("Customer created successfully.");
    }

    public Customer createCustomer(){
        Customer customer = new Customer();
        customer.setId_number("89123456v");
        customer.setId_type("NIC");
        customer.setTitle("MR");
        customer.setFirst_name("SAMAN");
        customer.setLast_name("KUMARA");
        customer.setDob("12/03/1881");
        customer.setProfession("DOCTOR");
        customer.setGender("MALE");
        customer.setStatus("A");
        customer.setLoyalty("CVG");
        customer.setPrimary_number("776456804");
        customer.setAccount_numbers("776456806,776456805");
        customer.setAddress_line1("NO 273/2");
        customer.setAddress_line2("NEDIMALA");
        customer.setAddress_line3("DEHIWALA");
        return customer;
    }

    private Customer createCustomer(CustomerService service, Customer customer){
        return service.insertCustomer(customer);
    }

}