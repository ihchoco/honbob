package com.kdn.honbob.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdn.honbob.domain.model.DinningTable;
import com.kdn.honbob.domain.model.Menu;
import com.kdn.honbob.domain.repository.DinningTableRepository;
import com.kdn.honbob.domain.repository.MenuRepository;
import com.kdn.honbob.domain.repository.OrderRepository;
import com.kdn.honbob.presentation.dto.MenuRequestDto;
import com.kdn.honbob.service.OrderService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private DinningTableRepository dinningTableRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public void clean(){
        menuRepository.deleteAll();
        dinningTableRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    @DisplayName("주문 등록 테스트")
    public void 주문_등록_테스트() throws Exception {
        //given
        Menu menu = new Menu(null, "오징어 우동볶음", 8900, 10);
        DinningTable dinningTable = DinningTable.builder()
                .name("1번 테이블")
                .seatCount(4)
                .build();

        //when
        Menu savedMenu = menuRepository.save(menu);
        DinningTable savedTable = dinningTableRepository.save(dinningTable);


        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("menuId", String.valueOf(savedMenu.getId()))
                        .param("orderCnt", String.valueOf(2))
                        .param("dinningTableId", String.valueOf(savedTable.getId())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("주문 조회 테스트")
    @Transactional
    public void 주문_조회_테스트() throws Exception {
        //given
        Menu menu = new Menu(null, "오징어 우동볶음", 8900, 10);
        DinningTable dinningTable = DinningTable.builder()
                .name("1번 테이블")
                .seatCount(4)
                .build();

        //when
        //Menu savedMenu = menuRepository.save(menu);
        //DinningTable savedTable = dinningTableRepository.save(dinningTable);

        entityManager.persist(menu);
        entityManager.persist(dinningTable);
        entityManager.flush();


        mockMvc.perform(MockMvcRequestBuilders.get("/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}