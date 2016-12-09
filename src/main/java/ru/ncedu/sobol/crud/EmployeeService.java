package ru.ncedu.sobol.crud;

import ru.ncedu.sobol.essences.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gregory on 06-Dec-16.
 */
public class EmployeeService {
    static EntityManager em = Persistence.createEntityManagerFactory("SOBOL").createEntityManager();

    static public Employee add(Employee employee) {
        em.getTransaction().begin();
        Employee employeeFromDB = em.merge(employee);
        em.getTransaction().commit();
        return employeeFromDB;
    }

    static public Employee get(int id){
        return em.find(Employee.class, id);
    }

    static public Employee getByUser(int id) {
        return em.find(Employee.class, em.createQuery("select e from Employee e where manager_id = " + id,
                Employee.class).getSingleResult().getEmployee_id());
    }

    static public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    static public void update(Employee employee){
        em.getTransaction().begin();
        em.merge(employee);
        em.getTransaction().commit();
    }

    static public Employee getManager(int id){
        return get(get(id).getManager_id());
    }

    static public List<Employee> getAllSubordinates(int id){
        return em.createQuery("select e from Employee e where manager_id = " + id, Employee.class).getResultList();
    }

    static public List<Employee> getAll() {
        return em.createQuery("select e from Employee e", Employee.class).getResultList();
    }
}