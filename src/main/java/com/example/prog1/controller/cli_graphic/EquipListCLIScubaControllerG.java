package com.example.prog1.controller.cli_graphic;

import com.example.prog1.bean.*;
import com.example.prog1.controller.applicativo.RentalEquipApplicativo;
import com.example.prog1.controller.grafico.InternalControllerGrafico;
import com.example.prog1.exception.InvalidFormatException;
import com.example.prog1.exception.StartException;
import com.example.prog1.utilities.PrinterCli;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;

public class EquipListCLIScubaControllerG extends ControllerGraficoManagementCli{
    @Override
    public void start() throws StartException {
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        while (true){
            int choice;
            try{
                choice = showMenu();
                switch (choice){
                    case 1 -> showEquip();
                    case 2 -> addQuantity();
                    case 3 -> showCart(userBean);
                    case 4 -> completeOrder();
                    case 5 -> deleteCart(userBean);
                    case 6 -> back();
                    case 7 -> {
                        logOut();
                        if(choice > 7){
                            break;
                        }
                    }
                    default -> throw new InvalidFormatException("Invalid choice");
                }
            } catch (IOException e) {
                logger.log(Level.INFO, e.getMessage());
            } catch (InvalidFormatException e) {
               logger.log(Level.INFO, e.getMessage());
            }
        }
    }

    private void logOut() throws StartException {
        new LoginCliControllerGrafico().start();
    }

    private void back() throws StartException {
        new HomeScubaCLIGrafico().start();
    }
    private void deleteCart(UserBean userBean){
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        rentalEquipApplicativo.deleteItem(userBean);
    }

    @Override
    public int showMenu() throws IOException {
        PrinterCli.printMessage("*** SCUBA DASHBOARD ***\n *** Rent Equipment Module ***\n");
        PrinterCli.printMessage("1) Show Equip\n");
        PrinterCli.printMessage("2) Add Quantity\n");
        PrinterCli.printMessage("3) Show Cart\n");
        PrinterCli.printMessage("4) Complete Order\n");
        PrinterCli.printMessage("5) Delete Cart\n");
        PrinterCli.printMessage("6) Home\n");
        PrinterCli.printMessage("7) LogOut\n");
        return getMenuChoice(1,7);
    }
    private void showEquip() {
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        CominicationBean cominicationBean = InternalControllerGrafico.getInternalControllerInstance().getBeanString();
        String str = cominicationBean.getStr();
        List<EquipBean> equipBeanList = null;
        if (str != null) {
            equipBeanList = rentalEquipApplicativo.getEquipsName(str);
        } else {
            equipBeanList = rentalEquipApplicativo.getEquips();
        }
        PrinterCli.printMessage("--- DIVING'S EQUIP ---");
        for (EquipBean d : equipBeanList) {
            String type = d.getType();
            String size = d.getSize();
            Integer avail = d.getAvail();
            Integer price = d.getPrice();
            PrinterCli.printMessage(type + " " + size + " " + avail + "   " + price + "\n");
        }
    }
    private void addQuantity() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        PassStringsBean passStringsBean;
        PrinterCli.printMessage("Please write the item you are interested in: ");
        String item = reader.readLine();
        PrinterCli.printMessage("Please write the size of the equip:  ");
        String size = reader.readLine();
        PrinterCli.printMessage("Please write the needed quantities: ");
        Integer quant = Integer.valueOf(reader.readLine());
        passStringsBean = new PassStringsBean(item, size, quant);
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        rentalEquipApplicativo.infoEquipCartCLI(passStringsBean, userBean);
    }
    private void showCart(UserBean userBean)  {
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        List<CartBean> cartBeanList = rentalEquipApplicativo.showCart(userBean.getUserEmail());
        PrinterCli.printMessage("--- YOUR CART ---");
        for (CartBean c : cartBeanList) {
            String type = c.getType();
            String size = c.getSize();
            Integer quant = c.getQuant();
            Integer price = c.getPrice();
            PrinterCli.printMessage(type + " " + size + " " + quant + "   " + price + "\n");
        }
    }
    private void completeOrder() throws StartException {
        CominicationBean cominicationBean = InternalControllerGrafico.getInternalControllerInstance().getBeanString();
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        List<CartBean> cartBeanList = rentalEquipApplicativo.showCart(userBean.getUserEmail());
        rentalEquipApplicativo.saveItem(cartBeanList, userBean, cominicationBean);
        new RentSummaryScubaCLI().start();
    }
}
