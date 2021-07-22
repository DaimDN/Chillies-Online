package com.chillies.STAFFSERVICE.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Staff {
    @Id

    private String id;
    private Date registerationDate;
    private String name;
    private Boolean active;
    private Long UniqueCode;


}
