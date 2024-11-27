package com.busanit501.helloworld.member.controller;


import com.busanit501.helloworld.member.dto.MemberDTO;
import com.busanit501.helloworld.member.service.MemberService;
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
@WebServlet(name = "MemberlistController", urlPatterns = "/member/list")
public class MemberlistController extends HttpServlet {
    // 외주 일 시키기, 누구? 서비스 한테, 선언만,
    private MemberService memberService = MemberService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //
        log.info("doGet TodoList2Controller 확인");
        try {
            // 서비스에 외주 주고, 전체 목록 리스트 받아오기.
            List<MemberDTO> memberList = memberService.listAll();
            // 화면에 데이터 전달. + 화면에 데이터 탑재된 화면을 -> 웹브라우저에게 전달.
            request.setAttribute("list", memberList);
            request.getRequestDispatcher("/WEB-INF/member/memberList.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
