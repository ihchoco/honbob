package com.kdn.honbob.service;

import com.kdn.honbob.domain.model.DinningTable;
import com.kdn.honbob.domain.repository.DinningTableRepository;
import com.kdn.honbob.presentation.dto.DinningTableRequestDto;
import com.kdn.honbob.presentation.dto.DinningTableResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DinningTableService {
    private final DinningTableRepository dinningTableRepository;

    @Transactional
    public Long registerDinningTable(DinningTableRequestDto dinningTableRequestDto){
        DinningTable dinningTable = DinningTable.builder()
                .id(dinningTableRequestDto.getId())
                .seatCount(dinningTableRequestDto.getSeatCount())
                .name(dinningTableRequestDto.getName())
                .build();

        DinningTable saveddinningTable = dinningTableRepository.save(dinningTable);

        return saveddinningTable.getId();
    }
    public DinningTableResponseDto findDinningTableById(Long id){
        DinningTable findDinningTable = dinningTableRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("해당하는 ID를 가진 테이블이 없습니다!!");
        });

         return new DinningTableResponseDto(
                 findDinningTable.getId(),
                 findDinningTable.getSeatCount(),
                 findDinningTable.getName()
        );
    }
    public List<DinningTableResponseDto> findAlldinningTable(){
        List<DinningTable> findDinningTableList = dinningTableRepository.findAll();

        if(findDinningTableList.isEmpty())
            throw new IllegalStateException("등록된 테이블이 없습니다!! 테이블을 등록해주세요");

        return findDinningTableList.stream().map(findDinningTable ->
            new DinningTableResponseDto(
                    findDinningTable.getId(),
                    findDinningTable.getSeatCount(),
                    findDinningTable.getName()
            )
        ).toList();
    }
}
