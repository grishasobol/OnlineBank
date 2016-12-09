package ru.ncedu.sobol.crud;

import ru.ncedu.sobol.essences.Account;
import ru.ncedu.sobol.essences.User;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Gregory on 07-Dec-16.
 */
public class UserService {
    static EntityManager em = Persistence.createEntityManagerFactory("SOBOL").createEntityManager();

    static public User add(User user) {
        em.getTransaction().begin();
        User userFromDB = em.merge(user);
        em.getTransaction().commit();
        return userFromDB;
    }

    static public User get(int id){
        return em.find(User.class, id);
    }

    static public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    static public void update(User user){
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    static public List<User> getAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    static public void deleteAll(){
        em.createQuery("DROP TABLE IF EXISTS employees\n" +
                "DROP TABLE IF EXISTS actionhistory\n" +
                "DROP TABLE IF EXISTS openkeys\n" +
                "DROP TABLE IF EXISTS accounts\n" +
                "DROP TABLE IF EXISTS users");
    }

}
