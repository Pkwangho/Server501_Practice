package com.busanit501.helloworld.member;

import com.busanit501.helloworld.food2.dto.Food2MemberDTO;
import com.busanit501.helloworld.food2.service.Food2MemberService;
import com.busanit501.helloworld.jdbcex.dto.MemberDTO;
import com.busanit501.helloworld.jdbcex.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;

@Log4j2

public class Food2MemberServiceTest {

    private Food2MemberService food2memberService;

    @BeforeEach
    public void ready() {
        food2memberService = Food2MemberService.INSTANCE;
    }

    // 등록
    @Test
    public void loginTest() throws SQLException {
        Food2MemberDTO food2MemberDTO = food2memberService.login("박광호", "1234");
        log.info("MemberService loginTest : " + food2MemberDTO.toString());
    }

    @Test
    public void updateUuidTest() throws SQLException {
        String uuid = UUID.randomUUID().toString();
        food2memberService.updateUuid("박광호2",uuid);
    }

    @Test
    public void getMemberWithUuidSearch() throws SQLException {
        // 각자 테이블의 유저의uuid를 직접 복사해서 붙여넣기.
        // 각각 전부 다 달라요.
        food2memberService.getMemberWithUuidService("696a5e51-4475-4f35-a37d-4f4a5dd0866a");
        Food2MemberDTO food2memberDTO = food2memberService.getMemberWithUuidService("696a5e51-4475-4f35-a37d-4f4a5dd0866a");
        log.info("food2memberDTO : " + food2memberDTO);
    }

}
