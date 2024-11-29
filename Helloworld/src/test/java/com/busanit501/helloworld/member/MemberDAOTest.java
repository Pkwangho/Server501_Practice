package com.busanit501.helloworld.member;


import com.busanit501.helloworld.member.dao.MemberDAO;
import com.busanit501.helloworld.member.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Log4j2
public class MemberDAOTest {
    private MemberDAO memberDAO;

    // 아래에 각 단위 테스트가 실행되기전에, 먼저 실행을 함.
    @BeforeEach
    public void ready() {
        memberDAO = new MemberDAO();
    }

    // 1
    @Test
    public void insertTest() throws Exception {

        MemberVO memberVO = MemberVO.builder()
                .name("박광호")
                .password("123456")
                .dueDate(LocalDate.of(2024, 12, 31))
                .build();

        memberDAO.insert(memberVO);
    }

    //2, 전체 목록 조회 테스트
    @Test
    public void testList() throws SQLException {
        List<MemberVO> list = memberDAO.selectAll();
        list.forEach(vo -> System.out.println(vo));
    }

    //3, 하나 조회 테스트
    @Test
    public void getOneTest() throws SQLException {
        Long tno = 3L;
        MemberVO memberVO = memberDAO.selectOne(tno);
        System.out.println(memberVO);
    }

    // 4, 삭제 테스트
    @Test
    public void deleteTest() throws SQLException {
        Long tno = 4L;
        memberDAO.deleteMember(tno);
    }

     // 4, 수정 테스트
    @Test
    public void updateTest() throws SQLException {
        // 실제 작업은 내용을 화면에서 받아오는 대신,
        // 하드 코딩으로 값을 더미로 테스트.
        MemberVO memberVO = MemberVO.builder()
                .tno(3L)
                .name("수정 테스트 중")
                .password("1234567")
                .finished(false)
                .dueDate(LocalDate.of(2024, 11, 25))
                .build();
        memberDAO.updateOne(memberVO);
    }

    @Test
    public void updateUuidTest() throws SQLException {
        String uuid = UUID.randomUUID().toString();
        log.info("uuid 랜덤 문자열 샘플 : " + uuid);
        memberDAO.updateUuid("박광호",uuid);
    }

}// class

