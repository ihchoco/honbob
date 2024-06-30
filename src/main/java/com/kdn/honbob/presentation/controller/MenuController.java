package com.kdn.honbob.presentation.controller;

import com.kdn.honbob.presentation.dto.MenuRequestDto;
import com.kdn.honbob.presentation.dto.MenuResponseDto;
import com.kdn.honbob.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MenuController {
    private final MenuService menuService;

    @PostMapping("/menus")
    public Long registerMenu(@Valid @RequestBody MenuRequestDto menuRequestDto){
        System.out.println("registerMenu 호출");
        return menuService.registerMenu(menuRequestDto);
    }

    @GetMapping("/menus/{id}")
    public MenuResponseDto findMenuById(@PathVariable Long id){
        return menuService.findMenuById(id);
    }

    @GetMapping("/menus")
    public List<MenuResponseDto> findAllMenu(){
        return menuService.findAllMenu();
    }

}
