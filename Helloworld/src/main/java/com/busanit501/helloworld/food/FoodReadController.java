package com.busanit501.helloworld.food;

import com.busanit501.helloworld.todo.dto.FoodDTO;
import com.busanit501.helloworld.todo.dto.TodoDTO;
import com.busanit501.helloworld.todo.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FoodReadController",urlPatterns = "/food/read")

public class FoodReadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("doGet TodoReadController 하나 조회 예제");
        Long tno = Long.parseLong(request.getParameter("tno"));

        // 서비스에서, 하나의 todo 더미 데이터를 조회 후,
        TodoDTO foodDTO  = TodoService.INSTANCE.getOne(tno);

        // 화면에 전달하기.
        request.setAttribute("dto", foodDTO);
        request.getRequestDispatcher("/WEB-INF/food/food_Read.jsp")
                .forward(request, response);
    }
}
