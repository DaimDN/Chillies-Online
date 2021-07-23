package com.chillies.CUSTOMERSERVICE.Services;


import com.chillies.CUSTOMERSERVICE.Repository.customerRepository;
import com.chillies.CUSTOMERSERVICE.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class customerService {

    @Autowired
    private customerRepository CustomerRepository;


    public List<Customer> getAllCustomers(){
        return CustomerRepository.findAll();
    }

    public Customer addCustomer(Customer aCustomer){
        CustomerRepository.save(aCustomer);
        return aCustomer;
    }

    public void deleteCustomer(String customerId){
        CustomerRepository.deleteCustomerById(customerId);
    }

    public Optional<Customer> findACustomer(String customerId){
     Optional<Customer> aCustomer =  CustomerRepository.findById(customerId);
        return aCustomer;
    }

    public Customer updateCustomer(Customer customer) throws NoSuchElementException {
        CustomerRepository.save(customer);
        return customer;
    }

}
