package com.example.prog1.controller.cli_graphic;

import com.example.prog1.bean.DivingBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.RentalEquipApplicativo;
import com.example.prog1.controller.grafico.InternalControllerGrafico;
import com.example.prog1.exception.StartException;
import com.example.prog1.utilities.PrinterCli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class AddDivingManCLI extends ControllerGraficoManagementCli{
    @Override
    public void start() throws StartException {
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        try {
            insertNewDiving(userBean);
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
        }
    }

    @Override
    public int showMenu() throws IOException {
        return 0;
    }
    public void insertNewDiving(UserBean userBean) throws IOException, StartException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrinterCli.printMessage("Please write the diving's name: ");
        String name = reader.readLine();
        PrinterCli.printMessage("Please write the diving's location: ");
        String location = reader.readLine();
        PrinterCli.printMessage("Please write the diving's telephone number: ");
        String tel = reader.readLine();
        DivingBean divingBean = new DivingBean(name, location,tel);
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        rentalEquipApplicativo.addDiving(userBean, divingBean);
        PrinterCli.printMessage("*** INSERTION SUCCESSFUL *** \n");
        new DivingListManCLI().start();
    }
}
