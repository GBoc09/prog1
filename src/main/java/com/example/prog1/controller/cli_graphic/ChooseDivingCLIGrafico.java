package com.example.prog1.controller.cli_graphic;

import com.example.prog1.bean.CominicationBean;
import com.example.prog1.bean.DivingBean;
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


public class ChooseDivingCLIGrafico extends ControllerGraficoManagementCli {
    @Override
    public void start() throws StartException {
        while (true){
            int choice;
            try{
                choice = showMenu();
                switch (choice){
                    case 1: displayDiving();
                        break;
                    case 2: back();
                        break;
                    case 3: logOut();
                        break;
                    default: throw new InvalidFormatException("Invalid choice");
                }
            } catch (IOException e) {
               throw new StartException("IOException");
            } catch (InvalidFormatException e) {
                logger.log(Level.INFO, e.getMessage());
            }
        }
    }
    @Override
    public int showMenu() throws IOException {
        PrinterCli.printMessage("*** SCUBA DASHBOARD *** \n  *** Which diving do you choose? *** \n");
        PrinterCli.printMessage("1) Show diving list\n");
        PrinterCli.printMessage("2) Home\n");
        PrinterCli.printMessage("3) LogOut\n");
        return getMenuChoice(1,3);
    }
    private void displayDiving() throws StartException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
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
            PrinterCli.printMessage("Please write the diving's name: ");
            String name = reader.readLine();
            CominicationBean cominicationBean = new CominicationBean(name);
            InternalControllerGrafico.getInternalControllerInstance().setBeanString(cominicationBean);
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
        }

        new EquipListCLIScubaControllerG().start();
    }

    private void back() throws StartException {
        new HomeScubaCLIGrafico().start();
    }
    private void logOut() throws StartException {new LoginCliControllerGrafico().start();}

}
