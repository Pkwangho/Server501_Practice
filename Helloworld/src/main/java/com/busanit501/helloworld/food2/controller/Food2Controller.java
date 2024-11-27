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
import java.util.List;

@Log4j2 // log.info("이런 형식으로 출력 한다.")
@WebServlet(name = "Food2Controller", urlPatterns = "/food/list2")
public class Food2Controller extends HttpServlet {
    // 외주 일 시키기, 누구? 서비스 한테, 선언만,
    private Food2Service food2Service = Food2Service.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //
        log.info("doGet Food2ListController 확인");
        try {
            // 서비스에 외주 주고, 전체 목록 리스트 받아오기.
            List<Food2DTO> food2List = Food2Service.INSTANCE.listAll();
            // 화면에 데이터 전달. + 화면에 데이터 탑재된 화면을 -> 웹브라우저에게 전달.
            request.setAttribute("list", food2List);
            request.getRequestDispatcher("/WEB-INF/food2/food2List.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
