package ru.ncedu.sobol.crud;

import ru.ncedu.sobol.essences.Account;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by Gregory on 06-Dec-16.
 */
public class AccountService {
    static EntityManager em = Persistence.createEntityManagerFactory("SOBOL").createEntityManager();

    static public Account add(Account account) {
        em.getTransaction().begin();
        Account accountFromDB = em.merge(account);
        em.getTransaction().commit();
        return accountFromDB;
    }

    static public Account get(int id){
        return em.find(Account.class, id);
    }

    static public Account getByUser(int user_id){
        return em.createQuery("select a from Account a where user_id = " + user_id, Account.class).getSingleResult();
    }

    static public void delete(int id){
        em.getTransaction().begin();
        Account account = get(id);
        em.remove(OpenKeyService.get(account.getAccount_id()));
        em.remove(account);
        em.getTransaction().commit();
    }

    static public void update(Account account){
        em.getTransaction().begin();
        em.merge(account);
        em.getTransaction().commit();
    }
}
