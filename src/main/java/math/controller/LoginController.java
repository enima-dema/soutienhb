package math.controller;

import math.business.User;
import math.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * Created by Human Booster on 20/09/2017.
 */
@ManagedBean(name="login")
@SessionScoped
public class LoginController implements Serializable{
    private static final long serialVersionUID = 1L;
    private User user;
    private UserService userService;
    private boolean fail;

    @PostConstruct
    public void init(){
        user = new User();
        user.setLogin("amed");
        user.setPassword("test123");
        userService = new UserService();
        fail = false;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isFail() {
        return fail;
    }

    public void setFail(boolean fail) {
        this.fail = fail;
    }

    public String validateLogin(){
        System.out.println("TEST USER");
        System.out.println(user);
        try {
            User userLogged = userService.getOne(user.getLogin());
            System.out.println(userLogged);
            if (userLogged != null && user.getPassword().equals(userLogged.getPassword())) {
                HttpSession session = SessionView.getSession();
                session.setAttribute("user", userLogged);
                return "home";
            } else {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("There's a mistake");
                message.setDetail("Please, check you creditentials.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                fail = true;
                return "login";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    public String logout(){
        HttpSession session = SessionView.getSession();
        session.invalidate();
        return "login";
    }

    public boolean isConnected(){
        HttpSession session = SessionView.getSession();
        User user = (User) session.getAttribute("user");
        return user != null;
    }
}
