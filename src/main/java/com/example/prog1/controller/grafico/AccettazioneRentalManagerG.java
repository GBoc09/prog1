package com.example.prog1.controller.grafico;

import com.example.prog1.model.Rental;
import com.example.prog1.pattern.state.RentalState;
import com.example.prog1.utilities.SwapPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

public class AccettazioneRentalManagerG {
    @FXML private RadioButton acceptButt;
    @FXML private ToggleGroup accettazione;
    @FXML private MenuItem courses;
    @FXML private MenuItem diving;
    @FXML private MenuItem equipment;
    @FXML private MenuItem home;
    @FXML private MenuItem logout;
    @FXML private Label manLabel;
    @FXML private MenuBar menuBar;
    @FXML private RadioButton rejectButt;
    @FXML private MenuItem rental;
    @FXML private Button sendEmail;
    private static final String MANAGER_HOME = "managerHome1.fxml";
    private static final String LOGIN_SCREEN = "login1.fxml";
    private static final String SELECT_EQUIP = "selectDivingMan.fxml";
    private static final String SHOW_DIV_MAN = "showDivingMan.fxml";
    private static final String ACCEPT_REJECT = "accettazioneRentalManager.fxml";
    private static final String ACCEPT = "accept";
    private static final String REJECT = "reject";
    private String decisione;

    /** gestione del pattern state per modificare lo stato dell'ordine */
    @FXML
    void onButtonClicked(ActionEvent event) {
        Node source = (Node) event.getSource();
        Rental rental1 = new Rental();
        RentalState stateRental = rental1.getStatoRental();
        if (acceptButt.isSelected() && source == sendEmail){
            decisione = ACCEPT;
            stateRental.gestioneStatoRental(rental1, decisione);
            /** comunicazione con un controller applicativo che inivia la mail */
        } else if (rejectButt.isSelected() && source == sendEmail) {
            decisione = REJECT;
            stateRental.gestioneStatoRental(rental1, decisione);
            /** comunicazione con controller applicativo che invia la mail */
        }
    }

    @FXML
    void onMenuItemSelected(ActionEvent event) throws IOException {
        MenuItem sourceItem = (MenuItem) event.getSource();
        if (sourceItem == home){
            SwapPage.getInstance().gotoPage(MANAGER_HOME);
        } else if (sourceItem == logout){
            SwapPage.getInstance().gotoPage(LOGIN_SCREEN);
        } else if (sourceItem == equipment){
            SwapPage.getInstance().gotoPage(SELECT_EQUIP);
        }else if (sourceItem == diving){
            SwapPage.getInstance().gotoPage(SHOW_DIV_MAN);
        } else if (sourceItem == rental){
            SwapPage.getInstance().gotoPage(ACCEPT_REJECT);
        }
    }

}

