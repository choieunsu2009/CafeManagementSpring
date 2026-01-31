package com.example.cafemanagementsystem.dto;

import com.example.cafemanagementsystem.entity.Customer;
import com.example.cafemanagementsystem.entity.Menu;
import com.example.cafemanagementsystem.entity.Orders;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class OrderDTO {
    private Long orderId;
    private Long statusCode;
    private Customer userId;
    private Menu menuId;

    // 상태별 boolean 플래그 (Mustache 조건문용)
    private boolean isPending; // statusCode == 0
    private boolean isProcessing; // statusCode == 1
    private boolean isCompleted; // statusCode == 2

    // 상태별 버튼 텍스트와 스타일
    private String statusText;
    private String statusButtonClass;

    // Entity를 받는 생성자
    public OrderDTO(Orders order) {
        this.orderId = order.getOrderId();
        this.statusCode = order.getStatusCode();
        this.userId = order.getUserId();
        this.menuId = order.getMenuId();

        // 상태 플래그 설정
        this.isPending = (statusCode == 0);
        this.isProcessing = (statusCode == 1);
        this.isCompleted = (statusCode == 2);

        // 상태별 텍스트와 스타일 설정
        switch (statusCode.intValue()) {
            case 0:
                this.statusText = "제조중";
                this.statusButtonClass = "btn btn-outline-primary btn-sm";
                break;
            case 1:
                this.statusText = "제조완료";
                this.statusButtonClass = "btn btn-outline-danger btn-sm";
                break;
            case 2:
                this.statusText = "완료됨";
                this.statusButtonClass = "btn btn-secondary btn-sm";
                break;
            default:
                this.statusText = "알 수 없음";
                this.statusButtonClass = "btn btn-secondary btn-sm";
        }
    }

    // 기존 생성자 (하위 호환성 유지)
    public OrderDTO(Long orderId, Long statusCode, Customer userId, Menu menuId) {
        this.orderId = orderId;
        this.statusCode = statusCode;
        this.userId = userId;
        this.menuId = menuId;

        // 상태 플래그 설정
        this.isPending = (statusCode == 0);
        this.isProcessing = (statusCode == 1);
        this.isCompleted = (statusCode == 2);

        // 상태별 텍스트와 스타일 설정
        switch (statusCode.intValue()) {
            case 0:
                this.statusText = "접수";
                this.statusButtonClass = "btn btn-warning btn-sm";
                break;
            case 1:
                this.statusText = "제조중";
                this.statusButtonClass = "btn btn-info btn-sm";
                break;
            case 2:
                this.statusText = "완료";
                this.statusButtonClass = "btn btn-success btn-sm";
                break;
            default:
                this.statusText = "알 수 없음";
                this.statusButtonClass = "btn btn-secondary btn-sm";
        }
    }

    public Orders toEntity() {
        return new Orders(orderId, statusCode, userId, menuId);
    }
}
