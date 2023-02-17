package com.example.prog1.controller.grafico;

import com.example.prog1.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManagerHomeControllerG {
    Logger logger = Logger.getLogger(ManagerHomeControllerG.class.getName());
    @FXML private Button addCourse;
    @FXML private Button addDiving;
    @FXML private Button addEqup;
    @FXML private Label goToLogin;
    @FXML private Button yourDivingCenter;
    @FXML private MenuItem courses;
    @FXML private MenuItem diving;
    @FXML private MenuItem equipment;
    @FXML private MenuItem home;
    @FXML private MenuItem logout;
    @FXML private MenuBar menuBar;
    @FXML
    void onMenuItemSelected(ActionEvent event) throws IOException {
        MenuItem sourceItem = (MenuItem) event.getSource();
        if (sourceItem == home){
            MainApp app = new MainApp();
            app.changeScene("managerHome1.fxml");
        } else if (sourceItem == logout){
            MainApp app = new MainApp();
            app.changeScene("login1.fxml");
        } else if (sourceItem == equipment){
//            MainApp app = new MainApp();
//            app.changeScene(/*pagina che mostra la lista dell'equip*/);
        }else if (sourceItem == diving){
//            MainApp app = new MainApp();
//            app.changeScene(/*pagina che mostra la lista dei diving associati ad un manager */);
        }

    }
    @FXML
    void aggiungiDiving(ActionEvent event) {
        try{
            MainApp app = new MainApp();
            app.changeScene("managerDivingInsert1.fxml");
        }
        catch (Exception e){
            logger.log(Level.INFO, "Exception Error");
        }
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
    void visualizzaDivingCenter(ActionEvent event) {
        //todo
    }

}

