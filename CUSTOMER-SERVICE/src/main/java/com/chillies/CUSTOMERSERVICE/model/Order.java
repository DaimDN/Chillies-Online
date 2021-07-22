package com.chillies.CUSTOMERSERVICE.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class Order {
    @Id
    private String orderId;
    private Date orderDate;
    private Customer customer;
    private int price;
    private List Products;

}
