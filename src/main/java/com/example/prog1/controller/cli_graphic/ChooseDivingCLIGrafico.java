package com.example.prog1.controller.cli_graphic;

import com.example.prog1.bean.CominicationBean;
import com.example.prog1.bean.DivingBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.RentalEquipApplicativo;
import com.example.prog1.controller.grafico.InternalControllerGrafico;
import com.example.prog1.exception.InvalidFormatException;
import com.example.prog1.utilities.PrinterCli;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import static java.lang.Thread.sleep;

public class ChooseDivingCLIGrafico extends ControllerGraficoManagementCli {

    @Override
    public void start() {
        while (true){
            int choice;
            try{
                choice = showMenu();
                switch (choice){
                    case 1: displayEquip();
                        break;
                    case 2: back();
                        break;
                    default: throw new InvalidFormatException("Invalid choice");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
           } catch (InvalidFormatException e) {
                logger.log(Level.INFO, e.getMessage());
            }
        }
    }
    @Override
    public int showMenu() throws IOException {
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        PrinterCli.printMessage("*** SCUBA DASHBOARD *** \n  *** Which diving do you choose? *** \n");
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        List<DivingBean> divingBeanList = rentalEquipApplicativo.getDivingList();
        int i = 1;
        for (DivingBean d : divingBeanList) {
            String name = d.getName();
            String loc = d.getLocation();
            String tel = d.getTelephone();
            PrinterCli.printMessage(i+" "+name+" "+loc+" "+tel+"\n");
            i++;
        }
        PrinterCli.printMessage("Please write the name of the diving: ");
        Scanner str = new Scanner(System.in);
        String s = String.valueOf(str);
        CominicationBean cominicationBean = new CominicationBean(s);
        InternalControllerGrafico.getInternalControllerInstance().setBeanString(cominicationBean);
        return getMenuChoice(1,2);
    }
    private void displayEquip(){
        //todo
         }
         private void back(){
        // todo
         }

}
