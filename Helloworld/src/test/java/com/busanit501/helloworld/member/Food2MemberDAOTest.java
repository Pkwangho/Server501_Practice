package com.busanit501.helloworld.member;

import com.busanit501.helloworld.food2.dao.Food2MemberDAO;
import com.busanit501.helloworld.food2.vo.Food2MemberVO;
import com.busanit501.helloworld.jdbcex.dao.MemberDAO;
import com.busanit501.helloworld.jdbcex.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;

@Log4j2

public class Food2MemberDAOTest {

    private Food2MemberDAO food2memberDAO;

    // 아래에 각 단위 테스트가 실행되기전에, 먼저 실행을 함.
    @BeforeEach
    public void ready() {
        food2memberDAO = new Food2MemberDAO();
    }

    @Test
    public void getMemberWithMpwTest() throws SQLException {
        String name = "박광호";
        String password = "1234";
        Food2MemberVO food2memberVO = food2memberDAO.getMemberWithMpw(name,password);
        log.info("food2memberVO 조회 확인: " +food2memberVO );
    }
    @Test
    public void updateUuidTest() throws SQLException {
        String uuid = UUID.randomUUID().toString();
        log.info("uuid 랜덤 문자열 샘플 : " + uuid);
        food2memberDAO.updateUuid("박광호",uuid);
    }

    @Test
    public void getMemberWithUuidTest() throws SQLException {
        // 각자 테이블의 유저의uuid를 직접 복사해서 붙여넣기.
        // 각각 전부 다 달라요.
        Food2MemberVO food2memberVO = food2memberDAO.getMemberWithUuid("696a5e51-4475-4f35-a37d-4f4a5dd0866a");
        log.info("memberVO : " + food2memberDAO);
    }

}

