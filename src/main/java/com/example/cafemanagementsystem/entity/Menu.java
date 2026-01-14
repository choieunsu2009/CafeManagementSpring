package com.example.cafemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@ToString
@Getter
public class Menu {
    @Id
    @GeneratedValue
    private Long menuId;

    @Column
    private String menuName;

    @Column
    private Long price;

    public Menu() {
    }
}
