package ru.ncedu.sobol.essences;

import javax.persistence.*;

/**
 * Created by Gregory on 24-Nov-16.
 */
@Entity
@Table(name = "openkeys")
public class Openkey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int open_key_id;

    int account_id;
    String open_key;
    String macadress;

    public Openkey( int account_id, String open_key, String macadress) {
        this.open_key = open_key;
        this.macadress = macadress;
        this.account_id = account_id;
    }

    public Openkey() {
    }

    public void setOpen_key(String open_key) {
        this.open_key = open_key;
    }

    public void setMacadress(String macadress) {
        this.macadress = macadress;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getOpen_key_id() {
        return open_key_id;
    }

    public String getOpen_key() {
        return open_key;
    }

    public String getMacadress() {
        return macadress;
    }

    public int getAccount_id() {
        return account_id;
    }
}
