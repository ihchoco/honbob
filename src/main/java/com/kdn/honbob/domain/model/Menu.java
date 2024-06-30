package com.kdn.honbob.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MENU")
public class Menu {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    // 요구사항) 메뉴에 재고가 없으면 주문 할 수 없다
    public void validateStockQuantityAvailability(int orderCnt){
        if(orderCnt > stockQuantity)
            throw new IllegalArgumentException("재고 개수를 초과하였습니다. 주문 수량 : " + orderCnt + " , 재고 : "+stockQuantity);
    }

    public void reduceStock(int orderCnt){
        validateStockQuantityAvailability(orderCnt);
        stockQuantity -= orderCnt;
    }

    public void addStock(int orderCnt){
        stockQuantity += orderCnt;
    }
}
