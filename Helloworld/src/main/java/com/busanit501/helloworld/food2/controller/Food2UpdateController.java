package com.busanit501.helloworld.food2.controller;

import com.busanit501.helloworld.food2.dto.Food2DTO;
import com.busanit501.helloworld.food2.service.Food2Service;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Log4j2
@WebServlet(name = "Food2UpdateController",urlPatterns = "/food/update2")
public class Food2UpdateController extends HttpServlet {
    // 서비스를 포함 하기. 의존하기.
    private Food2Service food2Service = Food2Service.INSTANCE;
    // 날짜 포맷팅
    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //화면 전달.
        // 서비스에서, 하나의 todo 더미 데이터를 조회 후,
        try {
            // 클릭한 게시글 번호를 가지고 와야함.
            Long tno = Long.parseLong(request.getParameter("tno"));
            Food2DTO food2DTO = food2Service.get(tno);
            // 화면에 전달하기.
            request.setAttribute("dto", food2DTO);
            request.getRequestDispatcher("/WEB-INF/food2/food2Upd.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 글 작성 로직 처리.
    // 화면에서 데이터 전달받고, -> DTO 담아서, -> 서비스로 전달.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // PRG 패턴,
        // POST 처리 후, Redirect , Get 호출,
        // 무한 post 방지 효과, 화면 전환 효과.
        // 임시로 담을  DTO 인스턴스 필요함.

        // 넘어온 값의 형태 : 문자열 : "on"
        String finished = request.getParameter("finished");
        log.info("finished : " + finished);
        boolean checkFinished = false;
        if(finished.equals("on")){
            checkFinished = true;
        }
        Food2DTO food2DTO = Food2DTO.builder()
                .tno(Long.valueOf(request.getParameter("tno")))
                .name(request.getParameter("name"))
                .price(Double.parseDouble(request.getParameter("price")))
                .dueDate(LocalDate.parse(request.getParameter("dueDate"),DATE_TIME_FORMATTER))
                .finished(checkFinished)
                .build();
        log.info("food2DTO 수정된 내용: " + food2DTO);
        // Controller -> Service
        try {
            food2Service.update(food2DTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("doPost : 글쓰기 처리하는 로직, 디비 연결 전, 리스트로 이동함");
        response.sendRedirect("/food/list2");

    }
}
