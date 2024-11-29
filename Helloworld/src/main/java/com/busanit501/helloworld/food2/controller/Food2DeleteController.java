package com.busanit501.helloworld.food2.controller;

import com.busanit501.helloworld.food2.service.Food2Service;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2 // log.info("이런 형식으로 출력 한다.")
@WebServlet(name = "Food2DeleteController", urlPatterns = "/food/delete2")
public class Food2DeleteController extends HttpServlet {
    // 외주 일 시키기, 누구? 서비스 한테, 선언만,
    private Food2Service food2Service = Food2Service.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long tno = Long.valueOf(request.getParameter("tno"));
        //
        log.info("doPost TodoDeleteController 확인");
        try {
           food2Service.delete(tno);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/food/list2");
    }
}
