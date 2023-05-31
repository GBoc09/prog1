package com.example.prog1.controller.cli_graphic;

import com.example.prog1.bean.IntegerComunicationBean;
import com.example.prog1.bean.RentalBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.RentalEquipApplicativo;
import com.example.prog1.controller.grafico.InternalControllerGrafico;
import com.example.prog1.exception.InvalidFormatException;
import com.example.prog1.exception.StartException;
import com.example.prog1.utilities.PrinterCli;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

public class RentSummaryScubaCLI extends ControllerGraficoManagementCli {
    @Override
    public void start() throws StartException {
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        while (true){
            int choice;
            try{
                choice = showMenu();
                switch (choice){
                    case 1 -> showSummary(userBean);
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
        PrinterCli.printMessage("*** SCUBA DASHBOARD ***\n *** Complete Order Module ***\n");
        PrinterCli.printMessage("1) Show Summary\n");
        PrinterCli.printMessage("2) Home\n");
        PrinterCli.printMessage("3) LogOut\n");
        return getMenuChoice(1,3);
    }
    private void showSummary(UserBean userBean) {
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        List<RentalBean> rentalBeans = null;
        IntegerComunicationBean comunicationBean = InternalControllerGrafico.getInternalControllerInstance().getIntBean();
        rentalBeans = rentalEquipApplicativo.summaryRental(userBean.getUserEmail(), comunicationBean.getIndex());
        PrinterCli.printMessage("--- RENTAL SUMMARY ---");
        for (RentalBean d : rentalBeans){
            Integer id = d.getIdRental();
            String type = d.getEquipType();
            Integer total = d.getTotal();
            PrinterCli.printMessage("IdRental: "+id + " " +"Equipment: " + type + " " + "Total: €"+total + "\n");
        }
    }
    private void logOut() throws StartException {
        new LoginCliControllerGrafico().start();
    }

    private void back() throws StartException {
        new HomeScubaCLIGrafico().start();
    }
}
