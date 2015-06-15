package com.springapp.mvc.service;

import com.springapp.mvc.dao.CustomerRepository;
import com.springapp.mvc.model.neo4j.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Hasitha on 6/5/2015.
 */

@Transactional
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
    @Transactional
    public Customer findById(long id) {
        return customerRepository.findOne(id);
    }

    @Override
    @Transactional
    public Result<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional
    public Result<Customer> findCustomer(String id_number) {
        return customerRepository.findAllBySchemaPropertyValue("id_number",id_number);
    }

    @Override
    public Customer executeQuery(String first_name) {
        return customerRepository.executeCustomizedQuery(first_name);
    }

    @Override
    public int count(){
        return (int) customerRepository.count();
    }
}