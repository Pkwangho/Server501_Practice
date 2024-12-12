package com.busanit501.springex.sample.service;

import com.busanit501.springminitest.dto.FoodDTO;
import com.busanit501.springminitest.dto.PageRequestDTO;
import com.busanit501.springminitest.dto.PageResponseDTO;
import com.busanit501.springminitest.service.FoodService;
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
//방법2
//@RequiredArgsConstructor
public class FoodServiceTest {

    // 방법1
    @Autowired
    private FoodService foodService;

    //방법2
//    private final TodoService todoService;

    @Test
    public void testRegister() {
        FoodDTO foodDTO = FoodDTO.builder()
                .name("샘플데이터 서비스에서 입력")
                .price(9000.0)
                .dueDate(LocalDate.now())
                .build();
        foodService.register(foodDTO);
    } //

    @Test
    public void testGetAll() {
        List<FoodDTO> list = foodService.getAll();
        for (FoodDTO foodDTO:list) {
            log.info("foodDTO : " + foodDTO);
        }
    } //

    @Test
    public void testGetOne() {
        FoodDTO foodDTO = foodService.getOne(4L);
        log.info("foodDTO : " + foodDTO);
    } //

    @Test
    public void testDelete() {
        foodService.delete(8L);
    }

    @Test
    public void testUpdate() {
        // 업데이트 할 더미 데이터 필요, TodoVO
        FoodDTO foodDTO = FoodDTO.builder()
                .tno(5L)
                .name("수정 제목")
                .price(9000.0)
                .dueDate(LocalDate.now())
                .finished(true)
                .build();
        foodService.update(foodDTO);
    }

    @Test
    public void testPageList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .keyword("수정")
                .types(new String[]{"t","w"})
                .from(LocalDate.of(2024,12,05))
                .to(LocalDate.of(2024,12,06))
                .finished(true)
                .build();
        // PageResponseDTO, 안에는 , page, size, skip, start,end,
        // prev, next,  페이징된 목록 요소들
        PageResponseDTO<FoodDTO> list = foodService.selectList(pageRequestDTO);
        list.getDtoList().stream().forEach(dto -> log.info("dto : " + dto));
        log.info("list total : " + list.getTotal());
        log.info("list prev : " + list.isPrev());
        log.info("list next : " + list.isNext());
        log.info("list start : " + list.getStart());
        log.info("list end : " + list.getEnd());

    }




}//
