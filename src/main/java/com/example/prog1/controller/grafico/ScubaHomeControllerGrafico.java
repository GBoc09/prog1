package com.example.prog1.controller.grafico;

import com.example.prog1.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScubaHomeControllerGrafico {

    @FXML
    private Button bookCourse;

    @FXML
    private MenuItem cart;

    @FXML
    private Label goToLogin;

    @FXML
    private MenuItem home;

    @FXML
    private Button logBook;

    @FXML
    private MenuItem logbook;

    @FXML
    private MenuItem logout;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button rentEqip;
    Logger logger = Logger.getLogger(ScubaHomeControllerGrafico.class.getName());

    @FXML
    void goToLogin(MouseEvent event) {
        try{
        MainApp app = new MainApp();
        app.changeScene("login1.fxml");
        } catch (Exception e){
            logger.log(Level.INFO, "Exception Error");
        }
    }
    @FXML
    void onMenuItemSelected(ActionEvent event) throws IOException {
        MenuItem sourceItem = (MenuItem) event.getSource();
        BorderPane scubaBorderPane = (BorderPane) menuBar.getScene().getRoot();
        InternalControllerGrafico.getInternalControllerInstance().onNextScreen(scubaBorderPane);
        String newNode;
        if (sourceItem == home){
            MainApp app = new MainApp();
            app.changeScene("scubaHome.fxml");
        } else if (sourceItem == logout){
            InternalControllerGrafico.getInternalControllerInstance().onBackClicked(event);
        } else if (sourceItem == cart){
            /* aggiungere un controller grafico per il carrello */
            MainApp app = new MainApp();
            app.changeScene("cart1.fxml");
        }

    }
    /**
     * INIZIO USE CASE
     * */
    @FXML
    void rentEquip(ActionEvent event) {
        try{
            MainApp app = new MainApp();
            app.changeScene("rentEquip1.fxml");
        } catch (Exception e){
            logger.log(Level.INFO, "Exception Error");
        }
    }

}

