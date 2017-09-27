package math.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean(name="session")
@SessionScoped
public class SessionView implements Serializable {
    private static final long serialVersionUID = 1L;
    static HttpSession session;

    public static HttpSession getSession(){
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
                .getSession(false);
        return session;
    }

}