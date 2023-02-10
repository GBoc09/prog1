package com.example.prog1.controller.grafico;

import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.RegistrationApplicativo;
import com.example.prog1.exception.DuplicatedUserException;
import com.example.prog1.exception.InvalidCredentialException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

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

    private Integer userType;

    @FXML
    void onBackClicked(MouseEvent event) {
        ScreenControllerGrafico.getInstance().onBackClick((Node) event.getSource());

    }

    @FXML
    void signUp(ActionEvent event) {
        Node source = (Node)event.getSource();
        UserBean userBean;
        try {
            userBean = takeInfo();
            RegistrationApplicativo registrationApplicativo = new RegistrationApplicativo();
            registrationApplicativo.registration(userBean);

            Alert alert = new Alert( Alert.AlertType.CONFIRMATION, "Registration successful");
            alert.showAndWait();
            ScreenControllerGrafico.getInstance().onBackClick(source);
        }catch (InvalidCredentialException | DuplicatedUserException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Try again");
            alert.showAndWait();
        }
    }
    private UserBean takeInfo() throws InvalidCredentialException {
        UserBean userBeanInfo = new UserBean();
        userBeanInfo.setUserEmail(email.getText());
        userBeanInfo.setPass(password.getText());
        userBeanInfo.setName(name.getText());
        userBeanInfo.setSurname(lastname.getText());
        userBeanInfo.setLicense(license.getText());
        userBeanInfo.setUserType(userType);

        if (email.getText().isEmpty() || password.getText().isEmpty() || password1.getText().isEmpty() || name.getText().isEmpty() || lastname.getText().isEmpty()
        || license.getText().isEmpty()) {
            throw new InvalidCredentialException("Fill all fields");
        }
        if (!password.getText().equals(password1.getText())){
            throw new InvalidCredentialException("The password must be the same");
        }
        InternalControllerGrafico.getInternalControllerInstance().setLoggedUser(userBeanInfo);
        return userBeanInfo;
    }
}
