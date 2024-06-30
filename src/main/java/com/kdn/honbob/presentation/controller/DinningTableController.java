package com.kdn.honbob.presentation.controller;

import com.kdn.honbob.presentation.dto.DinningTableRequestDto;
import com.kdn.honbob.presentation.dto.DinningTableResponseDto;
import com.kdn.honbob.presentation.dto.MenuRequestDto;
import com.kdn.honbob.presentation.dto.MenuResponseDto;
import com.kdn.honbob.service.DinningTableService;
import com.kdn.honbob.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class DinningTableController {
    private final DinningTableService dinningTableService;

    @PostMapping("/tables")
    public Long registerTable(@Valid @RequestBody DinningTableRequestDto dinningTableRequestDto){
        return dinningTableService.registerDinningTable(dinningTableRequestDto);
    }

    @GetMapping("/tables/{id}")
    public DinningTableResponseDto findTableById(@PathVariable Long id){
        return dinningTableService.findDinningTableById(id);
    }

    @GetMapping("/tables")
    public List<DinningTableResponseDto> findAllTable(){
        return dinningTableService.findAlldinningTable();
    }

}
