package com.example.prog1.controller.cli_graphic;

import com.example.prog1.bean.CominicationBean;
import com.example.prog1.bean.DivingBean;
import com.example.prog1.bean.UserBean;
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

public class ShowEquipManCLI extends ControllerGraficoManagementCli {
    @Override
    public void start() throws StartException {
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        while (true){
            int choice;
            try{
                choice = showMenu();
                switch (choice){
                    case 1 -> selectDiving(userBean);
                    case 2 -> back();
                    case 3 -> logOut();
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

    @Override
    public int showMenu() throws IOException {
        PrinterCli.printMessage("*** MANAGER DASHBOARD *** \n  *** Which diving do you choose? *** \n");
        PrinterCli.printMessage("1) Show diving list\n");
        PrinterCli.printMessage("2) Home\n");
        PrinterCli.printMessage("3) LogOut\n");
        return getMenuChoice(1,3);
    }
    private void logOut() throws StartException { new LoginCliControllerGrafico().start(); }
    private void back() throws StartException { new HomeManagerCLIGrafico().start(); }
    private void selectDiving(UserBean userBean) throws StartException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            List<DivingBean> divingBeanList = RentalEquipApplicativo.getDivingListMan(userBean);
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
        new EquipListCLIMan().start();
    }

}
