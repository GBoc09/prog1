package com.example.prog1.controller.cli_graphic;

import com.example.prog1.bean.CartBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.RentalEquipApplicativo;
import com.example.prog1.controller.grafico.InternalControllerGrafico;
import com.example.prog1.exception.InvalidFormatException;
import com.example.prog1.exception.StartException;
import com.example.prog1.utilities.PrinterCli;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

public class AcceptRejectOrderManCLI extends ControllerGraficoManagementCli{
    private String decisione;
    private static final String ACCEPT = "accept";
    private static final String REJECT = "reject";
    @Override
    public void start() throws StartException {
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        while (true){
            int choice;
            try{
                choice = showMenu();
                switch (choice){
                    case 1 -> acceptOrder(userBean);
                    case 2 -> rejectOrder(userBean);
                    case 3 -> back();
                    case 4 -> logOut();
                    default -> throw new InvalidFormatException("Invalid choice");
                }
                break;

            } catch (IOException e) {
                logger.log(Level.INFO, e.getMessage());
            } catch (InvalidFormatException e) {
                logger.log(Level.INFO, e.getMessage());
            }
        }
    }
    private void logOut() throws StartException { new LoginCliControllerGrafico().start(); }
    private void back() throws StartException { new HomeManagerCLIGrafico().start(); }
    @Override
    public int showMenu() throws IOException {
        PrinterCli.printMessage("*** MANAGER DASHBOARD ***\n *** Order ***\n");
        PrinterCli.printMessage("1) Accept Order\n");
        PrinterCli.printMessage("2) Reject Order\n");
        PrinterCli.printMessage("3) Home\n");
        PrinterCli.printMessage("4) LogOut\n");
        return getMenuChoice(1,4);
    }
    private void acceptOrder(UserBean userBean){
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        List<CartBean> cartBeanList = null;
        cartBeanList = rentalEquipApplicativo.showCartMan();
        for (CartBean c : cartBeanList){
            String type = c.getType();
            String size = c.getSize();
            Integer quant = c.getQuant();
            Integer price = c.getPrice()*quant;
            PrinterCli.printMessage("Rental:\n"+"Equip: "+type+"Size: "+size+"Quantity: "+quant+"Price: "+price);
        }
        decisione = ACCEPT;
        rentalEquipApplicativo.sendConfirmation(userBean.getUserEmail(),decisione);
        PrinterCli.printMessage("--- THE CONFIRMATION EMAIL HAS BEEN SENT --- \n");
        deleteCart();
    }
    private void deleteCart() {
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        rentalEquipApplicativo.deleteItem();
    }
    private void rejectOrder(UserBean userBean){
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        List<CartBean> cartBeanList = null;
        cartBeanList = rentalEquipApplicativo.showCartMan();
        for (CartBean c : cartBeanList){
            String type = c.getType();
            String size = c.getSize();
            Integer quant = c.getQuant();
            Integer price = c.getPrice()*quant;
            PrinterCli.printMessage("Rental:\n"+"Equip: "+type+"Size: "+size+"Quantity: "+quant+"Price: "+price);
        }
        decisione = REJECT;
        rentalEquipApplicativo.sendConfirmation(userBean.getUserEmail(),decisione);
        PrinterCli.printMessage("--- THE EMAIL HAS BEEN SENT --- \n");
    }
}
