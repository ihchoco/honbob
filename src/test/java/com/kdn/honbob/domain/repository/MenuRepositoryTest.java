package com.kdn.honbob.domain.repository;

import com.kdn.honbob.domain.model.Menu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class MenuRepositoryTest {

    @Autowired
    private MenuRepository menuRepository;

    @Test
    @DisplayName("음식메뉴 등록 테스트")
    public void 메뉴_등록_테스트(){
        //given
        Menu menu = new Menu(null, "오징어 우동볶음", 8900, 10);

        //when
        Menu savedMenu = menuRepository.save(menu);

        //then (import org.assertj.core.api.Assertions.*);
        Assertions.assertThat(menuRepository.count()).isEqualTo(1);
        Assertions.assertThat(savedMenu.getName()).isEqualTo(menu.getName());

        //실제 눈으로 확인
        System.out.println("DB에 저장된 메뉴 Count : " + menuRepository.count());
        System.out.println("DB에 저장된 메뉴 이름 : " + savedMenu.getName());

    }
}