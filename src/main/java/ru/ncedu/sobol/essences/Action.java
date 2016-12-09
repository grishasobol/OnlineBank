package ru.ncedu.sobol.essences;

import javax.persistence.*;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Gregory on 24-Nov-16.
 */
@Entity
@Table(name = "actionhistory")
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int action_id;

    Date data;
    int user_id;
    String action;

    public Action(Date data, int user_id, String action) {
        this.data = data;
        this.user_id = user_id;
        this.action = action;
    }

    public Action() {
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getData() {
        return data;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getAction() {
        return action;
    }
}
