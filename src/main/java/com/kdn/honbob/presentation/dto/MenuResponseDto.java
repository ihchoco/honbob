package com.kdn.honbob.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MenuResponseDto {
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;
}
