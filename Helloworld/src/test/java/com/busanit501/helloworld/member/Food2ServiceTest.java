package com.busanit501.helloworld.member;

import com.busanit501.helloworld.food2.dto.Food2DTO;
import com.busanit501.helloworld.food2.service.Food2Service;

import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
@Log4j2
public class Food2ServiceTest {
    private Food2Service food2Service;

    @BeforeEach
    public void ready(){
        food2Service =  Food2Service.INSTANCE;

    }

    @Test
    public void testInsert() throws SQLException {
        Food2DTO food2DTO = Food2DTO.builder()
                .name("샘플 작업 1126")
                .price(9000.0)
                .dueDate(LocalDate.now())
                .build();
        food2Service.register(food2DTO);
    }

    @Test
    public void testSelectAll() throws SQLException {
        List<Food2DTO> dtoList = food2Service.listAll();
        for (Food2DTO food2DTO:dtoList) {
            log.info(food2DTO);
        }
    }
    @Test
    public void testSelectOne() throws SQLException {
        val food2DTO = food2Service.get(4L);
        log.info("하나 조회. todoDTO " + food2DTO);
    }

    @Test
    public void testUpdate() throws SQLException {
        Food2DTO food2DTO = Food2DTO.builder()
                .tno(8L)
                .name("수정된 내용입니다.")
                .price(7000.0)
                .dueDate(LocalDate.now())
                .finished(false)
                .build();

        food2Service.update(food2DTO);
    }

    @Test
    public void testDelteOne() throws SQLException {
        food2Service.delete(8L);
    }


}


