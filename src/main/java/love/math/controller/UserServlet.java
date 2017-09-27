package love.math.controller;

import love.math.business.Answer;
import love.math.business.Choice;
import love.math.business.Question;
import love.math.business.User;
import love.math.service.AnswerService;
import love.math.service.QuestionService;
import love.math.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService us = new UserService();
        AnswerService as = new AnswerService();
        QuestionService qs = new QuestionService();
        HttpSession httpSession = request.getSession();
        User userConnected = (User) httpSession.getAttribute("user");
        User user = us.getOne(Integer.valueOf(request.getParameter("userId")), userConnected);
        System.out.println("TEST USER");
        System.out.println(user);
        request.setAttribute("user", user);
        List<Answer> answers = as.getAllAnswerFromUser(user.getId());
        LinkedHashMap<Question, Choice> toShow = new LinkedHashMap<>();
        for(Answer answer : answers){
            Question question = qs.getQuestionFromChoice(answer.getChoice().getId());
            Choice choice = answer.getChoice();
            toShow.put(question, choice);
        }
        request.setAttribute("questions", toShow);
        request.getRequestDispatcher("user.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
