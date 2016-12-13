package ru.ncedu.sobol.sign;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class SignChecker {
    private PublicKey pubKey;
    
    public SignChecker(PublicKey publ) {
	pubKey = publ;
    }
    
    public boolean verifyData(byte[] data, byte[] sign) {
	boolean verify = false;
	try {
	    Signature signature = Signature.getInstance("SHA1withDSA", "SUN");
	    signature.initVerify(pubKey);
	    signature.update(data);
	    
	    verify = signature.verify(sign);
	} catch (NoSuchAlgorithmException e) {
	    System.out.println(e.getMessage());
	} catch (NoSuchProviderException e) {
	    System.out.println(e.getMessage());
	} catch (InvalidKeyException e) {
	    System.out.println(e.getMessage());
	} catch (SignatureException e) {
	    System.out.println(e.getMessage());
	}
	
	return verify;
    }
}
