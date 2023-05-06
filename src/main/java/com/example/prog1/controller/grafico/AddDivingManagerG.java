package com.example.prog1.controller.grafico;

import com.example.prog1.bean.DivingBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.RentalEquipApplicativo;
import com.example.prog1.utilities.SwapPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddDivingManagerG {
    @FXML private MenuItem courses;
    @FXML private MenuItem diving;
    @FXML private TextField divingLoc;
    @FXML private TextField divingName;
    @FXML private TextField divingPhone;
    @FXML private MenuItem equipment;
    @FXML private Label errorLabel;
    @FXML private Button goOn;
    @FXML private MenuItem home;
    @FXML private MenuItem logout;
    @FXML private MenuBar menuBar;
    @FXML private MenuItem rental;

    private static final String SHOW_DIV_MAN = "showDivingMan.fxml";
    private static final String MANAGER_HOME = "managerHome1.fxml";
    private static final String LOGIN_SCREEN = "login1.fxml";
    private static final String SELECT_EQUIP = "selectDivingMan.fxml";
    private static final String ACCEPT_REJECT = "accettazioneRentalManager.fxml";
    /**
     * button continue deve portare alla pagina che mostra l'elenco dei diving assegiati al manager; */
    @FXML
    void goOn(ActionEvent event) throws IOException {
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        DivingBean divingBean = new DivingBean(divingName.getText(), divingLoc.getText(), divingPhone.getText());
        rentalEquipApplicativo.addDiving(userBean,divingBean);
        SwapPage.getInstance().gotoPage(SHOW_DIV_MAN);
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

