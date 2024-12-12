package com.busanit501.springex.sample.service;

import com.busanit501.springminitest.dto.PageRequestDTO;
import com.busanit501.springminitest.dto.PageResponseDTO;
import com.busanit501.springminitest.dto.UserDTO;
import com.busanit501.springminitest.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class) //JUnit5 테스트 설정.

@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")

public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testRegister() {
        UserDTO userDTO = UserDTO.builder()
                .name("샘플데이터 서비스에서 입력")
                .password("123456")
                .dueDate(LocalDate.now())
                .build();
        userService.register(userDTO);
    } //

    @Test
    public void testGetAll() {
        List<UserDTO> list = userService.getAll();
        for (UserDTO userDTO : list) {
            log.info("userDTO : " + userDTO);
        }
    } //

    @Test
    public void testGetOne() {
        UserDTO userDTO = userService.getOne(9L);
        log.info("userDTO : " + userDTO);

    } //

    @Test
    public void testDelete() {
        userService.delete(8L);
    } //

    @Test
    public void testUpdate() {
        // 업데이트 할 더미 데이터 필요, TodoVO
        UserDTO userDTO = UserDTO.builder()
                .uno(5L)
                .name("수정 제목")
                .password("수정 비밀번호2")
                .dueDate(LocalDate.now())
                .finished(true)
                .build();

        userService.update(userDTO);
    }

    @Test
    public void testPageList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .keyword("수정")
                .types(new String[]{"t","w"})
                .from(LocalDate.of(2024,12,12))
                .to(LocalDate.of(2024,12,13))
                .finished2(true)
                .build();
        PageResponseDTO<UserDTO> list = userService.selectList(pageRequestDTO);
        list.getDtoList().stream().forEach(dto -> log.info("dto : " + dto));
        log.info("list total : " + list.getTotal());
        log.info("list prev : " + list.isPrev());
        log.info("list next : " + list.isNext());
        log.info("list start : " + list.getStart());
        log.info("list end : " + list.getEnd());

    }

}//
