package com.example.prog1.controller.cli_graphic;

import com.example.prog1.bean.EquipBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.RentalEquipApplicativo;
import com.example.prog1.controller.grafico.InternalControllerGrafico;
import com.example.prog1.exception.InvalidFormatException;
import com.example.prog1.exception.StartException;
import com.example.prog1.utilities.PrinterCli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class AddEquipManCLI extends ControllerGraficoManagementCli{
    @Override
    public void start() throws StartException {
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        try {
            insertNewEquip(userBean);
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
        }
    }

    @Override
    public int showMenu() throws IOException {
       return 0;
    }
    private void insertNewEquip(UserBean userBean) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrinterCli.printMessage("Please write the equipment's type: ");
        String type = reader.readLine();
        PrinterCli.printMessage("Please write the equipment's size: ");
        String size = reader.readLine();
        PrinterCli.printMessage("Please write the equipment's availability: ");
        Integer avail = Integer.valueOf(reader.readLine());
        PrinterCli.printMessage("Please write the equipment's price: ");
        Integer price = Integer.valueOf(reader.readLine());
        EquipBean equipBean = new EquipBean(type,size,avail,price);
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        rentalEquipApplicativo.addProduct(equipBean,userBean);
        PrinterCli.printMessage("*** INSERTION SUCCESSFUL *** \n");
    }
}
