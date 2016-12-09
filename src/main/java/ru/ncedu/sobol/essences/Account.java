package ru.ncedu.sobol.essences;

import javax.persistence.*;

/**
 * Created by Gregory on 22-Nov-16.
 */

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int account_id;
    int user_id;

    public void setMoney(double money) {
        this.money = money;
    }

    public double getMoney() {

        return money;
    }

    String name;
    double money;

    public Account(int user_id, String name, double money) {
        this.user_id = user_id;
        this.name = name;
        this.money = money;
    }

    public Account() {
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }
}
