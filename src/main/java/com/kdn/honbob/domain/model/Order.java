package com.kdn.honbob.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//git branch 머지 리퀘스트 테스트
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDERS") // ORDER는 DB 예약어라서 사용 불가
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dinning_table_id")
    private DinningTable dinningTable;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<OrderMenu> orderMenuList = new ArrayList<>();

    private LocalDateTime orderData;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    // -- 연관관계 메서드 --
    private void addOrderMenu(OrderMenu orderMenu){
        this.orderMenuList.add(orderMenu);
        orderMenu.addOrder(this);
    }

    // -- 연관관계 메서드 --
    private void chooseDinningTable(DinningTable dinningTable){
        this.dinningTable = dinningTable;
        dinningTable.addOrder(this);
    }

    public static Order create(OrderMenu orderMenu, DinningTable dinningTable){
        Order order = new Order();

        order.chooseDinningTable(dinningTable);
        order.addOrderMenu(orderMenu);
        order.status = OrderStatus.PAYMENT;

        return order;
    }

    // -- 도메인 로직 --
    public Long cancel(){
        orderMenuList.forEach(orderMenu ->
            orderMenu.cancel()
        );

        return id;
    }
}
