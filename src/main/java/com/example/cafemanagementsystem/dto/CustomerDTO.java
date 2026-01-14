package com.example.cafemanagementsystem.dto;

import com.example.cafemanagementsystem.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Setter
public class CustomerDTO {
    private Long userId;
    private String name;
    private String  phone;

    public Customer toEntity(){
        return new Customer(userId, name, phone);
    }
}
