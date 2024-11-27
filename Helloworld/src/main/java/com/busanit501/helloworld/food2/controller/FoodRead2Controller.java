package com.busanit501.helloworld.food2.controller;

import com.busanit501.helloworld.food2.dto.Food2DTO;
import com.busanit501.helloworld.food2.service.Food2Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "FoodRead2Controller", urlPatterns = "/food/read2")
public class FoodRead2Controller extends HttpServlet {
    private Food2Service food2Service = Food2Service.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("doGet TodoRead2Controller 하나 조회 예제");

        // 서비스에서, 하나의 todo 더미 데이터를 조회 후,
        try {
            // 클릭한 게시글 번호를 가지고 와야함.
            Long tno = Long.parseLong(request.getParameter("tno"));
            Food2DTO food2DTO = food2Service.get(tno);
            // 화면에 전달하기.
            request.setAttribute("dto", food2DTO);
            request.getRequestDispatcher("/WEB-INF/food2/food2Read.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
