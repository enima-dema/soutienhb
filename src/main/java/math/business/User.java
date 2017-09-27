package math.business;

import java.util.Date;

/**
 * Created by Human Booster on 08/09/2017.
 */
public class User {
    private int id;
    private String name;
    private String lastname;
    private String login;
    private String password;
    //Maybe use an enum
    private String sexe;
    private Date birthday;
    private String description;
    private String compatibility;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(String compatibility) {
        this.compatibility = compatibility;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", sexe='" + sexe + '\'' +
                ", birthday=" + birthday +
                ", description='" + description + '\'' +
                ", compatibility='" + compatibility + '\'' +
                '}';
    }

    public String toPresent() {
        return "Nom : " + name + " " + lastname + "/sex : " + sexe + "/description : " + description;
    }
}
