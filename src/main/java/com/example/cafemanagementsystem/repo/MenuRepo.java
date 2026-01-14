package com.example.cafemanagementsystem.repo;

import com.example.cafemanagementsystem.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface MenuRepo  extends JpaRepository<Menu, Long> {
    ArrayList<Menu> findMenuByMenuId(Long menuId);

}
