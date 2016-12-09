package ru.ncedu.sobol.parser;

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

    public static boolean boolBit(char c){
        boolean result;
        if((c & 1) == 1){
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public Access(int accesses) {
        this.accesses = accesses;
        String binAccesses = Integer.toBinaryString(accesses);
        while (binAccesses.length() < numberOfFlags) {
            binAccesses = "0" + binAccesses;
        }
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
