package com.example.prog1.controller.cli_graphic;

import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.grafico.InternalControllerGrafico;
import com.example.prog1.exception.InvalidFormatException;
import com.example.prog1.utilities.PrinterCli;

import java.io.IOException;

public class HomeScubaCLIGrafico extends ControllerGraficoManagementCli{
    @Override
    public void start() {
        while (true){
            int choice;
            try {
                choice = showMenu();
                switch (choice){
                    case 1 -> rentEquip();
                    case 2 -> selectCourse();
                    case 3 -> addDive();
                    case 4 -> goToCart();
                    case 5 -> logOut();
                    case 6 -> System.exit(0);
                    default -> throw new InvalidFormatException("invalid choice");
                }
            } catch (InvalidFormatException e){

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public int showMenu() throws IOException {
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        PrinterCli.printMessage("*** SCUBA DASHBOARD *** \n  *** What do "+userBean.getUserEmail()+" want to do? ***  \n");
        PrinterCli.printMessage("1) Rent Equipment\n");
        PrinterCli.printMessage("2) Select Diving Course\n");
        PrinterCli.printMessage("3) Add dive to your LogBook\n");
        PrinterCli.printMessage("4) Cart\n");
        PrinterCli.printMessage("5) LogOut\n");
        PrinterCli.printMessage("6) Quit\n");
        return getMenuChoice(1,6);
    }
    private void rentEquip(){/** creare il controller grafico per questo */
        new ChooseDivingCLIGrafico().start();
    }
    private void selectCourse() throws InvalidFormatException {
        PrinterCli.printMessage("Not implemented\n");
        throw new InvalidFormatException("Function not implemented");
    }
    private void addDive() throws InvalidFormatException {
        PrinterCli.printMessage("Not implemented\n");
        throw new InvalidFormatException("Function not implemented");
    }
    private void goToCart(){/** creare il controller grafico per questo */}
    private void logOut(){new LoginCliControllerGrafico().start();}
}
