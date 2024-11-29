package com.busanit501.helloworld.food2.controller;

import com.busanit501.helloworld.food2.dto.Food2MemberDTO;
import com.busanit501.helloworld.food2.service.Food2MemberService;

import com.busanit501.helloworld.member.dto.MemberDTO;
import com.busanit501.helloworld.member.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@Log4j2
@WebServlet(name = "Food2LoginController", urlPatterns = "/login2")
public class Food2LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("LoginController doGet ");
        request.getRequestDispatcher("/WEB-INF/food2/login.jsp")
                .forward(request, response);
    }

    // 로직 처리,
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("LoginController doPost ");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        // auto, 자동로그인 체크 여부, -> 결과 문자열 : "on"
        String auto = request.getParameter("auto");
        // 상태 변수,
        boolean rememberMe = auto != null && auto.equals("on");



        // 디비에가서, 해당 유저가 있으면, 임시로 세션에 저장,
        // 예외처리도 없음.
        // 조금있다 할 예정.
        // 임의로 세션 동작 여부만 확인중.
        try {
            Food2MemberDTO food2memberDTO = Food2MemberService.INSTANCE.login(name,password);

            if (rememberMe) {
                String uuid = UUID.randomUUID().toString();
                // 디비에 uuid 업데이트 하는 경우이고,
                MemberService.INSTANCE.updateUuid(name, uuid);
                // 현재 로그인한 memberDTO, uuid 업데이트가 안된 상태임.
                // 업데이트 해주고, 세션에 저장할 예정.
                food2memberDTO.setUuid(uuid);

                // 쿠키를 생성해서, 웹브라우저에게 전달.
                Cookie rememberCookie = new Cookie("rememberMe", uuid);
                rememberCookie.setPath("/");
                rememberCookie.setMaxAge(60*60*24*7);
                response.addCookie(rememberCookie);
            }

            // 세션에, 위의 로그인 정보를 저장,
            HttpSession session = request.getSession();
            session.setAttribute("loginInfo2", food2memberDTO);
            response.sendRedirect("/food/list2");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("/login2?result=error");
        }

    }
}
