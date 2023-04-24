package com.example.prog1.controller.cli_graphic;

import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.RegistrationApplicativo;
import com.example.prog1.controller.grafico.InternalControllerGrafico;
import com.example.prog1.exception.DuplicatedUserException;
import com.example.prog1.exception.InvalidFormatException;
import com.example.prog1.exception.SqlException;
import com.example.prog1.exception.StartException;
import com.example.prog1.utilities.PrinterCli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class SignUpCliControllerG extends ControllerGraficoManagementCli{
    private int userType;
    private int type;
    public static final int SCUBA_TYPE = 0;
    public static final int FREE_TYPE = 1;
    public static final int MANAGER_TYPE = 2;
    public static final int NOT_LOG = -1;
    @Override
    public void start() {
        while (true){
            int choice;
            try{
                choice = showMenu();
                switch (choice){
                    case 1 -> signup();
                    case 2 -> new SignUpCliControllerG().start();
                    case 3 -> System.exit(0);
                    default -> throw new InvalidFormatException("Invalid choice");
                }
            } catch (InvalidFormatException | IOException e){
                logger.log(Level.INFO, e.getMessage());
            }
        }
    }

    @Override
    public int showMenu() throws IOException {
        PrinterCli.printMessage("1) Sign Up\n");
        PrinterCli.printMessage("2) Go to Login\n");
        PrinterCli.printMessage("3) Quit\n");
        return getMenuChoice(1,3);
    }

    private void signup(){
        UserBean userBeanInfo = new UserBean();
        RegistrationApplicativo registrationApplicativo = new RegistrationApplicativo();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            PrinterCli.printMessage("Name: ");
            String name = reader.readLine();
            PrinterCli.printMessage("Lastname: ");
            String last = reader.readLine();
            PrinterCli.printMessage("License Number: ");
            String numb = reader.readLine();
            PrinterCli.printMessage("Email: ");
            String email = reader.readLine();
            PrinterCli.printMessage("Password: ");
            String pass  = reader.readLine();
            PrinterCli.printMessage("Confirm password: ");
            String conf = reader.readLine();
            PrinterCli.printMessage("Select role:\n 1) Scuba Diver \n 2) Free Diver\n 3) Diving Manager\n");
            String role = reader.readLine();

            userBeanInfo.setUserEmail(email);
            userBeanInfo.setPass(pass);
            userBeanInfo.setName(name);
            userBeanInfo.setSurname(last);
            userBeanInfo.setLicense(numb);

            if (role.equals("1")){
                userType = SCUBA_TYPE;
            } else if (role.equals("2")) {
                userType = FREE_TYPE;
            } else if (role.equals("3")) {
                userType = MANAGER_TYPE;
            }
            userBeanInfo.setUserType(userType);
            InternalControllerGrafico.getInternalControllerInstance().setLoggedUser(userBeanInfo);
            registrationApplicativo.registration(userBeanInfo);
            type = userBeanInfo.getUserType();
            switch (type){
                case 0 -> new HomeScubaCLIGrafico().start();
                case 1 -> new HomeFreeCLIGrafico().start();
                case 2 -> new HomeManagerCLIGrafico().start();
                default -> type = NOT_LOG;

            }
        } catch (IOException | DuplicatedUserException | StartException e) {
            logger.log(Level.INFO, e.getMessage());
        }
    }

}
