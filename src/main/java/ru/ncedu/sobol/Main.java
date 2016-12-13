package ru.ncedu.sobol;

import ru.ncedu.sobol.sign.SignChecker;
import ru.ncedu.sobol.sign.SignMaker;

import java.nio.charset.StandardCharsets;
import java.security.PublicKey;

public class Main {

    public static void main(String[] args) {
		SignMaker s = new SignMaker();
		byte[] str = "abcdef".getBytes(StandardCharsets.UTF_8);
		PublicKey pubKey = s.getPublicKey();
	
		byte[] sign = s.signData(str);
		System.out.println(sign);
		System.out.println(pubKey.toString());
	
		SignChecker ch = new SignChecker(pubKey);
	
		System.out.println(ch.verifyData(str, sign));
    }

}
