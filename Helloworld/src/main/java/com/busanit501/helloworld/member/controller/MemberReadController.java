package com.busanit501.helloworld.member.controller;

import com.busanit501.helloworld.jdbcex.dto.TodoDTO;
import com.busanit501.helloworld.jdbcex.service.TodoService;
import com.busanit501.helloworld.member.dto.MemberDTO;
import com.busanit501.helloworld.member.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "MemberReadController", urlPatterns = "/member/read")
public class MemberReadController extends HttpServlet {
    private MemberService memberService = MemberService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("doGet TodoRead2Controller 하나 조회 예제");

        // 서비스에서, 하나의 todo 더미 데이터를 조회 후,
        try {
            // 클릭한 게시글 번호를 가지고 와야함.
            Long tno = Long.parseLong(request.getParameter("tno"));
            MemberDTO memberDTO = memberService.get(tno);
            // 화면에 전달하기.
            request.setAttribute("dto", memberDTO);
            request.getRequestDispatcher("/WEB-INF/member/memberRead.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}