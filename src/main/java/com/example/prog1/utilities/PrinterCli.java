package com.example.prog1.utilities;

public class PrinterCli {
    private PrinterCli() {
        throw new IllegalStateException("Utility class");
    }
    public static void printMessage(String s){
        System.out.println(s);
    }
}
