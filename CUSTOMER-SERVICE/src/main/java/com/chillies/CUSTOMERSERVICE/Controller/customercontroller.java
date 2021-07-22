package com.chillies.CUSTOMERSERVICE.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path = "/")
public class customercontroller {


    @GetMapping(path = "/all", produces = "Application/json")
    public ResponseEntity<List> getAllCutomer(){

    }



}
