package com.example.prog1.controller.cli_graphic;

import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.grafico.InternalControllerGrafico;
import com.example.prog1.exception.InvalidFormatException;
import com.example.prog1.utilities.PrinterCli;

import java.io.IOException;
import java.util.logging.Level;

public class HomeFreeCLIGrafico extends ControllerGraficoManagementCli{
    @Override
    public void start() {
        while (true){
            int choice;
            try {
                choice = showMenu();
                switch (choice){
                    case 1 -> selectCourse();
                    case 2 -> addDive();
                    case 3 -> logOut();
                    case 5 -> System.exit(0);
                    default -> throw new InvalidFormatException("invalid choice");
                }
            } catch (InvalidFormatException e){
                logger.log(Level.INFO, e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

    @Override
    public int showMenu() throws IOException {
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        PrinterCli.printMessage("*** What shoul I do for you "+userBean.getUserEmail());
        PrinterCli.printMessage("1) Select Diving Course\n");
        PrinterCli.printMessage("2) Add diving to your LogBook\n");
        PrinterCli.printMessage("3) LogOut\n");
        PrinterCli.printMessage("4) Quit\n");
        return getMenuChoice(1,4);
    }
    private void selectCourse() throws InvalidFormatException {
        PrinterCli.printMessage("Not implemented\n");
        throw new InvalidFormatException("Function not implemented");
    }
    private void addDive() throws InvalidFormatException {
        PrinterCli.printMessage("Not implemented\n");
        throw new InvalidFormatException("Function not implemented");
    }
    private void logOut(){new LoginCliControllerGrafico().start();}
}
