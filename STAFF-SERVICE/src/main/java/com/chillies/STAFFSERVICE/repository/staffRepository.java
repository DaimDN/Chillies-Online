package com.chillies.STAFFSERVICE.repository;

import com.chillies.STAFFSERVICE.model.Staff;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public interface staffRepository  extends MongoRepository<Staff, String > {

    Optional<Staff> findById(String customerId);

    Staff findByName(String name);
    LinkedList<Staff> findAll();

    @DeleteQuery
    void deleteStaffById(String id);


}
