package com.example.prog1.controller.grafico;

import com.example.prog1.MainApp;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.RegistrationApplicativo;
import com.example.prog1.exception.DuplicatedUserException;
import com.example.prog1.exception.InvalidCredentialException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class RegistrationControllerGrafico {
    public static final String SCUBA_HOME = "scubaHome1.fxml";
    public static final String FREE_HOME = "freeHome1.fxml";
    public static final String MANAGER_HOME = "managerHome1.fxml";

    @FXML
    private ToggleGroup attivita;

    @FXML
    private TextField email;

    @FXML
    private Label errorLabel;

    @FXML
    private RadioButton freeCheck;

    @FXML
    private TextField lastname;

    @FXML
    private TextField license;

    @FXML
    private RadioButton managerCheck;

    @FXML
    private TextField name;

    @FXML
    private Label onBackClicked;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField password1;

    @FXML
    private RadioButton scubaCheck;

    @FXML
    private Button signUp;

    private int userType;
    private int type;
    public static final int SCUBA_TYPE = 0;
    public static final int FREE_TYPE = 1;
    public static final int MANAGER_TYPE = 2;
    public static final int NOT_LOG = -1;
    private static final String LOGIN_SCREEN = "login1.fxml";
    @FXML
    void onBackClicked(MouseEvent event) throws IOException {
        ScreenControllerGrafico.getInstance().onBackClick((Node) event.getSource());
        MainApp app = new MainApp();
        app.changeScene(LOGIN_SCREEN);
    }
    @FXML
    void signUp(ActionEvent event) {
        Node source = (Node)event.getSource();
        UserBean userBean;
        try {
            userBean = insertInfo();
            RegistrationApplicativo registrationApplicativo = new RegistrationApplicativo();
            registrationApplicativo.registration(userBean);
            type = userBean.getUserType();
            switch (type) {
                case 0:
                    MainApp app = new MainApp();
                    app.changeScene("scubaHome1.fxml");
                    break;
                case 1:
                    MainApp app1 = new MainApp();
                    app1.changeScene("freeHome1.fxml");
                    break;
                case 2:
                    MainApp app2 = new MainApp();
                    app2.changeScene("managerHome1.fxml");
                    break;
                default: type = NOT_LOG;
            }
            Alert alert = new Alert( Alert.AlertType.CONFIRMATION, "Registration successful");
            alert.showAndWait();
          //  ScreenControllerGrafico.getInstance().onBackClick(source);
        }catch (InvalidCredentialException | DuplicatedUserException | IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "You are already registered");
            alert.showAndWait();
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
