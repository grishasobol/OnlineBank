package ru.ncedu.sobol.essences;

import javax.persistence.*;

/**
 * Created by Gregory on 22-Nov-16.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int user_id;

    String login;
    String password;
    int user_type;

    public User( String login, String password, int user_type) {
        this.login = login;
        this.password = password;
        this.user_type = user_type;
    }

    public User() {
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getUser_type() {
        return user_type;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "" + user_id + " " + login + " " + password + " " + user_type;
    }
}

