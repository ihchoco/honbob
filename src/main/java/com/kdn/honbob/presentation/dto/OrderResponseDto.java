package com.kdn.honbob.presentation.dto;

import com.kdn.honbob.domain.model.DinningTable;
import com.kdn.honbob.domain.model.Order;
import com.kdn.honbob.domain.model.OrderMenu;
import com.kdn.honbob.domain.model.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderResponseDto {
    private Long id;

    private String dinningTableName;

    private List<OrderMenuResponseDto> orderMenuList;

    private LocalDateTime orderData;

    private OrderStatus status;

}
