package com.chillies.CUSTOMERSERVICE.Controller;


import com.chillies.CUSTOMERSERVICE.Repository.customerRepository;
import com.chillies.CUSTOMERSERVICE.Services.customerService;
import com.chillies.CUSTOMERSERVICE.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
public class customercontroller {

    @Autowired
    private customerService CustomerServices;

    @Autowired
    private customerRepository CustomerRepository;


    @GetMapping(path = "all", produces = "Application/json")
    public ResponseEntity<?> getALLListOfCustomer(){
        return  ResponseEntity.ok().body(CustomerServices.getAllCustomers());
    }

    @PostMapping(path = "add", consumes = "Application/json", produces = "Application/json")
    public ResponseEntity<?> addCustomer(@RequestBody Customer aCustomer){
        List<Customer> allCustomers = CustomerServices.getAllCustomers();
        Stream<Customer> checkForCustomer =  allCustomers.stream().filter(x -> {
            return x.getName().equals(aCustomer.getName()) && x.getPostcode().equals(aCustomer.getPostcode()) && x.getTelno().equals(aCustomer.getTelno());
        });
        HashMap<String, String> message = new HashMap<>();
        if(checkForCustomer.count() > 0){
            message.put("msg", "customer already exist");
            message.put("type", "error");
            return ResponseEntity.ok().body(message);
        }
        else{
            CustomerServices.addCustomer(aCustomer);
            message.put("msg", "customer has been saved");
            message.put("type", "success");
            return ResponseEntity.ok().body(message);
        }
    }

    @DeleteMapping(path = "delete", consumes = "Application/json", produces = "Application/json")
    public ResponseEntity<?> deleteCustomer (@RequestBody String customerId){
        Optional<Customer> aCustomer = CustomerServices.findACustomer(customerId);
        if(aCustomer.isPresent()){
            CustomerServices.deleteCustomer(customerId);
            HashMap<String, String> deleteMessage = new HashMap<>();
            deleteMessage.put("msg", "customer has been deleted");
            return ResponseEntity.ok().body(deleteMessage);
        }
        else{
            HashMap<String, String> deleteMessage = new HashMap<>();
            deleteMessage.put("msg", "customer doesn't exist");
            return ResponseEntity.ok().body(deleteMessage);
        }
    }


    @PatchMapping(path = "update", consumes = "Application/json", produces = "Application/json")
    public ResponseEntity<?> updateCustomer (@RequestBody Customer customer){
        Optional<Customer> foundCustomer = CustomerRepository.findById(customer.getId());
        HashMap<String, String> message = new HashMap<>();
        if(!foundCustomer.isPresent()){
            message.put("msg", "customer doesnt't exist");
            message.put("type", "not found");
            return ResponseEntity.badRequest().body(message);
        }else{
            CustomerServices.updateCustomer(customer);
            message.put("msg", "customer has been updated");
            message.put("type", "success");
            return  ResponseEntity.ok().body(message);
        }

    }


    @GetMapping(path = "find/{id}", produces = "Application/json")
        public ResponseEntity<?> findaCustomer(@PathVariable String id){
        Optional<Customer> customer = CustomerRepository.findById(id);
        HashMap<String, String> message = new HashMap<>();
        if(customer.isEmpty()) {
            message.put("msg", "customer is not available");
            message.put("type", "not found");
            return ResponseEntity.badRequest().body(message);}
        return ResponseEntity.ok().body(customer);

    }
}
