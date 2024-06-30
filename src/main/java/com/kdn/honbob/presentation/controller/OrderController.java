package com.kdn.honbob.presentation.controller;

import com.kdn.honbob.presentation.dto.OrderRequestDto;
import com.kdn.honbob.presentation.dto.OrderResponseDto;
import com.kdn.honbob.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders")
    public Long createOrder(@RequestBody OrderRequestDto orderRequestDto){
        return orderService.create(
                orderRequestDto.getMenuId(),
                orderRequestDto.getOrderCnt(),
                orderRequestDto.getDinningTableId()
        );
    }

    @GetMapping("/orders")
    public List<OrderResponseDto> findAllOrder(){
        List<OrderResponseDto> allOrders = orderService.findAllOrders();
        allOrders.forEach(x -> System.out.println(x));
        return allOrders;
    }

    @DeleteMapping("/orders/{id}")
    public void cancelOrder(@PathVariable Long id){
        orderService.cancel(id);
    }
}
