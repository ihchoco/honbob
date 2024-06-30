package com.kdn.honbob.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderRequestDto {
    private Long menuId;
    private int orderCnt;
    private Long dinningTableId;
}
