package com.example.prog1.controller.grafico;

import com.example.prog1.MainApp;
import com.example.prog1.bean.DivingBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.ManagerApplicativo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AddDivingManagerG {

    @FXML private MenuItem courses;
    @FXML private MenuItem diving;
    @FXML private TextField divingID;
    @FXML private TextField divingLoc;
    @FXML private TextField divingName;
    @FXML private TextField divingPhone;
    @FXML private MenuItem equipment;
    @FXML private Label errorLabel;
    @FXML private Label goHome;
    @FXML private Button goOn;
    @FXML private MenuItem home;
    @FXML private MenuItem logout;
    @FXML private MenuBar menuBar;
    @FXML
    void goOn(ActionEvent event) { // button continue deve portare alla pagina che mostra l'elenco dei diving assogiati al manager;
        ManagerApplicativo managerApplicativo = new ManagerApplicativo();
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        DivingBean divingBean = new DivingBean(divingName.getText(), divingLoc.getText(), divingPhone.getText());
        managerApplicativo.addDiving(userBean,divingBean);
    }
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

}

