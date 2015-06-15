package com.springapp.mvc.dao;

import com.springapp.mvc.model.neo4j.Customer;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Hasitha on 6/10/2015.
 */

@Transactional
@EnableTransactionManagement
@Repository
public interface CustomerRepository extends GraphRepository<Customer>{
    @Query("match Customer where Customer.first_name={0} return Customer")
    Customer executeCustomizedQuery(String first_name);
}