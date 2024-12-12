package com.busanit501.springminitest.mapper;

import com.busanit501.springminitest.domain.FoodVO;
import com.busanit501.springminitest.dto.PageRequestDTO;

import java.util.List;

public interface FoodMapper {

    String getTime();

    void insert(FoodVO foodVO);

    List<FoodVO> selectAll();

    FoodVO selectOne(Long tno);

    void delete(Long tno);

    void update(FoodVO foodVO);

    List<FoodVO> selectList(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);

}

