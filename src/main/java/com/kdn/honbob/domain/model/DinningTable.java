package com.kdn.honbob.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Builder
@Getter
@NoArgsConstructor
@Entity
@Table(name = "DINNING_TABLE")
@AllArgsConstructor
public class DinningTable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "seat_count")
    private int seatCount;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "dinningTable")
    private List<Order> orderList = new ArrayList<>();

    public void addOrder(Order order){
        orderList.add(order);
    }

}
