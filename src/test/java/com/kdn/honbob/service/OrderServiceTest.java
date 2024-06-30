package com.kdn.honbob.service;

import com.kdn.honbob.domain.model.DinningTable;
import com.kdn.honbob.domain.model.Menu;
import com.kdn.honbob.domain.model.Order;
import com.kdn.honbob.domain.repository.DinningTableRepository;
import com.kdn.honbob.domain.repository.MenuRepository;
import com.kdn.honbob.domain.repository.OrderRepository;
import com.kdn.honbob.presentation.dto.MenuRequestDto;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private DinningTableRepository dinningTableRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("주문 생성 테스트")
    public void 주문_생성_테스트(){
        //given
        Menu menu = new Menu(null, "오징어 우동볶음", 8900, 10);
        DinningTable dinningTable = DinningTable.builder()
                .name("1번 테이블")
                .seatCount(4)
                .build();

        //when
        Menu savedMenu = menuRepository.save(menu);
        DinningTable savedTable = dinningTableRepository.save(dinningTable);

        Long orderId = orderService.create(savedMenu.getId(), 2, savedTable.getId());

        Order savedOrder = orderRepository.findById(orderId).orElseThrow(() ->
                new IllegalStateException("ID와 일치하는 주문이 없습니다")
        );
        //then
        Assertions.assertThat(savedOrder.getId()).isEqualTo(1L);
        Assertions.assertThat(orderRepository.count()).isEqualTo(1);

        System.out.println("DB에 저장된 주문 COUNT : "+orderRepository.count());

    }
}