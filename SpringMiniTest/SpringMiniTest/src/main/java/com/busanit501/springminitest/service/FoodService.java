package com.busanit501.springminitest.service;

import com.busanit501.springminitest.dto.FoodDTO;
import com.busanit501.springminitest.dto.PageRequestDTO;
import com.busanit501.springminitest.dto.PageResponseDTO;

import java.util.List;

public interface FoodService {
    void register(FoodDTO foodDTO);
    List<FoodDTO> getAll();

//    PageResponseDTO<FoodDTO> getListWithPage(PageRequestDTO pageRequestDTO);
    PageResponseDTO<FoodDTO> selectList(PageRequestDTO pageRequestDTO);

    FoodDTO getOne(Long tno);
    void delete(Long tno);
    void update(FoodDTO foodDTO);

}
