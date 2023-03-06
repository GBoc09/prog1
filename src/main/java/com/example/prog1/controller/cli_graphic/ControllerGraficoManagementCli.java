package com.example.prog1.controller.cli_graphic;

import com.example.prog1.utilities.PrinterCli;

import java.util.Scanner;
import java.util.logging.Logger;

public abstract class ControllerGraficoManagementCli implements ControllerGraficoInterface{
    Logger logger = Logger.getAnonymousLogger();
    protected int getMenuChoice(int a, int b){
        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (true){
            PrinterCli.printMessage("Please enter a chioce: ");
            choice = input.nextInt();
            if (choice >= a && choice <= b){
                break;
            }
            PrinterCli.printMessage("Invalid input\n");
        }
        return choice;
    }
}
