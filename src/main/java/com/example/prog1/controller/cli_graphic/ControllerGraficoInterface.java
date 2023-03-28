package com.example.prog1.controller.cli_graphic;

import com.example.prog1.exception.StartException;

import java.io.IOException;

public interface ControllerGraficoInterface {
    void start() throws StartException;
    int showMenu() throws IOException;
}
