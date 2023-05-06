package com.example.prog1.controller.cli_graphic;

import com.example.prog1.bean.CominicationBean;
import com.example.prog1.bean.EquipBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.RentalEquipApplicativo;
import com.example.prog1.controller.grafico.InternalControllerGrafico;
import com.example.prog1.exception.InvalidFormatException;
import com.example.prog1.exception.StartException;
import com.example.prog1.utilities.PrinterCli;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

public class EquipListCLIMan extends ControllerGraficoManagementCli{
    @Override
    public void start() throws StartException {
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        while (true){
            int choice;
            try{
                choice = showMenu();
                switch (choice){
                    case 1 -> showEquip(userBean);
                    case 2 -> back();
                    case 3 -> logOut();
                    default -> throw new InvalidFormatException("Invalid choice");
                }

            } catch (IOException e) {
                logger.log(Level.INFO, e.getMessage());
            } catch (InvalidFormatException e) {
                logger.log(Level.INFO, e.getMessage());
            }
        }
    }

    @Override
    public int showMenu() throws IOException {
        PrinterCli.printMessage("*** MANAGER DASHBOARD *** \n  *** Equipment *** \n");
        PrinterCli.printMessage("1) Show equipment list\n");
        PrinterCli.printMessage("2) Home\n");
        PrinterCli.printMessage("3) LogOut\n");
        return getMenuChoice(1,3);
    }
    private void logOut() throws StartException { new LoginCliControllerGrafico().start(); }
    private void back() throws StartException { new HomeManagerCLIGrafico().start(); }
    private void showEquip(UserBean userBean) {
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        CominicationBean cominicationBean = InternalControllerGrafico.getInternalControllerInstance().getBeanString();
        List<EquipBean> equipBeanList = null;
        equipBeanList = rentalEquipApplicativo.getEquips(userBean, cominicationBean);

        PrinterCli.printMessage("--- DIVING'S EQUIP ---");
        for (EquipBean d : equipBeanList) {
            String type = d.getType();
            String size = d.getSize();
            Integer avail = d.getAvail();
            Integer price = d.getPrice();
            PrinterCli.printMessage(type + " " + size + " " + avail + "   " + price + "\n");
        }
    }
}
