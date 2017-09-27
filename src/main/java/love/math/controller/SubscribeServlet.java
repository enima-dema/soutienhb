package love.math.controller;

import love.math.business.User;
import love.math.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SubscribeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        System.out.println(login);
        System.out.println(password);
        UserService us = new UserService();
        User user = us.getOne(login);
        if (user != null && password.equals(user.getPassword())){
            request.setAttribute("user", user);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }else {
            request.setAttribute("fail", true);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
