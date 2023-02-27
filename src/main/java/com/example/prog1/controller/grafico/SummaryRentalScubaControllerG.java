package com.example.prog1.controller.grafico;

import com.example.prog1.utilities.SwapPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.io.IOException;

public class SummaryRentalScubaControllerG {
    @FXML private MenuItem cart;
    @FXML private MenuItem home;
    @FXML private ListView<String> listView;
    @FXML private MenuItem logbook;
    @FXML private MenuItem logout;
    @FXML private MenuBar menuBar;
    @FXML private MenuItem rent;
    @FXML private Label divingLabel;
    @FXML private Label rentLabel;
    @FXML private Label totalLabel;

    private static final String LOGIN_SCREEN = "login1.fxml";
    private static final String SCUBA_HOME = "scubaHome1.fxml";
    private static final String CART_SCREEN = "cart1.fxml";
    private static final String SUMMARY_RENT_SCUBA = "summaryRentalScuba.fxml";

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

}

