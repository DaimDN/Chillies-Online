package com.chillies.STAFFSERVICE.controllers;


import com.chillies.STAFFSERVICE.model.Staff;
import com.chillies.STAFFSERVICE.repository.staffRepository;
import com.chillies.STAFFSERVICE.services.staffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class staffController {

    @Autowired
    private staffRepository StaffRepository;

    @Autowired
    private staffService StaffService;


    @GetMapping(path = "all", produces = "Application/json")
    public ResponseEntity<?> getALLStaff(){
        return  ResponseEntity.ok().body(StaffService.getAllStaff());
    }

    @PostMapping(path = "add", consumes = "Application/json", produces = "Application/json")
    public ResponseEntity<?> addStaff(@RequestBody Staff staff){
       List<Staff> allStaff = StaffService.getAllStaff();
       Stream<Staff> aFoundStaff =  allStaff.stream().filter(foundMember -> {
           return foundMember.getName().toUpperCase().equals(staff.getName().toUpperCase()) && foundMember.getUniqueCode().equals(staff.getUniqueCode());
       });
       if(aFoundStaff.toArray().length > 0){
           HashMap<String, String>  foundMessage = new HashMap<>();
           foundMessage.put("msg", "Staff member already exists");
           foundMessage.put("type", "error");
           return ResponseEntity.status(HttpStatus.CONFLICT).body(foundMessage);
       }
       else{
           StaffService.addStaff(staff);
           HashMap<String, String>  foundMessage = new HashMap<>();
           foundMessage.put("msg", "Staff member has been added");
           foundMessage.put("type", "success");
           return ResponseEntity.status(HttpStatus.ACCEPTED).body(foundMessage);
       }
    }

    @PatchMapping(path = "status/{id}/{status}", produces = "Application/json")
    public ResponseEntity<?> updateStaffStatus(@PathVariable String id,@PathVariable Boolean status ) {
        Optional<Staff> staff = StaffService.findAStaff(id);
        if(staff.isPresent())  {
            Staff newStaff = staff.get();
            newStaff.setActive(status);
            StaffRepository.save(newStaff);
            HashMap<String, Object> message = new HashMap<>();
            message.put("msg", "Staff has been updated");
            message.put("type", "success");
            return ResponseEntity.ok().body(message);
        }
        HashMap<String, Object> message = new HashMap<>();
        message.put("msg", "Staff doesn't exist");
        message.put("type", "error");
        return ResponseEntity.ok().body(message);

    }

    @GetMapping(path = "find/{id}", produces = "Application/json")
    public ResponseEntity<?> findStaff(@PathVariable String id){
        Optional<Staff> staff = StaffService.findAStaff(id);
        HashMap<String, String> StaffResponse = new HashMap<>();
        if(staff.isEmpty()){
            StaffResponse.put("msg", "Staff member doesnt exist");
            StaffResponse.put("type", "error");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StaffResponse);
        }
        else{
            HashMap<String, Object> foundStaff = new HashMap<>();
            foundStaff.put("data", staff);
            foundStaff.put("type", "success");
            return ResponseEntity.status(HttpStatus.OK).body(foundStaff);
        }
    }

    @DeleteMapping(path = "delete", consumes = "Application/json", produces = "Application/json")
    public ResponseEntity<?> deleteStaffMember(@RequestBody  Staff staff){
        StaffService.deleteStaff(staff.getId());
        HashMap<String, String> deletingMessage = new HashMap<>();
        deletingMessage.put("msg", "staff member has been deleted");
        deletingMessage.put("type", "success");
        return ResponseEntity.status(HttpStatus.OK).body(deletingMessage);
    }
}
