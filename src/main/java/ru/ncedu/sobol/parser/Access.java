package ru.ncedu.sobol.parser;

import java.util.HashMap;

/**
 * Created by Gregory on 09-Dec-16.
 */
public class Access {

    HashMap<Operations, Boolean> accessMap = new HashMap<>();

    public Access(String binAccesses){
        int i = 0;
        for(Operations op : Operations.values()){
            accessMap.put(op, boolBit(binAccesses.charAt(i)));
            i++;
        }
    }

    public Access(int accesses) {
        this(binarization(accesses));
    }

    public boolean isPermitted(Operations operation){
        return accessMap.get(operation);
    }

    static String binarization(int accesses){
        String binAccesses = Integer.toBinaryString(accesses);
        while (binAccesses.length() < Operations.values().length) {
            binAccesses = binAccesses + "0";
        }
        return binAccesses;
    }

    static boolean boolBit(char c){
        boolean result;
        if(c == '1'){
            result = false;
        } else {
            result = true;
        }
        return result;
    }
}
