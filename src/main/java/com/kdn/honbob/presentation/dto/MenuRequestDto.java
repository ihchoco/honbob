package com.kdn.honbob.presentation.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MenuRequestDto {
    private Long id;
    @NotEmpty(message = "이름은 필수입니다")
    private String name;
    @Positive(message = "가격은 필수입니다")
    private int price;
    @Positive(message = "재고는 필수입니다")
    private int stockQuantity;
}
