package ru.ncedu.sobol.essences;

/**
 * Created by Gregory on 24-Nov-16.
 */

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int employee_id;

    int manager_id;
    int user_id;
    int accesses;
    double salary;


    public Employee( int manager_id, int user_id, int accesses, double salary) {
        this.manager_id = manager_id;
        this.user_id = user_id;
        this.accesses = accesses;
        this.salary = salary;
    }

    public Employee() {
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setAccesses(int accesses) {
        this.accesses = accesses;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public int getManager_id() {
        return manager_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getAccesses() {
        return accesses;
    }

    public double getSalary() {
        return salary;
    }
}
