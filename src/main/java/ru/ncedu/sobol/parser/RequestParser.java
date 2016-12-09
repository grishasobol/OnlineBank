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
    private final static String NOT_CONFIRMED = "notconfirmed";
    private final static String SEPARATOR = ";";
    private final static int DEFAULT_USER_ACCESSES = 92;

    int my_user_id;
    Access accesses = null;
    SQLHandler sqlHandler;
    SecureRandom secureRandom;


    Operations lastOperation = null;
    String lastCheckMessage = null;
    String lastRequest = null;


    public RequestParser(int my_user_id) {
        this.my_user_id = my_user_id;
        sqlHandler = new SQLHandler(my_user_id);
        if(sqlHandler.iamEmployee()){
            accesses = new Access(sqlHandler.getEmployee(my_user_id).getAccesses());
        } else {
            accesses = new Access(DEFAULT_USER_ACCESSES);
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
        String result = null;
        String[] tokens = request.split(" ");
        Operations operation = parseRequestToOperation(tokens);
        try {
            switch (operation) {
                case BAD_OPERATION:
                    result = WRONG_REQUEST;
                    break;
                case CONFIRMATION:
                    if(lastOperation == null || lastRequest == null || lastCheckMessage == null){
                        result = WRONG_REQUEST;
                        break;
                    }
                    String signature = tokens[1];
                    int user_id = Integer.parseInt(tokens[2]);
                    if(!checkSignature(signature, user_id)) {
                        result = NOT_CONFIRMED;
                        break;
                    }
                    Access access = new Access(sqlHandler.getEmployee(user_id).getAccesses());
                    if(){

                    }

                    tokens = lastRequest.split(" ");
                    switch (lastOperation) {
                        case BAD_OPERATION:
                            result = WRONG_REQUEST;
                            break;
                        case CONFIRMATION:
                            result = WRONG_REQUEST;
                            break;
                        case INFO_USER:
                            result = getUserInfo(sqlHandler.getUser(Integer.parseInt(tokens[2])));
                            break;
                        case INFO_MY:
                            result = WRONG_REQUEST;
                            break;
                        case INFO_ALL:
                            List<User> allUsers = sqlHandler.getAllUsers();
                            String response = "";
                            for (User user : allUsers) {
                                response += getUserInfo(user) + "\n";
                            }
                            result = response;
                            break;
                        case ADD_EMPLOYEE:
                            sqlHandler.addEmployee(sqlHandler.getMy_employee_id(), Integer.parseInt(tokens[2]),
                                    Integer.parseInt(tokens[3]), Double.parseDouble(tokens[4]));
                            result = SUCCESSFUL_OPERATION;
                            break;
                        case ADD_USER:
                            sqlHandler.addUser(tokens[2], tokens[3], 2, tokens[4]);
                            result = SUCCESSFUL_OPERATION;
                            break;
                        case ADD_MONEY:
                            result = WRONG_REQUEST;
                            break;
                        case DELETE_ALL:
                            sqlHandler.deleteAll();
                            result = SUCCESSFUL_OPERATION;
                            break;
                        case DELETE_MY:
                            sqlHandler.deleteUser(my_user_id);
                            result = SUCCESSFUL_OPERATION;
                            break;
                        case DELETE_USER:
                            sqlHandler.deleteUser(Integer.parseInt(tokens[2]));
                            result = SUCCESSFUL_OPERATION;
                            break;
                        default:
                            result = WRONG_REQUEST;
                            break;
                    }
                    break;
                case INFO_USER:
                    result = confirmMessage(request);
                    break;
                case INFO_MY:
                    result = getUserInfo(sqlHandler.getUser(this.my_user_id));
                    break;
                case INFO_ALL:
                    result = confirmMessage(request);
                    break;
                case ADD_EMPLOYEE:
                    result = confirmMessage(request);
                    break;
                case ADD_USER:
                    result = confirmMessage(request);
                    break;
                case ADD_MONEY:
                    sqlHandler.addMoney(Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3]));
                    break;
                case DELETE_ALL:
                    result = confirmMessage(request);
                    break;
                case DELETE_MY:
                    result = confirmMessage(request);
                    break;
                case DELETE_USER:
                    result = confirmMessage(request);
                    break;
                default:
                    result = WRONG_REQUEST;
                    break;
            }
        } catch (Exception e){
            e.printStackTrace();
            result = WRONG_REQUEST;
        }

        lastOperation = operation;
        lastRequest = request;
        return result;
    }

    private String getUserInfo(User user){
        Account account = sqlHandler.getAccount(user.getUser_id());
        return "Accepted;" + "my_user_id " + user.getUser_id() + ";login "
                + user.getLogin() + ";type " + user.getUser_type() + ";"
                + "name " + account.getName() + ";money " + account.getMoney() + ";";
    }

    private String checkMessage(){
        return "" + secureRandom.nextLong() + System.currentTimeMillis();
    }

    private boolean checkSignature(String signature, int user_id){
        return true;
    }

    private String confirmMessage(String request){
        lastCheckMessage = checkMessage();
        return CONFIRM_ACCESS + SEPARATOR + request + SEPARATOR + lastCheckMessage;
    }
}
