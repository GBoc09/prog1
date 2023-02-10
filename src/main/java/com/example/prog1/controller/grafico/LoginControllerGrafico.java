package com.example.prog1.controller.grafico;

import com.example.prog1.MainApp;
import com.example.prog1.bean.AccessInfoBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.LoginApplicativo;
import com.example.prog1.exception.NotExistentUserException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginControllerGrafico {
    private static final String REGISTER_SCREEN = "registration1.fxml";
    @FXML
    private Button createAccout;

    @FXML
    private TextField email;

    @FXML
    private Button entry;

    @FXML
    private Label errorLabel;

    @FXML
    private PasswordField userPass;
    @FXML
    public void onBackClicked(ActionEvent event){
        ScreenControllerGrafico.getInstance().onBackClick((Node)event.getSource());
    }
    @FXML
    void onButtonClicked(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        ScreenControllerGrafico.getInstance().pushScreen(source.getScene());
        Stage stage = (Stage) (source).getScene().getWindow();

        if(source == entry) {
            if (email.getText().isEmpty() || userPass.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Fill all fields!");
                alert.showAndWait();
                return;
            }
            UserBean userBean = login();
            if(userBean != null) {
                InternalControllerGrafico.getInternalControllerInstance().enterAsUser(null, stage);
            }
         } else if (source == createAccout) {
//            ScreenControllerGrafico.getInstance().pushScreen(source.getScene());
//            FXMLLoader registationLoader = new FXMLLoader(getClass().getClassLoader().getResource(REGISTER_SCREEN));
//            stage.setScene(new Scene(registationLoader.load()));
            MainApp app = new MainApp();
            app.changeScene(REGISTER_SCREEN);
        }
    }
    private UserBean login(){
        String userEmail = email.getText();
        String pass = userPass.getText();
        AccessInfoBean accessInfoBean = new AccessInfoBean(userEmail, pass);
        LoginApplicativo loginApplicativo = new LoginApplicativo();

        UserBean loggedUser = null;
        try {
            loggedUser = loginApplicativo.verifyUser(accessInfoBean);
        }catch (NotExistentUserException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
        return loggedUser;
    }

}
