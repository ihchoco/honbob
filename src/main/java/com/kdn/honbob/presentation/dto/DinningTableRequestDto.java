package com.kdn.honbob.presentation.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DinningTableRequestDto {
    private Long id;

    private int seatCount;

    @NotEmpty(message = "테이블명은 필수값입니다")
    private String name;
}
