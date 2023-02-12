package com.example.prog1.controller.grafico;

import com.example.prog1.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ManagerHomeControllerG {
    Logger logger = Logger.getLogger(ManagerHomeControllerG.class.getName());
    @FXML
    private Button addCourse;

    @FXML
    private Button addDiving;

    @FXML
    private Button addEqup;

    @FXML
    private Label goToLogin;

    @FXML
    private Button yourDivingCenter;

    @FXML
    void aggiungiDiving(ActionEvent event) {

    }

    @FXML
    void aggiungiEquip(ActionEvent event) {
        try{
            MainApp app = new MainApp();
            app.changeScene("addEquip1.fxml");
        }
        catch (Exception e){
            logger.log(Level.INFO, "Exception Error");
        }
    }

    @FXML
    void goToLogin(MouseEvent event) {
        try{
            MainApp app = new MainApp();
            app.changeScene("login1.fxml");
        }
        catch (Exception e){
            logger.log(Level.INFO, "Exception Error");
        }
    }

    @FXML
    void visualizzaDivingCenter(ActionEvent event) {

    }

}

