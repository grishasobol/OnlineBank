package ru.ncedu.sobol.https;

/**
 * Created by Gregory on 04-Dec-16.
 */

import java.io.*;
import java.security.KeyStore;
import javax.net.ssl.*;

public class HTTPSServer implements Runnable{

    private int port = 8000;
    private boolean isServerDone = false;

    public HTTPSServer(){
    }

    public HTTPSServer(int port){
        this.port = port;
    }


    private SSLContext createSSLContext() {
        try{
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream("testkey.jks"),"password".toCharArray());
            // Create key manager
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, "password".toCharArray());
            KeyManager[] km = keyManagerFactory.getKeyManagers();

            // Create trust manager
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
            trustManagerFactory.init(keyStore);
            TrustManager[] tm = trustManagerFactory.getTrustManagers();

            // Initialize SSLContext
            SSLContext sslContext = SSLContext.getInstance("TLSv1");
            sslContext.init(km,  tm, null);

            return sslContext;
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    public void run() {
        SSLContext sslContext = this.createSSLContext();

        try{
            // Create server socket factory
            SSLServerSocketFactory sslServerSocketFactory = sslContext.getServerSocketFactory();

            // Create server socket
            SSLServerSocket sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(this.port);

            System.out.println("SSL server started");
            while(!isServerDone){
                SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();

                // Start the server thread
                new HTTPSRequestHandler(sslSocket).start();
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
