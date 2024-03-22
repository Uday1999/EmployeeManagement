package org.example;

import java.util.Scanner;

public class Utility {

    public static Scanner sc = new Scanner(System.in);

    public static String getWord() {
        return sc.next();
    }

    public static String getLine() {
        return sc.nextLine();
    }

    public static int getInt() {
        return sc.nextInt();
    }

    public static double getDecimal() {
        return sc.nextDouble();
    }
}
