package com.example.prog1.controller.grafico;

import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.RegistrationApplicativo;
import com.example.prog1.exception.DuplicatedUserException;
import com.example.prog1.exception.InvalidCredentialException;
import com.example.prog1.exception.SqlException;
import com.example.prog1.utilities.SwapPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistrationControllerGrafico {
    @FXML private ToggleGroup attivita;
    @FXML private TextField email;
    @FXML private RadioButton freeCheck;
    @FXML private TextField lastname;
    @FXML private TextField license;
    @FXML private RadioButton managerCheck;
    @FXML private TextField name;
    @FXML private PasswordField password;
    @FXML private PasswordField password1;
    @FXML private RadioButton scubaCheck;
    @FXML private Button signUp;

    private int userType;
    private int type;
    public static final int SCUBA_TYPE = 0;
    public static final int FREE_TYPE = 1;
    public static final int MANAGER_TYPE = 2;
    public static final int NOT_LOG = -1;
    private static final String LOGIN_SCREEN = "login1.fxml";
    private static final String SCUBA_SCREEN = "scubaHome1.fxml";
    private static final String FREE_SCREEN = "freeHome1.fxml";
    private static final String MAN_SCREEN = "managerHome1.fxml";
    @FXML
    void onBackClicked(MouseEvent event) throws IOException {
        SwapPage.getInstance().gotoPage(LOGIN_SCREEN);
    }
    Logger logger = Logger.getLogger(CartControllerGrafico.class.getName());
    @FXML
    void signUp(ActionEvent event) throws InvalidCredentialException {
        UserBean userBean;
        try {
            userBean = insertInfo();
            RegistrationApplicativo registrationApplicativo = new RegistrationApplicativo();
            registrationApplicativo.registration(userBean);
            type = userBean.getUserType();
            switch (type) {
                case 0:
                    SwapPage.getInstance().gotoPage(SCUBA_SCREEN);
                    break;
                case 1:
                    SwapPage.getInstance().gotoPage(FREE_SCREEN);
                    break;
                case 2:
                    SwapPage.getInstance().gotoPage(MAN_SCREEN);
                    break;
                default: type = NOT_LOG;
            }
            Alert alert = new Alert( Alert.AlertType.CONFIRMATION, "Registration successful");
            alert.showAndWait();
        }catch (DuplicatedUserException | IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "You are already registered");
            alert.showAndWait();
        } catch (InvalidCredentialException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please check credentials");
            alert.showAndWait();
            throw new InvalidCredentialException();
        } catch (SqlException e) {
            logger.log(Level.INFO, e.getMessage());
        }
    }
    private UserBean insertInfo() throws InvalidCredentialException {
        if (email.getText().isEmpty() || password.getText().isEmpty() || password1.getText().isEmpty() || name.getText().isEmpty() || lastname.getText().isEmpty()
                || license.getText().isEmpty()) {
            throw new InvalidCredentialException("Fill all fields");
        }
        if (!password.getText().equals(password1.getText())){
            throw new InvalidCredentialException("The password must be the same");
        }
        UserBean userBeanInfo = new UserBean();
        userBeanInfo.setUserEmail(email.getText());
        userBeanInfo.setPass(password.getText());
        userBeanInfo.setName(name.getText());
        userBeanInfo.setSurname(lastname.getText());
        userBeanInfo.setLicense(license.getText());
        if (scubaCheck.isSelected()) {
            userType = SCUBA_TYPE;
        } else if (freeCheck.isSelected()) {
            userType = FREE_TYPE;
        } else if (managerCheck.isSelected()) {
            userType= MANAGER_TYPE;
        }
        userBeanInfo.setUserType(userType);

        InternalControllerGrafico.getInternalControllerInstance().setLoggedUser(userBeanInfo);
        return userBeanInfo;
    }
}
