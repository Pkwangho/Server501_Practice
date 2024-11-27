package com.busanit501.helloworld.food;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FoodInputController" , urlPatterns = "/food/input")

public class FoodInputController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //화면 전달.
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/food/food_input.jsp");
        dispatcher.forward(request,response);
    }
    
}
