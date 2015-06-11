package com.springapp.mvc.dao;

import com.springapp.mvc.model.neo4j.Customer;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Hasitha on 6/10/2015.
 */
@Repository
public interface CustomerRepository extends GraphRepository<Customer>{
}