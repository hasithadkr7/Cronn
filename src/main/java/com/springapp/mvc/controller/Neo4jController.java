package com.springapp.mvc.controller;

import com.springapp.mvc.model.neo4j.Customer;
import com.springapp.mvc.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scala.collection.*;
import scala.collection.immutable.List;

import java.lang.Iterable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
    @Transactional public void customerDetails(@PathVariable("message") String message){
        getCustomersByIdNumber(customerService,"74578856v");
        executeQuery(customerService,"BUDDHIKA");
//        getAllCustomers(customerService);
//        if(message.equals("create")){
//            Customer customer = createCustomer();
//            createCustomer(customerService, customer);
//            System.out.println("Customer "+customer.getFirst_name()+" created successfully.");
//        }
//        else if(message.equals("findAll")){
//            getAllCustomers(customerService);
//        }
//        else {
//            System.out.println("Please set the action.");
//        }
    }

    private Customer createCustomer(){
        Customer customer = new Customer();
        customer.setId_number("74578856v");
        customer.setId_type("NIC");
        customer.setTitle("MR");
        customer.setFirst_name("BUDDHIKA");
        customer.setLast_name("JAYAWARDHANA");
        customer.setDob("06/10/1974");
        customer.setProfession("ENGINEER");
        customer.setGender("MALE");
        customer.setStatus("A");
        customer.setLoyalty("CVB");
        customer.setPrimary_number("771597534");
        customer.setAccount_numbers("7779638521,778521478,771122334");
        customer.setAddress_line1("NO 23/2");
        customer.setAddress_line2("ROYAL STREET");
        customer.setAddress_line3("DEHIWALA");
        return customer;
    }

    private Customer createCustomer(CustomerService service, Customer customer){
        return service.insertCustomer(customer);
    }

    @Transactional
    private Customer getOneCustomerById(CustomerService service,Long id){
        return service.findById(id);
    }

    @Transactional
    private void getAllCustomers(CustomerService service){
        Iterable<Customer> c = service.findAll();
        System.out.println("Customer count: "+service.count());
        Result<Customer> result = service.findAll();
        Iterator<Customer> iterator = result.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Transactional
    private void getCustomersByIdNumber(CustomerService service,String id_number){
        Result<Customer> result = service.findCustomer(id_number);
        Iterator<Customer> iterator = result.iterator();
        while(iterator.hasNext()){
            Customer customer = iterator.next();
            System.out.println("Customer name : "+customer.getFirst_name());
            System.out.println("Customer ID : "+customer.getId());
//            System.out.println("his old profession :"+customer.getProfession());
//            System.out.println("Customer changing his profession...");
//            customer.setProfession("POLITICIAN");
//            service.insertCustomer(customer);
        }
    }

    @Transactional
    private void executeQuery(CustomerService service,String first_name){
        Customer customer = service.executeQuery(first_name);
        System.out.println("Customized query result: "+customer.getLast_name());
//        Result<Customer> result = service.executeQuery(query);
//        Iterator<Customer> iterator = result.iterator();
//        while(iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
    }
}