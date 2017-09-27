package math.controller;

import math.business.Answer;
import math.business.Choice;
import math.business.Question;
import math.business.User;
import math.service.AnswerService;
import math.service.QuestionService;
import math.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Human Booster on 20/09/2017.
 */
@ManagedBean(name = "userBean")
@SessionScoped
public class UserController implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private User user;
    private UserService us;
    private AnswerService as;
    private QuestionService qs;
    private LinkedHashMap<Question, Choice> questions;

    @PostConstruct
    public void init() {
        us = new UserService();
        as = new AnswerService();
        qs = new QuestionService();
        questions = new LinkedHashMap<>();
    }

    public String seeUser() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        User userConnected = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        int id = Integer.valueOf(params.get("id"));
        user = us.getOne(id, userConnected);

        List<Answer> answers = as.getAllAnswerFromUser(user.getId());
        for (Answer answer : answers) {
            Question question = qs.getQuestionFromChoice(answer.getChoice().getId());
            Choice choice = answer.getChoice();
            questions.put(question, choice);
        }



        return "user";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LinkedHashMap<Question, Choice> getQuestions() {
        return questions;
    }

    public void setQuestions(LinkedHashMap<Question, Choice> questions) {
        this.questions = questions;
    }
}
