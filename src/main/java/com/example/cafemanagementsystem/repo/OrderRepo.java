package com.example.cafemanagementsystem.repo;

import com.example.cafemanagementsystem.entity.Orders;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface OrderRepo  extends JpaRepository<Orders, Long> {
    @NullMarked
    ArrayList<Orders> findAll();

    ArrayList<Orders> findOrderByUserId_UserId(Long userId);

    // statusCode가 2가 아닌것만 출력 => 0, 1만 출력
    // 항상 2만 입력할것
    ArrayList<Orders> findOrderByStatusCodeIsNot(Long statusCode);

    // statusCode가 2인것만 출력
    // 항상 2만 입력할것
    ArrayList<Orders> findOrderByStatusCodeIs(Long statusCode);
}
