package com.example.cafemanagementsystem.controller;

import com.example.cafemanagementsystem.entity.Customer;
import com.example.cafemanagementsystem.entity.Point;
import com.example.cafemanagementsystem.repo.CustomerRepo;
import com.example.cafemanagementsystem.repo.PointRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class PointContoller {
    @Autowired
    private PointRepo pointRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/points/customer/{phone}")
    @ResponseBody
    public Point findPointByPhone(@PathVariable String phone){
        Customer user = customerRepo.findCustomerByPhone(phone);
        log.info(String.valueOf(user.getUserId()));
        Point point = pointRepo.findLatestByUserId(user.getUserId());
        Point point1;
        if (point == null) {point1 = new Point(null, false, null, 0L, user);}
        else {point1 = point;}
        log.info(point1.toString());
        return point1;
    }
}
