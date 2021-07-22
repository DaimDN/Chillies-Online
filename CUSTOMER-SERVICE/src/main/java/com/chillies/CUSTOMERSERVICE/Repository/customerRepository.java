package com.chillies.CUSTOMERSERVICE.Repository;


import com.chillies.CUSTOMERSERVICE.model.Customer;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.Optional;

@Repository
public interface customerRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findById(String customerId);
    LinkedList<Customer> findAll();
    @DeleteQuery
    void deleteCustomerById(String id);


}
