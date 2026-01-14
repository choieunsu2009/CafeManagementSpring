package com.example.cafemanagementsystem.dto;

import com.example.cafemanagementsystem.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MenuDTO {
    private Long menuId;
    private String menuName;
    private Long price;

    public Menu toEntity(){
        return new Menu(menuId, menuName, price);
    }
}
