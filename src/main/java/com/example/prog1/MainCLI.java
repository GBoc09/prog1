package com.example.prog1;

import com.example.prog1.controller.cli_graphic.LoginCliControllerGrafico;
import com.example.prog1.exception.StartException;

public class MainCLI {
    public static void main (String[] args){
        LoginCliControllerGrafico loginCliControllerGrafico = new LoginCliControllerGrafico();
        try {
            loginCliControllerGrafico.start();
        } catch (StartException e) {
            throw new RuntimeException(e);
        }
    }
}
