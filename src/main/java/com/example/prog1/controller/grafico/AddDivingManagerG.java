package com.example.prog1.controller.grafico;

import com.example.prog1.MainApp;
import com.example.prog1.bean.DivingBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.ManagerApplicativo;
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

    private static final String SHOW_DIV_MAN = "showDivingMan.fxml";
    private static final String MANAGER_HOME = "managerHome1.fxml";
    private static final String LOGIN_SCREEN = "login1.fxml";
    private static final String SHOW_EQUIP_MAN = "showEquipManager.fxml";
    /**
     * button continue deve portare alla pagina che mostra l'elenco dei diving assegiati al manager; */
    @FXML
    void goOn(ActionEvent event) throws IOException {
        ManagerApplicativo managerApplicativo = new ManagerApplicativo();
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        DivingBean divingBean = new DivingBean(divingName.getText(), divingLoc.getText(), divingPhone.getText());
        managerApplicativo.addDiving(userBean,divingBean);
//        MainApp app = new MainApp();
//        app.changeScene("showDivingMan.fxml");
        SwapPage.getInstance().gotoPage(SHOW_DIV_MAN);
    }
    @FXML
    void onMenuItemSelected(ActionEvent event) throws IOException {
        MenuItem sourceItem = (MenuItem) event.getSource();
        if (sourceItem == home){
//            MainApp app = new MainApp();
//            app.changeScene("managerHome1.fxml");
            SwapPage.getInstance().gotoPage(MANAGER_HOME);
        } else if (sourceItem == logout){
//            MainApp app = new MainApp();
//            app.changeScene("login1.fxml");
            SwapPage.getInstance().gotoPage(LOGIN_SCREEN);
        } else if (sourceItem == equipment){
//            MainApp app = new MainApp();
//            app.changeScene("showEquipManager.fxml");
            SwapPage.getInstance().gotoPage(SHOW_EQUIP_MAN);
        }else if (sourceItem == diving){
//            MainApp app = new MainApp();
//            app.changeScene("showDivingMan.fxml");
            SwapPage.getInstance().gotoPage(SHOW_DIV_MAN);
        }

    }

}

