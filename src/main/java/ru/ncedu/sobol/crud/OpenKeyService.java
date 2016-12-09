package ru.ncedu.sobol.crud;

import ru.ncedu.sobol.essences.Account;
import ru.ncedu.sobol.essences.Openkey;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by Gregory on 06-Dec-16.
 */
public class OpenKeyService {
    static EntityManager em = Persistence.createEntityManagerFactory("SOBOL").createEntityManager();

    static public Openkey add(Openkey openkey) {
        em.getTransaction().begin();
        Openkey openkeyFromDB = em.merge(openkey);
        em.getTransaction().commit();
        return openkeyFromDB;
    }

    static public Openkey get(int id){
        return em.find(Openkey.class, id);
    }

    static public Openkey getByAccount(int account_id){
        return em.createQuery("select o from openkeys o where account_id = " + account_id, Openkey.class).getSingleResult();
    }

    static public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    static public void update(Openkey openkey){
        em.getTransaction().begin();
        em.merge(openkey);
        em.getTransaction().commit();
    }
}
