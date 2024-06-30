package com.kdn.honbob.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderMenuResponseDto {
    private String name;
    private int price;
    private int count;
    private int totalPrice;
}
