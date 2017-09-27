package math.controller;

import math.business.User;
import math.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Human Booster on 20/09/2017.
 */
@ManagedBean(name="home")
@SessionScoped
public class HomeController implements Serializable{
    private static final long serialVersionUID = 1L;
    private List<User> users;
    private UserService us;

    @PostConstruct
    public void init(){
        us = new UserService();
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        users = us.getAllExceptLoggedUser(user.getId(), user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
