package com.busanit501.springminitest.service;

import com.busanit501.springminitest.domain.UserVO;
import com.busanit501.springminitest.dto.PageRequestDTO;
import com.busanit501.springminitest.dto.PageResponseDTO;
import com.busanit501.springminitest.dto.UserDTO;
import com.busanit501.springminitest.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;
    private final ModelMapper modelMapper;

    @Override
    public void register(UserDTO userDTO) {
        UserVO userVO = modelMapper.map(userDTO, UserVO.class);
        userMapper.insert(userVO);
    }
    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> list = userMapper.selectAll().stream()
                .map(vo -> modelMapper.map(vo,UserDTO.class))
                .collect(Collectors.toList());
        return list;
    }
    @Override
    public UserDTO getOne(Long uno) {
        UserVO userVO= userMapper.selectOne(uno);
        UserDTO userDTO = modelMapper.map(userVO,UserDTO.class);
        return userDTO;
    }
    @Override
    public void delete(Long uno) {
        userMapper.delete(uno);
    }
    @Override
    public void update(UserDTO userDTO) {
        UserVO userVO = modelMapper.map(userDTO,UserVO.class);
        userMapper.update(userVO);
    }
    @Override
    public PageResponseDTO<UserDTO> selectList(PageRequestDTO pageRequestDTO) {
        int total = userMapper.getCount(pageRequestDTO);
        List<UserDTO> dtoList = userMapper.selectList(pageRequestDTO).stream()
                .map(vo -> modelMapper.map(vo,UserDTO.class))
                .collect(Collectors.toList());
        PageResponseDTO<UserDTO> pageResponseDTO = PageResponseDTO.<UserDTO>withAll()
                .total(total)
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .build();
        return pageResponseDTO;
    }
}
