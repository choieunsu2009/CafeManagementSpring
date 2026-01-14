package com.example.cafemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@ToString
@Getter
@Table(name = "Point")
public class Point {
    @Id
    @GeneratedValue
    private Long RID;

    @Column
    private boolean status;

    @Column
    private Long point;

    @Column
    private Long total_point;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Customer userId;


    public Point() {
    }
}
