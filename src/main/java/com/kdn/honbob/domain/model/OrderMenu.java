package com.kdn.honbob.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDER_MENU")
public class OrderMenu {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "price")
    private int price;

    @Column(name = "count")
    private int count;

    @Column(name = "totalPrice")
    private int totalPrice;

    public void addOrder(Order order){
        this.order = order;
        menu.reduceStock(count);
    }

    public static OrderMenu createOrderMenu(Menu menu, int orderCnt){
        // 요구사항) 메뉴에 재고가 없으면 주문 할 수 없다
        menu.validateStockQuantityAvailability(orderCnt);

        return OrderMenu.builder()
                .menu(menu)
                .price(menu.getPrice())
                .count(orderCnt)
                .totalPrice(menu.getPrice() * orderCnt)
                .build();
    }

    // -- 도메인 로직 --
    public void cancel(){
        menu.addStock(count);
    }
}
