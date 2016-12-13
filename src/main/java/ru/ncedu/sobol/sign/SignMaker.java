package ru.ncedu.sobol.sign;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;

public class SignMaker {
    private PrivateKey privKey;
    private PublicKey pubKey;
    
    public SignMaker() {
	KeyPairGenerator keyGen = null;
	SecureRandom random = null;
	try {
	    keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
	    random = SecureRandom.getInstance("SHA1PRNG", "SUN");
	} catch (NoSuchAlgorithmException e) {
	    System.out.println(e.getMessage());
	} catch (NoSuchProviderException e) {
	    System.out.println(e.getMessage());
	}
	
	keyGen.initialize(1024, random);
	    
	KeyPair pair = keyGen.generateKeyPair();
	privKey = pair.getPrivate();
	pubKey = pair.getPublic();
    }
    
    public PublicKey getPublicKey() {
	return pubKey;
    }
    
    public byte[] signData(byte[] data) {
	byte[] realSig = null;

	try {
	    Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
	    dsa.initSign(privKey);
	    dsa.update(data);
	    realSig = dsa.sign();
	} catch (NoSuchAlgorithmException e) {
	    System.out.println(e.getMessage());
	} catch (NoSuchProviderException e) {
	    System.out.println(e.getMessage());
	} catch (InvalidKeyException e) {
	    System.out.println(e.getMessage());
	} catch (SignatureException e) {
	    System.out.println(e.getMessage());
	}
	    	
	return realSig;
    }
}
