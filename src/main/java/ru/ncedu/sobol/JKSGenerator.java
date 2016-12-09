package ru.ncedu.sobol;

import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.X509Certificate;

/**
 * Created by Gregory on 04-Dec-16.
 */
public class JKSGenerator {
    public static void main(String[] argv){
        try{
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(null,null);

            keyStore.store(new FileOutputStream("mytestkey.jks"), "password".toCharArray());
        }catch(Exception ex){
            ex.printStackTrace();
        }
        try{
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream("mytestkey.jks"),"password".toCharArray());

            CertAndKeyGen gen = new CertAndKeyGen("RSA","SHA1WithRSA");
            gen.generate(1024);

            X509Certificate cert=gen.getSelfCertificate(new X500Name("CN=SINGLE_CERTIFICATE"), (long)365*24*3600);

            keyStore.setCertificateEntry("single_cert", cert);

            keyStore.store(new FileOutputStream("mytestkey.jks"), "password".toCharArray());
        }catch(Exception ex){
            ex.printStackTrace();
        }
        try{
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream("mytestkey.jks"),"password".toCharArray());

            CertAndKeyGen gen = new CertAndKeyGen("RSA","SHA1WithRSA");
            gen.generate(1024);

            X509Certificate cert=gen.getSelfCertificate(new X500Name("CN=SINGLE_CERTIFICATE"), (long)365*24*3600);

            keyStore.setCertificateEntry("single_cert", cert);

            keyStore.store(new FileOutputStream("mytestkey.jks"), "password".toCharArray());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
