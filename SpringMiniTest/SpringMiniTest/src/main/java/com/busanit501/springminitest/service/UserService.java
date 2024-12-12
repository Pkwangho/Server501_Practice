package com.busanit501.springminitest.service;

import com.busanit501.springminitest.dto.PageRequestDTO;
import com.busanit501.springminitest.dto.PageResponseDTO;
import com.busanit501.springminitest.dto.UserDTO;

import java.util.List;

public interface UserService {
    void register(UserDTO userDTO);
    List<UserDTO> getAll();
    PageResponseDTO<UserDTO> selectList(PageRequestDTO pageRequestDTO);
    UserDTO getOne(Long uno);
    void delete(Long uno);
    void update(UserDTO userDTO);
}
