package com.example.cafemanagementsystem.repo;

import com.example.cafemanagementsystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findCustomerByPhone(String phone);
    Customer findCustomerByUserId(Long userId);

    Boolean existsByPhone(String  phone);
}
