package com.example.cafemanagementsystem.repo;

import com.example.cafemanagementsystem.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface PointRepo extends JpaRepository<Point, Long> {
    ArrayList<Point> findPointByUserId_UserId(Long userId);

    @Query(value = "select * from Point where userId = :userId order by RID DESC LIMIT 1;", nativeQuery = true)
    ArrayList<Point> findLatestByUserId(Long userId);
}
