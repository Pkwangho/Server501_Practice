package com.busanit501.helloworld.food;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FoodResultController" , urlPatterns = "/food/result")

public class FoodResultController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //화면 전달.
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/food/food_result.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost : 글쓰기 처리하는 로직, 디비 연결 전 리스트로 이동함");
        response.sendRedirect("/food/input");
    }
}
