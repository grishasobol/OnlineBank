package ru.ncedu.sobol;

import ru.ncedu.sobol.essences.User;
import ru.ncedu.sobol.https.HTTPSServer;
import ru.ncedu.sobol.https.HTTPSClient;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.SQLException;

import ru.ncedu.sobol.parser.Access;
import ru.ncedu.sobol.sql.SQLHandler;

import ru.ncedu.sobol.sql.*;

/**
 * Created by Gregory on 19-Nov-16.
 */
public class Checker{
    public static void main(String[] args) throws InterruptedException {
//        HTTPSServer httpsServer = new HTTPSServer(8000);
//        Thread myThready = new Thread(httpsServer);
//        myThready.start();
//                myThready.join();

        Access a = new Access(10);

//        SQLHandler sqlHandler = new SQLHandler();
//        try {
//            for(User user : sqlHandler.getAllUsers()){
//                System.out.println(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(sqlHandler.deleteUser(1));
//
//        SQLHandler sqlHandler = new SQLHandler(1);
//        System.out.println(sqlHandler.getUser(1));
//        sqlHandler.addUser("Miha", "lol", 10, "1111111111111111111111111111111111111");
////


    }
}

