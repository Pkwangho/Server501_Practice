package com.busanit501.springminitest.service;

import com.busanit501.springminitest.domain.FoodVO;
import com.busanit501.springminitest.dto.FoodDTO;
import com.busanit501.springminitest.dto.PageRequestDTO;
import com.busanit501.springminitest.dto.PageResponseDTO;
import com.busanit501.springminitest.mapper.FoodMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodMapper foodMapper;
    private final ModelMapper modelMapper;

    @Override
    public void register(FoodDTO foodDTO) {
        FoodVO foodVO = modelMapper.map(foodDTO, FoodVO.class);
        foodMapper.insert(foodVO);

    }

    // mapper , TodoVO 타입 요소로 목록을 받아옴.
    // 화면에 전달하는 타입, TodoDTO 타입으로 변환
    @Override
    public List<FoodDTO> getAll() {
        List<FoodDTO> list = foodMapper.selectAll().stream()
                .map(vo -> modelMapper.map(vo, FoodDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public FoodDTO getOne(Long tno) {
        FoodVO foodVO= foodMapper.selectOne(tno);
        FoodDTO foodDTO = modelMapper.map(foodVO,FoodDTO.class);
        return foodDTO;
    }

    @Override
    public void delete(Long tno) {
        foodMapper.delete(tno);

    }

    @Override
    public void update(FoodDTO foodDTO) {
        FoodVO foodVO = modelMapper.map(foodDTO,FoodVO.class);
        foodMapper.update(foodVO);
    }

    @Override
    public PageResponseDTO<FoodDTO> selectList(PageRequestDTO pageRequestDTO) {
        // 준비물,
        // 서버 -> 웹 , 전달한 준비물 1) 전체갯수 2) 페이징 처리 todo 목록, 3) pageRequestDTO
//        1) 전체갯수
        int total = foodMapper.getCount(pageRequestDTO);
//        2) 페이징 처리 todo 목록 , TodoVO -> TodoDTO 변환해서 전달.
        List<FoodDTO> dtoList = foodMapper.selectList(pageRequestDTO).stream()
                .map(vo -> modelMapper.map(vo,FoodDTO.class))
                .collect(Collectors.toList());
        // 준비물을 가지고 , 응답할 PageResponseDTO, 생성자 통해서 초기화 작업.
        PageResponseDTO<FoodDTO> pageResponseDTO = PageResponseDTO.<FoodDTO>withAll()
                .total(total)
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .build();
        // 기존 방법1, 빌더 패턴
//        PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.builder()
//                .total(total)
//                .dtoList(dtoList)
//                .pageRequestDTO(pageRequestDTO)
//                .build();

        return pageResponseDTO;
    }

}
