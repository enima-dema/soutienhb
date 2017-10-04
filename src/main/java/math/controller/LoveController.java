package math.controller;

import math.business.User;
import math.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Human Booster on 20/09/2017.
 */
@Controller
public class LoveController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession hs;

    @RequestMapping(value = {"/login", "/"})
    public ModelAndView accueil() {
        ModelAndView mav = new ModelAndView("login");
        mav.getModel().put("isConnected", isConnected());
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView connectPost(@RequestParam Map<String, Object> map) {
        System.out.println("IM IN CONNECT POST");
        User user = userService.getOne((String) map.get("login"));
        if (user != null && map.get("password").equals(user.getPassword())) {
            hs.setAttribute("user", user);
            return home();
        }
        return accueil();
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("home");
        if (isConnected()){
            List<User> users = userService.getAllExceptLoggedUser(((User) hs.getAttribute("user")).getId(), null);
            mav.getModel().put("users", users);
            return mav;
        }
        return accueil();
    }

    @RequestMapping(value = "/user/{id}")
    public ModelAndView showUser(@PathVariable int id) {
        ModelAndView mav = new ModelAndView("user");
        mav.getModel().put("user", userService.getOne(id));
        return mav;
    }


    private boolean isConnected() {
        return hs.getAttribute("user") != null;
    }
}
