package com.example.prog1.controller.grafico;

import com.example.prog1.MainApp;
import com.example.prog1.bean.AccessInfoBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.LoginApplicativo;
import com.example.prog1.exception.NotExistentUserException;
import com.example.prog1.utilities.SwapPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;


import java.io.IOException;

public class LoginControllerGrafico {
    private static final String REGISTER_SCREEN = "registration1.fxml";
    private static final String SCUBA_SCREEN = "scubaHome1.fxml";
    private static final String FREE_SCREEN = "freeHome1.fxml";
    private static final String MANAGER_SCREEN = "managerHome1.fxml";

    @FXML private Button createAccout;
    @FXML private TextField email;
    @FXML private Button entry;
    @FXML private PasswordField userPass;
    private Integer type = -1;
    @FXML
    void onButtonClicked(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Integer userType;
        if(source == entry) {
            if (email.getText().isEmpty() || userPass.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Fill all fields!");
                alert.showAndWait();
                return;
            }
            UserBean userBean = login();
            userType = userBean.getUserType();
            if(userBean != null) {
                switch(userType){
                    case 0:
                        InternalControllerGrafico.getInternalControllerInstance().setLoggedUser(userBean);
//                        MainApp app = new MainApp();
//                        app.changeScene(SCUBA_SCREEN);
                        SwapPage.getInstance().gotoPage(SCUBA_SCREEN);
                        break;
                    case 1:
                        InternalControllerGrafico.getInternalControllerInstance().setLoggedUser(userBean);
//                        MainApp app1 = new MainApp();
//                        app1.changeScene(FREE_SCREEN);
                        SwapPage.getInstance().gotoPage(FREE_SCREEN);
                        break;
                    case 2:
                        InternalControllerGrafico.getInternalControllerInstance().setLoggedUser(userBean);
//                        MainApp app2 = new MainApp();
//                        app2.changeScene(MANAGER_SCREEN);
                        SwapPage.getInstance().gotoPage(MANAGER_SCREEN);
                        break;
                    default: userType = type;
                }
            }
         } else if (source == createAccout) {
//            MainApp app = new MainApp();
//            app.changeScene(REGISTER_SCREEN);
            SwapPage.getInstance().gotoPage(REGISTER_SCREEN);
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
