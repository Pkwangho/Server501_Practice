package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.food2.dao.Food2DAO;

import com.busanit501.helloworld.food2.vo.Food2VO;
import com.busanit501.helloworld.jdbcex.vo.TodoVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class FoodDAOTest {
    private Food2DAO food2DAO;

    @BeforeEach
    public void setUp() {
        food2DAO = new Food2DAO();
    }

    @Test
    public  void insertTest() throws SQLException {

        Food2VO food2VO1 = Food2VO.builder()
                .name("샘플 디비 작성 테스트")
                .price(9000.0)
                .dueDate(LocalDate.of(2024,12,31))
                .build();

        food2DAO.insert(food2VO1);
    }
    @Test
    public void testList() throws SQLException {
        List<Food2VO> list = food2DAO.selectAll();
        list.forEach(vo -> System.out.println(vo));
    }

    //3, 하나 조회 테스트
    @Test
    public void getOneTest() throws SQLException {
        Long tno = 3L;
        Food2VO food2VO = food2DAO.selectOne(tno);
        System.out.println(food2VO);
    }

    // 4, 삭제 테스트
    @Test
    public void deleteTest() throws SQLException {
        Long tno = 3L;
        food2DAO.deleteFood(tno);
    }

    // 4, 수정 테스트
    @Test
    public void updateTest() throws SQLException {
        // 실제 작업은 내용을 화면에서 받아오는 대신,
        // 하드 코딩으로 값을 더미로 테스트.
        Food2VO food2VO = Food2VO.builder()
                .tno(4L)
                .name("수정 테스트 중")
                .price(9000.0)
                .finished(true)
                .dueDate(LocalDate.of(2024, 11, 25))
                .build();

            food2DAO.updateOne(food2VO);

    }


}
