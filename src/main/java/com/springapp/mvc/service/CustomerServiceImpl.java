package com.springapp.mvc.service;

import com.springapp.mvc.dao.CustomerRepository;
import com.springapp.mvc.model.neo4j.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.stereotype.Service;

/**
 * Created by Hasitha on 6/5/2015.
 */

@Service("customerService")
public class  CustomerServiceImpl implements CustomerService {

    @Qualifier("customerRepository")
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer insertCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    @Override
    public Customer findById(long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public Result<Customer> findAll() {
        return customerRepository.findAll();
    }

}