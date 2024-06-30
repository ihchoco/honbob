package com.kdn.honbob.service;

import com.kdn.honbob.domain.model.DinningTable;
import com.kdn.honbob.domain.model.Menu;
import com.kdn.honbob.domain.model.Order;
import com.kdn.honbob.domain.model.OrderMenu;
import com.kdn.honbob.domain.repository.DinningTableRepository;
import com.kdn.honbob.domain.repository.MenuRepository;
import com.kdn.honbob.domain.repository.OrderRepository;
import com.kdn.honbob.presentation.dto.OrderMenuResponseDto;
import com.kdn.honbob.presentation.dto.OrderResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final DinningTableRepository dinningTableRepository;

    @Transactional
    public Long create(Long menuId, int orderCnt, Long dinningTableId){
        //1. menu 조회
        Menu findMenu = findMenuById(menuId);

        //2. dinningTable 조회
        DinningTable dinningTable = findDinningTableById(dinningTableId);

        //3. orderMenu 생성
        OrderMenu orderMenu = OrderMenu.createOrderMenu(findMenu, orderCnt);

        //4. Order 생성
        Order order = Order.create(orderMenu, dinningTable);

        //저장
        return orderRepository.save(order).getId();
    }

    private Menu findMenuById(Long menuId){
        return menuRepository.findById(menuId).orElseThrow(() -> {
            throw new IllegalArgumentException("해당하는 ID를 가진 메뉴가 없습니다!!");
        });
    }

    private DinningTable findDinningTableById(Long dinningTableId) {
        return dinningTableRepository.findById(dinningTableId).orElseThrow(() -> {
            throw new IllegalArgumentException("해당하는 ID를 가진 테이블이 없습니다!!");
        });
    }

    @Transactional
    public List<OrderResponseDto> findAllOrders(){

        List<Order> findAllOrders = orderRepository.findAllWithDinningTableAndOrderMenus();

        return findAllOrders.stream().map(order ->
             new OrderResponseDto(
                    order.getId(),
                    order.getDinningTable().getName(),
                    order.getOrderMenuList().stream().map(orderMenu -> new OrderMenuResponseDto(
                            orderMenu.getMenu().getName(), orderMenu.getPrice(), orderMenu.getCount(), orderMenu.getTotalPrice())
                    ).toList(),
                    order.getOrderData(),
                    order.getStatus())
        ).toList();
    }

    public void cancel(Long orderId){
        Order findOrder = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("주문 번호가 없습니다"));
        findOrder.cancel();

        orderRepository.delete(findOrder);
    }

}
