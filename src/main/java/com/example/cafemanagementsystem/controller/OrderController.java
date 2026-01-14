package com.example.cafemanagementsystem.controller;

import com.example.cafemanagementsystem.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {
    @Autowired
    private OrderRepo orderRepo;

}
