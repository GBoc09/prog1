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
                    case 6 -> order();
                    case 7 -> logOut();
                    case 8 -> System.exit(0);
                    default -> throw new InvalidFormatException("invalid choice");
                }
                break;
            } catch (InvalidFormatException e){
                logger.log(Level.INFO, e.getMessage());
            } catch (IOException e) {
                logger.log(Level.INFO, e.getMessage());
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
        PrinterCli.printMessage("6) Order section\n");
        PrinterCli.printMessage("7) LogOut\n");
        PrinterCli.printMessage("8) Quit\n");
        return getMenuChoice(1,8);
    }
    private void addEquip() throws StartException{ new AddEquipManCLI().start();}
    private void addDiving() throws StartException{ new AddDivingManCLI().start(); }
    private void addCourse() throws InvalidFormatException {
        PrinterCli.printMessage("Not implemented");
        throw new InvalidFormatException("Function not implemented");
    }
    private void showEquip() throws StartException{ new ShowEquipManCLI().start(); }
    private void showDiving() throws StartException {new DivingListManCLI().start(); }
    private void order() throws StartException { new AcceptRejectOrderManCLI().start(); }
    private void logOut() throws StartException { new LoginCliControllerGrafico().start(); }
}
