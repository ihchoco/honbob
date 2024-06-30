package com.kdn.honbob.domain.repository;

import com.kdn.honbob.domain.model.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // Fetch Join을 사용하여 Order 엔티티의 관련 데이터를 함께 조회
//    @EntityGraph(attributePaths = {"dinningTable", "orderMenuList"})
//    List<Order> findAllWithDinningTableAndOrderMenus(); //이름이 중요함

    @Query("SELECT DISTINCT o FROM Order o " +
            "LEFT JOIN FETCH o.dinningTable dt " +
            "LEFT JOIN FETCH o.orderMenuList om " +
            "LEFT JOIN FETCH om.menu")
    List<Order> findAllWithDinningTableAndOrderMenus();
}
