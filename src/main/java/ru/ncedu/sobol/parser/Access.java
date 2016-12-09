package ru.ncedu.sobol.parser;

import java.util.HashMap;

/**
 * Created by Gregory on 09-Dec-16.
 */
public class Access {

    boolean infoUser;
    boolean infoMy;
    boolean infoAll;
    boolean deleteAll;
    boolean deleteMy;
    boolean deleteUser;
    boolean addUser;
    boolean addMoney;
    boolean addEmployee;
    int numberOfFlags = 9;
    int accesses;

    HashMap<Operations, Boolean> accessMap = new HashMap<>();

    public static boolean boolBit(char c){
        boolean result;
        if(c == '1'){
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public Access(String binAccesses){

    }

    public Access(int accesses) {
        this.accesses = accesses;
        String binAccesses = Integer.toBinaryString(accesses);
        while (binAccesses.length() < Operations.values().length) {
            binAccesses = binAccesses + "0";
        }
        int i = 0;
        for(Operations op : Operations.values()){
            accessMap.put(op, boolBit(binAccesses.charAt(i)));
            i++;
        }
//        accessMap.put(Operations.INFO_USER, boolBit(binAccesses.charAt(0)));
//        accessMap.put(Operations.INFO_MY, boolBit(binAccesses.charAt(1)));
//        accessMap.put(Operations.INFO_ALL, boolBit(binAccesses.charAt(2)));
//        accessMap.put(Operations.DELETE_ALL, boolBit(binAccesses.charAt(3)));
//        accessMap.put(Operations.DELETE_MY, boolBit(binAccesses.charAt(4)));
//        accessMap.put(Operations.DELETE_USER, boolBit(binAccesses.charAt(5)));
//        accessMap.put(Operations.ADD_USER, boolBit(binAccesses.charAt(6)));
//        accessMap.put(Operations.ADD_MONEY, boolBit(binAccesses.charAt(7)));
//        accessMap.put(Operations.ADD_EMPLOYEE, boolBit(binAccesses.charAt(8)));

        infoUser = boolBit(binAccesses.charAt(0));
        infoMy = boolBit(binAccesses.charAt(1));
        infoAll = boolBit(binAccesses.charAt(2));
        deleteAll = boolBit(binAccesses.charAt(3));
        deleteMy = boolBit(binAccesses.charAt(4));
        deleteUser = boolBit(binAccesses.charAt(5));
        addUser = boolBit(binAccesses.charAt(6));
        addMoney = boolBit(binAccesses.charAt(7));
        addEmployee = boolBit(binAccesses.charAt(8));
    }

    public int getAccesses() {
        return accesses;
    }

    public boolean isInfoUser() {
        return infoUser;
    }

    public boolean isInfoMy() {
        return infoMy;
    }

    public boolean isInfoAll() {
        return infoAll;
    }

    public boolean isDeleteAll() {
        return deleteAll;
    }

    public boolean isDeleteMy() {
        return deleteMy;
    }

    public boolean isDeleteUser() {
        return deleteUser;
    }

    public boolean isAddUser() {
        return addUser;
    }

    public boolean isAddMoney() {
        return addMoney;
    }

    public boolean isAddEmployee() {
        return addEmployee;
    }

    public int getNumberOfFlags() {
        return numberOfFlags;
    }
}
