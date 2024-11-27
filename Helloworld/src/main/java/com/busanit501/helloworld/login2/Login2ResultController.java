package com.busanit501.helloworld.login2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login2ResultController", urlPatterns = "/login2/result")

public class Login2ResultController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String useremail = request.getParameter("useremail");
        String password = request.getParameter("password");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1> useremail: " + useremail + "</h1>");
        out.println("<h2> password: " + password + "</h2>");
        out.println("</body></html>");


    }
}
