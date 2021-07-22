package com.chillies.STAFFSERVICE.controllers;


import com.chillies.STAFFSERVICE.model.Staff;
import com.chillies.STAFFSERVICE.repository.staffRepository;
import com.chillies.STAFFSERVICE.services.staffService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Optional;

@RestController
public class staffController {

    @Autowired
    private staffRepository StaffRepository;

    @Autowired
    private staffService StaffService;

    @GetMapping(path = "a", produces = "Application/json")
    public ResponseEntity<?> getMessage(){
        return ResponseEntity.ok().body("hwrfuo3fi");
    }

    @GetMapping(path = "all", produces = "Application/json")
    public ResponseEntity<?> getALLStaff(){
        return  ResponseEntity.ok().body(StaffService.getAllStaff());
    }

    @PostMapping(path = "add", consumes = "Application/json", produces = "Application/json")
    public ResponseEntity<?> addStaff(@RequestBody Staff staff){
        Staff findaStaff = StaffRepository.findByName(staff.getName());
        HashMap<String, String> message = new HashMap<>();
        if(findaStaff != null) ResponseEntity.badRequest().body(message.put("msg", "staff already Exist"));
        StaffService.addStaff(staff);
        HashMap<String, String> addMessage = new HashMap<>();
        addMessage.put("msg", "staff member has been added");
        return  ResponseEntity.ok().body(addMessage);
    }
}
