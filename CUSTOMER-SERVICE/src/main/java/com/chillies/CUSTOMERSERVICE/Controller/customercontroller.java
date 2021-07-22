package com.chillies.CUSTOMERSERVICE.Controller;


import com.chillies.CUSTOMERSERVICE.Services.customerService;
import com.chillies.CUSTOMERSERVICE.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;


@RestController
public class customercontroller {

    @Autowired
    private customerService CustomerServices;


    @GetMapping(path = "all", produces = "Application/json")
    public ResponseEntity<?> getALLListOfCustomer(){
        return  ResponseEntity.ok().body(CustomerServices.getAllCustomers());
    }

    @PostMapping(path = "add", consumes = "Application/json", produces = "Application/json")
    public ResponseEntity<HashMap<String, String>> addCustomer(@RequestBody Customer aCustomer){
     CustomerServices.addCustomer(aCustomer);
     HashMap<String, String> message = new HashMap<>();
     message.put("msg", "customer has been added");
     return  ResponseEntity.ok().body(message);
    }

    @PostMapping(path = "delete", consumes = "Application/json", produces = "Application/json")
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
        Optional<Customer> aCustomer = CustomerServices.findACustomer(customer.getId());
        if(aCustomer.isPresent()){
            CustomerServices.updateCustomer(customer);
            HashMap<String, String> UpdateMessage = new HashMap<>();
            UpdateMessage.put("msg", "customer has been updated");
            return ResponseEntity.ok().body(UpdateMessage);
        }
        else{
            HashMap<String, String> deleteMessage = new HashMap<>();
            deleteMessage.put("msg", "customer doesn't exist");
            return ResponseEntity.ok().body(deleteMessage);
        }

    }

    @GetMapping(path="find/{id}", consumes = "Application/json", produces = "Application/json")
    public ResponseEntity<?> findCustomer(@PathVariable("id") String customerId){
        Optional<Customer> founded = CustomerServices.findACustomer(customerId);
        HashMap<String,Optional<Customer>> messageBox = new HashMap<>();
        messageBox.put("Customer" , founded);
        return ResponseEntity.ok().body(messageBox);
    }


}
