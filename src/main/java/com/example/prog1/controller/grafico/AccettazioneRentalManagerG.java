package com.example.prog1.controller.grafico;

import com.example.prog1.model.Rental;
import com.example.prog1.pattern.state.State;
import com.example.prog1.utilities.MenuBarManegerManagement;
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
    private static final String ACCEPT = "accept";
    private static final String REJECT = "reject";
    private String decisione;

    /** gestione del pattern state per modificare lo stato dell'ordine */
    @FXML
    void onButtonClicked(ActionEvent event) {
        Node source = (Node) event.getSource();
        Rental rental1 = new Rental();
        State stateRental = rental1.getStatoRental();
        if (acceptButt.isSelected() && source == sendEmail){
            decisione = ACCEPT;
            stateRental.gestioneStatoRental(rental1, decisione);
            stateRental = rental1.getStatoRental();
            System.out.println("stato ordine"+stateRental);
            /** comunicazione con un controller applicativo che inivia la mail */
        } else if (rejectButt.isSelected() && source == sendEmail) {
            decisione = REJECT;
            stateRental.gestioneStatoRental(rental1, decisione);
            stateRental = rental1.getStatoRental();
            System.out.println("stato ordine"+stateRental);
            /** comunicazione con controller applicativo che invia la mail */
        }
    }

    @FXML
    void onMenuItemSelected(ActionEvent event) throws IOException {
        MenuItem sourceItem = (MenuItem) event.getSource();
        if (sourceItem == home){
            MenuBarManegerManagement.getMenuBarManagerInstance().homeMan(sourceItem);
        } else if (sourceItem == logout){
            MenuBarManegerManagement.getMenuBarManagerInstance().logOut(sourceItem);
        } else if (sourceItem == equipment){
            MenuBarManegerManagement.getMenuBarManagerInstance().selectDivingMan(sourceItem);
        }else if (sourceItem == diving){
            MenuBarManegerManagement.getMenuBarManagerInstance().showDivingMan(sourceItem);
        } else if (sourceItem == rental){
            MenuBarManegerManagement.getMenuBarManagerInstance().acceptReject(sourceItem);
        }
    }

}

