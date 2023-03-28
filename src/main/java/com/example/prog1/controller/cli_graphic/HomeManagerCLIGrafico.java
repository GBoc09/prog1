package com.example.prog1.controller.cli_graphic;

import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.grafico.InternalControllerGrafico;
import com.example.prog1.exception.InvalidFormatException;
import com.example.prog1.exception.StartException;
import com.example.prog1.utilities.PrinterCli;

import java.io.IOException;
import java.util.logging.Level;

public class HomeManagerCLIGrafico extends ControllerGraficoManagementCli{
    @Override
    public void start() throws StartException {
        while (true){
            int choice;
            try {
                choice = showMenu();
                switch (choice){
                    case 1 -> addEquip();
                    case 2 -> addCourse();
                    case 3 -> addDiving();
                    case 4 -> showEquip();
                    case 5 -> showDiving();
                    case 6 -> logOut();
                    case 7 -> System.exit(0);
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
        PrinterCli.printMessage("*** MANGER DASHBOARD *** \n  *** What do "+userBean.getUserEmail()+" want to do? ***  \n");
        PrinterCli.printMessage("1) Add Equipment\n");
        PrinterCli.printMessage("2) Add Course\n");
        PrinterCli.printMessage("3) Add Diving \n");
        PrinterCli.printMessage("4) Show Equipment for a selected diving\n");
        PrinterCli.printMessage("5) Show all of your diving\n");
        PrinterCli.printMessage("6) LogOut\n");
        PrinterCli.printMessage("7) Quit\n");
        return getMenuChoice(1,6);
    }
    private void addEquip(){/** crea controller */}
    private void addDiving(){/** crea controller */}
    private void addCourse() throws InvalidFormatException {
        PrinterCli.printMessage("Not implemented");
        throw new InvalidFormatException("Function not implemented");
    }
    private void showEquip(){/** crea controller */}
    private void showDiving(){/** crea controller */}
    private void logOut() throws StartException {new LoginCliControllerGrafico().start();}
}
