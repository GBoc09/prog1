package com.example.prog1.controller.cli_graphic;

import com.example.prog1.bean.AccessInfoBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.LoginApplicativo;
import com.example.prog1.controller.grafico.CasaControllerGrafico;
import com.example.prog1.controller.grafico.InternalControllerGrafico;
import com.example.prog1.exception.InvalidCredentialException;
import com.example.prog1.exception.InvalidFormatException;
import com.example.prog1.exception.NotExistentUserException;
import com.example.prog1.exception.SqlException;
import com.example.prog1.utilities.PrinterCli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginCliControllerGrafico extends ControllerGraficoManagementCli{
    Logger logger = Logger.getLogger(LoginCliControllerGrafico.class.getName());
    public static final int SCUBA_TYPE = 0;
    public static final int FREE_TYPE = 1;
    public static final int MANAGER_TYPE = 2;
    public static final int NOT_LOG = -1;
    @Override
    public void start(){
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
                    default:
                        throw new InvalidFormatException("Invalid choice");
                }
            } catch (InvalidFormatException e){
                logger.log(Level.INFO, e.getMessage());
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
    private void login(){
        LoginApplicativo loginApplicativo = new LoginApplicativo();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
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
                case 0 :
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
                default: type = NOT_LOG;
            }

        } catch (IOException e){
            logger.log(Level.INFO, e.getMessage());
        } catch (NotExistentUserException e) {
            throw new RuntimeException(e);
        } catch (SqlException e) {
            throw new RuntimeException(e);
        }
    }
}
