package com.example.prog1.controller.grafico;

import com.example.prog1.utilities.SwapPage;
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

public class ScubaHomeControllerGrafico {
    @FXML private Button bookCourse;
    @FXML private MenuItem cart;
    @FXML private Label goToLogin;
    @FXML private MenuItem home;
    @FXML private Button addDiveToLog;
    @FXML private MenuItem logout;
    @FXML private MenuBar menuBar;
    @FXML private Button rentEqip;
    @FXML private MenuItem rent;
    Logger logger = Logger.getLogger(ScubaHomeControllerGrafico.class.getName());
    private static final String LOGIN_SCREEN = "login1.fxml";
    private static final String SCUBA_HOME = "scubaHome1.fxml";
    private static final String CART_SCREEN = "cart1.fxml";
    private static final String CHOOSE_DIVING = "chooseDivingScuba.fxml";
    private static final String SUMMARY_RENT_SCUBA = "summaryRentalScuba.fxml";
    @FXML
    void goToLogin(MouseEvent event) {
        try{
            SwapPage.getInstance().gotoPage(LOGIN_SCREEN);
        } catch (Exception e){
            logger.log(Level.INFO, "Exception Error");
        }
    }
    @FXML
    void onMenuItemSelected(ActionEvent event) throws IOException {
        MenuItem sourceItem = (MenuItem) event.getSource();
        if (sourceItem == home){
            SwapPage.getInstance().gotoPage(SCUBA_HOME);
        } else if (sourceItem == logout){
            SwapPage.getInstance().gotoPage(LOGIN_SCREEN);
        } else if (sourceItem == cart){
            SwapPage.getInstance().gotoPage(CART_SCREEN);
        } else if (sourceItem == rent) {
            SwapPage.getInstance().gotoPage(SUMMARY_RENT_SCUBA);
        }

    }
    /**
     * INIZIO USE CASE
     * */
    @FXML
    void rentEquip(ActionEvent event) {
        try{
            SwapPage.getInstance().gotoPage(CHOOSE_DIVING);
        } catch (Exception e){
            logger.log(Level.INFO, "Exception Error");
        }
    }

}

