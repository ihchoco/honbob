package com.kdn.honbob.domain.service;

import com.kdn.honbob.domain.repository.MenuRepository;
import com.kdn.honbob.presentation.dto.MenuRequestDto;
import com.kdn.honbob.presentation.dto.MenuResponseDto;
import com.kdn.honbob.service.MenuService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuRepository menuRepository;

    @BeforeEach
    public void clean(){
        menuRepository.deleteAll();
    }

    @Test
    @DisplayName("음식메뉴 등록 테스트")
    public void 메뉴_등록_테스트(){
        //given
        MenuRequestDto requestDto = new MenuRequestDto(null, "오징어 우동볶음", 8900, 10);

        //when
        Long savedMenuId = menuService.registerMenu(requestDto);

        //then (import org.assertj.core.api.Assertions.*);
        Assertions.assertThat(menuRepository.count()).isEqualTo(1);

        //실제 눈으로 확인
        System.out.println("DB에 저장된 메뉴 Count : " + menuRepository.count());
    }

    @Test
    @DisplayName("음식메뉴 조회 테스트")
    public void 메뉴_조회_테스트(){
        //given
        MenuRequestDto requestDto = new MenuRequestDto(null, "오징어 우동볶음", 8900, 10);

        //when
        Long savedMenuId = menuService.registerMenu(requestDto);
        MenuResponseDto findMenu = menuService.findMenuById(savedMenuId);

        //then
        Assertions.assertThat(requestDto.getName()).isEqualTo(findMenu.getName());

        System.out.println("DB에 저장된 메뉴 조회 : "+findMenu.getName());
    }

    @Test
    @DisplayName("전체 음식 메뉴 조회 테스트")
    public void 메뉴_전체_조회_테스트(){
        //given
        MenuRequestDto requestDto = new MenuRequestDto(null, "오징어 우동볶음", 8900, 10);

        //when
        menuService.registerMenu(requestDto);
        List<MenuResponseDto> findMenuList = menuService.findAllMenu();

        //then
        Assertions.assertThat(findMenuList.size()).isEqualTo(1);
        Assertions.assertThat(findMenuList.get(0).getName()).isEqualTo(requestDto.getName());

        System.out.println("DB에 저장된 전체 메뉴 COUNT : "+findMenuList.size());
    }

    @Test
    @DisplayName("전체 음식 메뉴 조회 실패 테스트")
    public void 메뉴_전체_조회_실패_테스트(){
        //given + when
       assertThrows(IllegalStateException.class, () -> {
           menuService.findAllMenu();
       });

    }

}