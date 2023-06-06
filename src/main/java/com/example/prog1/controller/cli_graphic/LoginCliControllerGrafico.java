package com.example.prog1.controller.cli_graphic;

import com.example.prog1.bean.AccessInfoBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.LoginApplicativo;
import com.example.prog1.controller.grafico.InternalControllerGrafico;
import com.example.prog1.exception.*;
import com.example.prog1.utilities.PrinterCli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginCliControllerGrafico extends ControllerGraficoManagementCli{
    Logger logger1 = Logger.getLogger(LoginCliControllerGrafico.class.getName());
    public static final int NOT_LOG = -1;
    @Override
    public void start() throws StartException {
        while (true){
            int choice;
            try{
                choice = showMenu();
                switch (choice){
                    case 1:
                        login();
                        break;
                    case 2:
                        new SignUpCliControllerG().start();
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        throw new InvalidFormatException("Invalid choice");
                }
                break;
            } catch (InvalidFormatException e){
                logger1.log(Level.INFO, e.getMessage());
            }
        }
    }
    @Override
    public int showMenu() {
        PrinterCli.printMessage("DiversWorld *** DASHBOARD ***");
        PrinterCli.printMessage("1) Login\n");
        PrinterCli.printMessage("2) Sign Up\n");
        PrinterCli.printMessage("3) LogOut\n");

        return getMenuChoice(1,3);
    }
    private void login() throws StartException {
        LoginApplicativo loginApplicativo = new LoginApplicativo();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            PrinterCli.printMessage("Username: ");
            String username = reader.readLine();
            PrinterCli.printMessage("Password: ");
            String password = reader.readLine();
            AccessInfoBean accessInfoBean = new AccessInfoBean(username, password);
            UserBean userBean = new UserBean();
            userBean.setUserEmail(accessInfoBean.getUserEmail());
            userBean = loginApplicativo.verifyUser(accessInfoBean);
            Integer type = userBean.getUserType();
            switch (type) {
                case 0:
                    InternalControllerGrafico.getInternalControllerInstance().setLoggedUser(userBean);
                    new HomeScubaCLIGrafico().start();
                    break;
                case 1:
                    InternalControllerGrafico.getInternalControllerInstance().setLoggedUser(userBean);
                    new HomeFreeCLIGrafico().start();
                    break;
                case 2:
                    InternalControllerGrafico.getInternalControllerInstance().setLoggedUser(userBean);
                    new HomeManagerCLIGrafico().start();
                    break;
                default:
                    type = NOT_LOG;
            }

        } catch (IOException e) {
            logger1.log(Level.INFO, e.getMessage());
        } catch (NotExistentUserException e) {
            logger.log(Level.INFO, e.getMessage());
        }
    }
}
