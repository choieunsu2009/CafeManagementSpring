package com.example.cafemanagementsystem.dto;

import com.example.cafemanagementsystem.entity.Customer;
import com.example.cafemanagementsystem.entity.Menu;
import com.example.cafemanagementsystem.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class OrderDTO {
    private Long orderId;
    private Long statusCode;
    private Customer userId;
    private Menu menuId;

    public Orders toEntity(){
        return new Orders(orderId, statusCode, userId, menuId);
    }
}
