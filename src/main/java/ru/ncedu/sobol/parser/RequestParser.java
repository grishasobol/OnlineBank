package ru.ncedu.sobol.parser;

import ru.ncedu.sobol.essences.Account;
import ru.ncedu.sobol.essences.User;
import ru.ncedu.sobol.sql.SQLHandler;

import java.util.List;

import java.security.SecureRandom;

/**
 * Created by Gregory on 08-Dec-16.
 */
public class RequestParser {

    private final static String WRONG_REQUEST = "wrongrequest";
    private final static String CONFIRM_ACCESS = "confirmaccess";
    private final static String RESPONSE = "response";
    private final static String SUCCESSFUL_OPERATION = "The operation done successfully";
    private final static String SEPARATOR = ";";
    private final static int USER_ACCESSES = 92;

    int user_id;
    Access accesses = null;
    SQLHandler sqlHandler;
    SecureRandom secureRandom;
    String lastCheckMessage = null;

    enum Operations { BAD_OPERATION, CONFIRMATION, INFO_USER, INFO_MY, INFO_ALL, ADD_EMPLOYEE, ADD_USER, ADD_MONEY,
        DELETE_ALL, DELETE_MY, DELETE_USER}

    Operations lastOperation = null;

    public RequestParser(int user_id) {
        this.user_id = user_id;
        sqlHandler = new SQLHandler(user_id);
        if(sqlHandler.iamEmployee()){
            accesses = new Access(sqlHandler.getEmployee(user_id).getAccesses());
        } else {
            accesses = new Access(USER_ACCESSES);
        }
        secureRandom = new SecureRandom();
    }

    private Operations parseRequestToOperation(String[] tokens){
        try {
            switch (tokens[0]) {
                case "info":
                    switch (tokens[1]){
                        case "user":
                            return Operations.INFO_USER;
                        case "my":
                            return Operations.INFO_MY;
                        case "all":
                            return Operations.INFO_ALL;
                        default:
                            return Operations.BAD_OPERATION;
                    }
                case "add":
                    switch (tokens[1]){
                        case "employee":
                            return Operations.ADD_EMPLOYEE;
                        case "user":
                            return Operations.ADD_USER;
                        case "money":
                            return Operations.ADD_MONEY;
                        default:
                            return Operations.BAD_OPERATION;
                    }
                case "delete":
                    switch (tokens[1]){
                        case "all":
                            return Operations.DELETE_ALL;
                        case "my":
                            return Operations.DELETE_MY;
                        case "user":
                            return Operations.DELETE_USER;
                        default:
                            return Operations.BAD_OPERATION;
                    }
                case "confirm":
                    return Operations.CONFIRMATION;
            }
        } catch (Exception e){
            e.printStackTrace();
            return Operations.BAD_OPERATION;
        }
        return Operations.BAD_OPERATION;
    }

    public String parse(String request){
        try {
            String[] tokens = request.split(" ");
            Operations operation = parseRequestToOperation(tokens);
            switch (operation) {
                case BAD_OPERATION:
                    return WRONG_REQUEST;
                case CONFIRMATION:
                    switch (lastOperation) {
                        case BAD_OPERATION:
                            return WRONG_REQUEST;
                        case CONFIRMATION:
                            return WRONG_REQUEST;
                        case INFO_USER:
                            return getUserInfo(sqlHandler.getUser(Integer.parseInt(tokens[2])));
                        case INFO_MY:
                            return WRONG_REQUEST;
                        case INFO_ALL:
                            List<User> allUsers = sqlHandler.getAllUsers();
                            String response = "";
                            for (User user : allUsers) {
                                response += getUserInfo(user) + "\n";
                            }
                            return response;
                        case ADD_EMPLOYEE:
                            sqlHandler.addEmployee(sqlHandler.getMy_employee_id(), Integer.parseInt(tokens[2]),
                                    Integer.parseInt(tokens[3]), Double.parseDouble(tokens[4]));
                            return SUCCESSFUL_OPERATION;
                        case ADD_USER:
                            sqlHandler.addUser(tokens[2], tokens[3], 2, tokens[4]);
                            return SUCCESSFUL_OPERATION;
                        case ADD_MONEY:
                            return WRONG_REQUEST;
                        case DELETE_ALL:
                            sqlHandler.deleteAll();
                            return SUCCESSFUL_OPERATION;
                        case DELETE_MY:
                            sqlHandler.deleteUser(user_id);
                            return SUCCESSFUL_OPERATION;
                        case DELETE_USER:
                            sqlHandler.deleteUser(Integer.parseInt(tokens[2]));
                            return SUCCESSFUL_OPERATION;
                    }
                    break;
                case INFO_USER:
                    return confirmMessage(request);
                case INFO_MY:
                    return getUserInfo(sqlHandler.getUser(this.user_id));
                case INFO_ALL:
                    return confirmMessage(request);
                case ADD_EMPLOYEE:
                    return confirmMessage(request);
                case ADD_USER:
                    return confirmMessage(request);
                case ADD_MONEY:
                    sqlHandler.addMoney(Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3]));
                case DELETE_ALL:
                    return confirmMessage(request);
                case DELETE_MY:
                    return confirmMessage(request);
                case DELETE_USER:
                    return confirmMessage(request);
            }
        } catch (Exception e){
            e.printStackTrace();
            return WRONG_REQUEST;
        }
        try {
            switch (tokens[0]) {
                case "info":
                    switch (tokens[1]){
                        case "user": {
                            User user = sqlHandler.getUser(Integer.parseInt(tokens[2]));
                            return getUserInfo(user);
                        }
                        case "my":
                            return  getUserInfo(sqlHandler.getUser(this.user_id));
                        case "all":
                            List<User> allUsers = sqlHandler.getAllUsers();
                            String response = "";
                            for(User user : allUsers){
                                response += getUserInfo(user) + "\n";
                            }
                            return response;
                        default:
                            return WRONG_REQUEST;
                    }
                case "add":
                    switch (tokens[1]){
                        case "employee":
                            if(!accesses.isAddEmployee()){
                                return confirmMessage(request);
                            }
                            sqlHandler.addEmployee(sqlHandler.getMy_employee_id(), Integer.parseInt(tokens[2]),
                                    Integer.parseInt(tokens[3]), Double.parseDouble(tokens[4]));
                            return SUCCESSFUL_OPERATION;
                        case "user":
                            if(!accesses.isAddUser()){
                                return confirmMessage(request);
                            }
                            sqlHandler.addUser(tokens[2], tokens[3], 2, tokens[4]);
                            return SUCCESSFUL_OPERATION;
                        case "money":
                            if(!accesses.isAddUser()){
                                return  confirmMessage(request);
                            }
                    }
            }
        } catch (Exception e){
            e.printStackTrace();
            return WRONG_REQUEST;
        }
        return WRONG_REQUEST;
    }

    private String getUserInfo(User user){
        Account account = sqlHandler.getAccount(user.getUser_id());
        return "Accepted;" + "user_id " + user.getUser_id() + ";login "
                + user.getLogin() + ";type " + user.getUser_type() + ";"
                + "name " + account.getName() + ";money " + account.getMoney() + ";";
    }

    private String checkMessage(){
        return "" + secureRandom.nextLong() + System.currentTimeMillis();
    }

    private String confirmMessage(String request){
        lastCheckMessage = request + SEPARATOR + checkMessage();
        return CONFIRM_ACCESS + SEPARATOR + lastCheckMessage;
    }
}
