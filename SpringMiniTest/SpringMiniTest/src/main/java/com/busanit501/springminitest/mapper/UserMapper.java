package com.busanit501.springminitest.mapper;


import com.busanit501.springminitest.dto.PageRequestDTO;
import com.busanit501.springminitest.domain.UserVO;

import java.util.List;

public interface UserMapper {

    String getTime();
    void insert(UserVO userVO);
    List<UserVO> selectAll();
    UserVO selectOne(Long uno);
    void delete(Long uno);
    void update(UserVO userVO);
    List<UserVO> selectList(PageRequestDTO pageRequestDTO);
    int getCount(PageRequestDTO pageRequestDTO);
}





