package com.kdn.honbob.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DinningTableResponseDto {
    private Long id;

    private int seatCount;

    private String name;
}
