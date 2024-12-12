package com.busanit501.springex.sample.mapper;

import com.busanit501.springminitest.domain.FoodVO;
import com.busanit501.springminitest.dto.PageRequestDTO;
import com.busanit501.springminitest.mapper.FoodMapper;
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
public class FoodMapperTest {

    // 해당 인스턴스가 없다면, 널로 받을게.
    // 기본값은 required = true
    @Autowired(required = false)
    private FoodMapper foodMapper;

    @Test
    public void testGetTime() {
        log.info("getTime : " + foodMapper.getTime());
    }

    @Test
    public void testInsert() {
        // 더미 데이터 , TodoVO 담아서, 진행.
        FoodVO foodVO = FoodVO.builder()
                .name("샘플 테스트")
                .price(9000.0)
                .dueDate(LocalDate.now())
                .build();
        foodMapper.insert(foodVO);
    }

    @Test
    public void testSelectAll() {
        List<FoodVO> lists = foodMapper.selectAll();
        for (FoodVO foodVo :lists) {
            log.info("foodVo : " + foodVo);
        }
    }
    @Test
    public void testSelectOne() {
        FoodVO  foodVo = foodMapper.selectOne(4L);
        log.info("foodVo : " + foodVo);
    }
    @Test
    public void testDelete() {
        foodMapper.delete(4L);
    }

    @Test
    public void testUpdate() {
        // 업데이트 할 더미 데이터 필요, TodoVO
        FoodVO foodVO = FoodVO.builder()
                .tno(6L)
                .name("수정 제목")
                .price(9000.0)
                .dueDate(LocalDate.now())
                .finished(true)
                .build();
        foodMapper.update(foodVO);
    }

    @Test
    public void testSelectAllWithPage() {
        // 페이징 준비물을 담은 PageRequestDTO 필요함,
        // 더미로 PageRequestDTO 만들고,
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .keyword("수정")
                .types(new String[]{"t","w"})
                .from(LocalDate.of(2024,12,05))
                .to(LocalDate.of(2024,12,06))
                .finished(true)
                .build();
        // 검색, 필터 조건 sql 작성중
        // where , and 조건이 어떻게 sql 문장이 구성 되는지 각각의 과정을 보기.

        List<FoodVO> list = foodMapper.selectList(pageRequestDTO);
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
                .from(LocalDate.of(2024,12,05))
                .to(LocalDate.of(2024,12,06))
                .finished(true)                .build();
        int total = foodMapper.getCount(pageRequestDTO);
        log.info("total : " + total);
    }




}
