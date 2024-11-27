package com.busanit501.helloworld.member;

import com.busanit501.helloworld.jdbcex.dao.TodoDAO;
import com.busanit501.helloworld.jdbcex.vo.TodoVO;
import com.busanit501.helloworld.member.dao.MemberDAO;
import com.busanit501.helloworld.member.vo.MemberVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class MemberDAOTest {
    private MemberDAO memberDAO;

    // 아래에 각 단위 테스트가 실행되기전에, 먼저 실행을 함.
    @BeforeEach
    public void ready() {
        memberDAO = new MemberDAO();
    }

    // 1
    @Test
    public void insetTest() throws Exception {

        MemberVO memberVO = MemberVO.builder()
                .name("박광호")
                .password("123456")
                .birthday(LocalDate.of(1996, 8, 27))
                .dueDate(LocalDate.of(2024, 12, 31))
                .build();

        MemberDAO.insert(memberVO);

    }

    //2, 전체 목록 조회 테스트
    @Test
    public void testList() throws SQLException {
        List<TodoVO> list = todoDAO.selectAll();
        list.forEach(vo -> System.out.println(vo));
    }

    //3, 하나 조회 테스트
    @Test
    public void getOneTest() throws SQLException {
        Long tno = 3L;
        TodoVO todoVO = todoDAO.selectOne(tno);
        System.out.println(todoVO);
    }

    // 4, 삭제 테스트
    @Test
    public void deleteTest() throws SQLException {
        Long tno = 4L;
        todoDAO.deleteTodo(tno);
    }

    // 4, 수정 테스트
    @Test
    public void updateTest() throws SQLException {
        // 실제 작업은 내용을 화면에서 받아오는 대신,
        // 하드 코딩으로 값을 더미로 테스트.
        TodoVO todoVO = TodoVO.builder()
                .tno(3L)
                .title("수정 테스트 중")
                .finished(true)
                .dueDate(LocalDate.of(2024, 11, 25))
                .build();

        todoDAO.updateOne(todoVO);

    }

}// class

