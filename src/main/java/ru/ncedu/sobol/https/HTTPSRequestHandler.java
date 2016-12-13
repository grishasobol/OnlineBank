package ru.ncedu.sobol.https;

import ru.ncedu.sobol.parser.RequestParser;

import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.*;

/**
 * Created by Gregory on 04-Dec-16.
 */
public class HTTPSRequestHandler extends Thread {
    private SSLSocket sslSocket = null;

    public HTTPSRequestHandler(SSLSocket sslSocket){
        this.sslSocket = sslSocket;
    }

    @Override
    public void run() {
        sslSocket.setEnabledCipherSuites(sslSocket.getSupportedCipherSuites());

        try{
            // Start handshake
            sslSocket.startHandshake();

            // Get session after the connection is established
            SSLSession sslSession = sslSocket.getSession();

            System.out.println("SSLSession :");
            System.out.println("\tProtocol : "+sslSession.getProtocol());
            System.out.println("\tCipher suite : "+sslSession.getCipherSuite());

            // Start handling application content
            InputStream inputStream = sslSocket.getInputStream();
            OutputStream outputStream = sslSocket.getOutputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream));

            System.out.println("Listen");
            String line;

            RequestParser requestParser = new RequestParser(1);

            boolean lol = true;

            while(lol) {
                line = bufferedReader.readLine();
                System.out.println("Input:  " + line);
                line = requestParser.parse(line);
                System.out.println("Output: " + line);
                printWriter.println(line);
                printWriter.flush();
            }

            sslSocket.close();
            System.out.println("Close");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
