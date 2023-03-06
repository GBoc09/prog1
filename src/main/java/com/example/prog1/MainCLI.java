package com.example.prog1;

import com.example.prog1.controller.cli_graphic.LoginCliControllerGrafico;

public class MainCLI {
    public static void main (String[] args){
        LoginCliControllerGrafico loginCliControllerGrafico = new LoginCliControllerGrafico();
        loginCliControllerGrafico.start();
    }
}
