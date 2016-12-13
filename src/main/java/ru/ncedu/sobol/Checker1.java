package ru.ncedu.sobol;

import ru.ncedu.sobol.parser.RequestParser;

/**
 * Created by Gregory on 10-Dec-16.
 */
public class Checker1 {
    public static void main(String[] args) throws InterruptedException {
        RequestParser requestParser = new RequestParser(1);
        requestParser.parse("");
    }
}
