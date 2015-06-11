package com.springapp.mvc.service;

import com.springapp.mvc.model.neo4j.Customer;
import org.springframework.data.neo4j.conversion.Result;

/**
 * Created by Hasitha on 6/5/2015.
 */
public interface CustomerService{
    public Customer insertCustomer(Customer customer);
    Customer findById(long id);
    Result<Customer> findAll();
}