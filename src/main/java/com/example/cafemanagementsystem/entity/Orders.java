package com.example.cafemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column
    private Long statusCode;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Customer userId;

    @ManyToOne
    @JoinColumn(name = "menuId")
    private Menu menuId;

    public Orders() {
    }
}
