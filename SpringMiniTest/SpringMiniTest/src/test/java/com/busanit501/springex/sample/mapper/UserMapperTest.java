package com.busanit501.springex.sample.mapper;


import com.busanit501.springminitest.domain.UserVO;
import com.busanit501.springminitest.dto.PageRequestDTO;
import com.busanit501.springminitest.mapper.UserMapper;
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
//JUnit4 테스트 설정. @Runwith
// 설정 파일의 경로를 지정.
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class UserMapperTest {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Test
    public void testGetTime() {
        log.info("getTime : " + userMapper.getTime());
    }

    @Test
    public void testInsert() {
        // 더미 데이터 , TodoVO 담아서, 진행.
        UserVO userVO = UserVO.builder()
                .name("국밥")
                .password("pig")
                .dueDate(LocalDate.now())
                .build();
        userMapper.insert(userVO);
    }

    @Test
    public void testSelectAll() {
        List<UserVO> lists = userMapper.selectAll();
        for (UserVO userVo:lists) {
            log.info("userVo : " + userVo);
        }
    }

    @Test
    public void testSelectOne() {
        UserVO  userVo = userMapper.selectOne(2L);
            log.info("userVo : " + userVo);
    }

    @Test
    public void testDelete() {
        userMapper.delete(3L);
    }

    @Test
    public void testUpdate() {
        // 업데이트 할 더미 데이터 필요, TodoVO
        UserVO userVO = UserVO.builder()
                .uno(6L)
                .name("수정 제목")
                .password("수정 페스워드")
                .dueDate(LocalDate.now())
                .finished(true)
                .build();

        userMapper.update(userVO);
    }

    @Test
    public void testSelectAllWithPage() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .keyword("수정")
                .types(new String[]{"t","w"})
                .from(LocalDate.of(2024,12,12))
                .to(LocalDate.of(2024,12,13))
                .finished(true)
                .build();

        List<UserVO> list = userMapper.selectList(pageRequestDTO);
        list.forEach(vo -> log.info("vo : " + vo));
    }

    // 페이징 처리해서 전체 갯수 조회
    @Test
    public void testGetCount() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .keyword("수정")
                .types(new String[]{"t","w"})
                .from(LocalDate.of(2024,12,12))
                .to(LocalDate.of(2024,12,13))
                .finished(true)                .build();
        int total = userMapper.getCount(pageRequestDTO);
        log.info("total : " + total);
    }

}
