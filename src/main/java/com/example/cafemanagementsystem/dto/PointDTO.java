package com.example.cafemanagementsystem.dto;

import com.example.cafemanagementsystem.entity.Customer;
import com.example.cafemanagementsystem.entity.Point;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class PointDTO {
    private Long RID;
    private boolean status;
    private Long point;
    private Long total_point;
    private Customer userId;

    public Point toEntity(){
        return new Point(RID, status, point, total_point, userId);
    }
}
