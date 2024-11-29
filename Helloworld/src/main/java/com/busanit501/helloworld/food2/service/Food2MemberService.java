package com.busanit501.helloworld.food2.service;

import com.busanit501.helloworld.food2.dao.Food2MemberDAO;
import com.busanit501.helloworld.food2.dto.Food2MemberDTO;
import com.busanit501.helloworld.food2.vo.Food2MemberVO;

import com.busanit501.helloworld.member.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;


// 설정1
@Log4j2
public enum Food2MemberService {
    INSTANCE;

    private Food2MemberDAO food2memberDAO;
    private ModelMapper modelMapper;

    // 생성자 이용해서, 초기화하기.
    Food2MemberService() {
        food2memberDAO = new Food2MemberDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public Food2MemberDTO login(String name, String password) throws SQLException {
       Food2MemberVO food2memberVO = food2memberDAO.getMemberWithMpw(name,password);
        Food2MemberDTO food2memberDTO = modelMapper.map(food2memberVO, Food2MemberDTO.class);
        return food2memberDTO;
    }
    public void updateUuid(String name, String uuid) throws SQLException {
        food2memberDAO.updateUuid(name,uuid);
    }

    public Food2MemberDTO getMemberWithUuidService(String uuid) throws SQLException {
        Food2MemberVO food2memberVO= food2memberDAO.getMemberWithUuid(uuid);
        Food2MemberDTO food2memberDTO = modelMapper.map(food2memberVO, Food2MemberDTO.class);
        return food2memberDTO;
    }



}






