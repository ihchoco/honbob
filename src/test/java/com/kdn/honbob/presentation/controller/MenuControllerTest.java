package com.kdn.honbob.presentation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdn.honbob.domain.model.Menu;
import com.kdn.honbob.domain.repository.MenuRepository;
import com.kdn.honbob.presentation.dto.MenuRequestDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void clean(){
        menuRepository.deleteAll();
    }

    @Test
    @DisplayName("메뉴 등록 테스트")
    public void 메뉴_등록_테스트() throws Exception {

        MenuRequestDto dto = new MenuRequestDto(1L, "오징어 우동볶음", 8900, 10);

        String json = objectMapper.writeValueAsString(dto);

        System.out.println(json);

        mockMvc.perform(MockMvcRequestBuilders.post("/menus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("메뉴 조회 테스트")
    public void 메뉴_조회_테스트() throws Exception{
        //given
        Menu menu = new Menu(null, "오징어 우동볶음", 8900, 10);

        //when
        Menu savedMenu = menuRepository.save(menu);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/menus/{id}", savedMenu.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("오징어 우동볶음"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(8900))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("메뉴 전체 조회 테스트")
    public void 메뉴_전체_조회_테스트() throws Exception{
        //given
        Menu menu = new Menu(null, "오징어 우동볶음", 8900, 10);

        //when
        Menu savedMenu = menuRepository.save(menu);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/menus/{id}", savedMenu.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
}