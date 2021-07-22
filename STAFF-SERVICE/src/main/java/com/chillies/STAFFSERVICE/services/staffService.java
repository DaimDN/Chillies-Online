package com.chillies.STAFFSERVICE.services;


import com.chillies.STAFFSERVICE.model.Staff;
import com.chillies.STAFFSERVICE.repository.staffRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class staffService {

    @Autowired
    private staffRepository StaffRepository;

    public List<Staff> getAllStaff(){
        return StaffRepository.findAll();
    }

    public Staff addStaff(Staff staff){
        staff.setRegisterationDate(new Date());
        staff.setActive(false);
        if(staff.getUniqueCode() == null) staff.setUniqueCode(1234l);
      StaffRepository.save(staff);
        return staff;
    }

    public void deleteStaff(String staffId){
        StaffRepository.deleteStaffById(staffId);
    }

    public Optional<Staff> findAStaff(String staffId){
        Optional<Staff> aStaff =  StaffRepository.findById(staffId);
        return aStaff;
    }

    public Staff updateStaff(Staff staff) throws NoSuchElementException {
        Optional<Staff> aStaff = StaffRepository.findById(staff.getId());
        Staff newStaffDetail  = aStaff.get();
        StaffRepository.save(newStaffDetail);
        return newStaffDetail;
    }


}
