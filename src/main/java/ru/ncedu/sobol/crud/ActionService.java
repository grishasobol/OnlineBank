package ru.ncedu.sobol.crud;

import ru.ncedu.sobol.essences.Action;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by Gregory on 06-Dec-16.
 */
public class ActionService {
    static EntityManager em = Persistence.createEntityManagerFactory("SOBOL").createEntityManager();

    static public Action add(Action action) {
        em.getTransaction().begin();
        Action actionFromDB = em.merge(action);
        em.getTransaction().commit();
        return actionFromDB;
    }

    static public Action get(int id){
        return em.find(Action.class, id);
    }

    static public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    static public void update(Action action){
        em.getTransaction().begin();
        em.merge(action);
        em.getTransaction().commit();
    }
}
