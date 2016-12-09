package ru.ncedu.sobol.sql;

/**
 * Created by Gregory on 05-Dec-16.
 */

import ru.ncedu.sobol.essences.*;
import ru.ncedu.sobol.crud.*;

import java.util.Date;
import java.util.List;

public class SQLHandler {
    int my_user_id;
    int my_employee_id;
    int my_account_id;
    int my_open_key_id;

    public int getMy_user_id() {
        return my_user_id;
    }

    public int getMy_employee_id() {
        return my_employee_id;
    }

    public int getMy_account_id() {
        return my_account_id;
    }

    public int getMy_open_key_id() {
        return my_open_key_id;
    }

    public SQLHandler(int user_id){
        my_user_id = user_id;
        try {
            Employee employee = EmployeeService.getByUser(user_id);
            my_employee_id = employee.getEmployee_id();
        } catch(Exception e) {
            my_employee_id = -1;
        }

        try {
            Account account = AccountService.getByUser(user_id);
            my_account_id = account.getAccount_id();
        } catch (Exception e) {
            my_employee_id = -1;
        }
        try {
            my_open_key_id = OpenKeyService.getByAccount(my_account_id).getOpen_key_id();
        } catch (Exception e){
            my_open_key_id = -1;
        }
    }

    public User getUser(int user_id){
        return UserService.get(user_id);
    }

    public Account getAccount(int user_id){
        return AccountService.getByUser(user_id);
    }

    public Employee getEmployee(int user_id){
        return EmployeeService.getByUser(user_id);
    }

    public List<User> getAllUsers() {
        return UserService.getAll();
    }

    public void addMoney(int user_id, double money){
        Account account = AccountService.getByUser(user_id);
        account.setMoney(account.getMoney() + money);
        AccountService.update(account);
    }

    public void addUser(String login, String password, int user_type, String pub_key){
        User user = new User(login, password, user_type);
        user = UserService.add(user);
        Account account = new Account(user.getUser_id(), login, 0);
        account = AccountService.add(account);
        OpenKeyService.add(new Openkey(account.getAccount_id(),pub_key, ""));
    }

    public void addEmployee(int manager_id, int user_id, int accesses, double salary){
        EmployeeService.add(new Employee(manager_id, user_id, accesses, salary));
    }

    public void addAction(int user_id, String action){
        ActionService.add(new Action(new Date(), user_id, action));
    }

    public void deleteEmployee(int employee_id){
        List<Employee> subEmployees = EmployeeService.getAllSubordinates(employee_id);
        for(Employee employee : subEmployees){
            employee.setManager_id(my_employee_id);
            EmployeeService.update(employee);
        }
        EmployeeService.delete(employee_id);
    }

    public boolean iamEmployee(){
        if(my_employee_id == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean isSubordinate(int employee_id){
        List<Employee> subEmployees = EmployeeService.getAllSubordinates(my_employee_id);
        for(Employee employee : subEmployees){
            if(employee_id == employee.getEmployee_id()){
                return true;
            }
        }
        return false;
    }

    public void deleteAll(){
        UserService.deleteAll();
    }

    public void deleteUser(int user_id){
        try {
            deleteEmployee(EmployeeService.getByUser(user_id).getEmployee_id());
        } catch (Exception e){}
        try {
            Account account = AccountService.getByUser(user_id);
            Openkey openkey = OpenKeyService.getByAccount(account.getAccount_id());
            OpenKeyService.delete(openkey.getOpen_key_id());
            AccountService.delete(account.getAccount_id());
        } catch (Exception e){}

    }


}
