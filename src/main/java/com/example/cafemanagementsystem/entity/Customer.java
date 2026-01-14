package com.example.cafemanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@ToString
@Getter
public class Customer {
    @Id
    @GeneratedValue
    private Long userId;

    @Column
    private String name;

    @Column
    private String phone;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @ToString.Exclude
    @JsonIgnore
    private List<Orders> orders = new ArrayList<>();

    @OneToMany(mappedBy = "userId", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @ToString.Exclude
    @JsonIgnore
    private List<Point> points = new ArrayList<>();

    public Customer() {
    }

    public Customer(Long userId, String name, String  phone) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
    }
}
