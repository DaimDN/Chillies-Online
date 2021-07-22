package com.chillies.CUSTOMERSERVICE.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.LinkedList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Customer {

    @Id
    private String id;
    private String name;
    private String address;
    private String city;
    private String Postcode;
    private String Telno;
    private LinkedList<Order> Orders;


}
