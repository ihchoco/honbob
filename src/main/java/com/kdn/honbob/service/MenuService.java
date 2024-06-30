package com.kdn.honbob.service;

import com.kdn.honbob.domain.model.Menu;
import com.kdn.honbob.domain.repository.MenuRepository;
import com.kdn.honbob.presentation.dto.MenuRequestDto;
import com.kdn.honbob.presentation.dto.MenuResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuService {
    private final MenuRepository menuRepository;

    public Long registerMenu(MenuRequestDto menuRequestDto){
        Menu menu = new Menu(
                menuRequestDto.getId(),
                menuRequestDto.getName(),
                menuRequestDto.getPrice(),
                menuRequestDto.getStockQuantity()
        );

        Menu savedMenu = menuRepository.save(menu);

        return savedMenu.getId();
    }

    public MenuResponseDto findMenuById(Long id){
        Menu findMenu = menuRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("해당하는 ID를 가진 메뉴가 없습니다!!");
        });

         return new MenuResponseDto(
                findMenu.getId(),
                findMenu.getName(),
                findMenu.getPrice(),
                findMenu.getStockQuantity()
        );
    }

    public List<MenuResponseDto> findAllMenu(){
        List<Menu> findMenuList = menuRepository.findAll();

        if(findMenuList.isEmpty())
            throw new IllegalStateException("등록된 메뉴가 없습니다!! 메뉴를 등록해주세요");

        return findMenuList.stream().map(findMenu ->
            new MenuResponseDto(
                    findMenu.getId(),
                    findMenu.getName(),
                    findMenu.getPrice(),
                    findMenu.getStockQuantity()
            )
        ).toList();
    }
}
