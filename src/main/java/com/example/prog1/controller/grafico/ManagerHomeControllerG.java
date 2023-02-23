package com.example.prog1.controller.grafico;

import com.example.prog1.MainApp;
import com.example.prog1.utilities.SwapPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.io.IOException;
public class ManagerHomeControllerG {
    @FXML private Button addCourse;
    @FXML private Button addDiving;
    @FXML private Button addEquip;
    @FXML private Button yourDivingCenter;
    @FXML private MenuItem courses;
    @FXML private MenuItem diving;
    @FXML private MenuItem equipment;
    @FXML private MenuItem home;
    @FXML private MenuItem logout;
    @FXML private MenuBar menuBar;
    @FXML private Button divingEquip;

    private static final String MANAGER_HOME = "managerHome1.fxml";
    private static final String LOGIN_SCREEN = "login1.fxml";
    private static final String SHOW_DIV_MAN = "showDivingMan.fxml";
    private static final String SELECT_DIV_MAN = "selectDivingMan.fxml";
    private static final String INSERT_DIV_MAN = "managerDivingInsert1.fxml";
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
//           app.changeScene("selectDivingMan.fxml");
            SwapPage.getInstance().gotoPage(SELECT_DIV_MAN);

        }else if (sourceItem == diving){
//            MainApp app = new MainApp();
//            app.changeScene("showDivingMan.fxml");
            SwapPage.getInstance().gotoPage(SHOW_DIV_MAN);
        }

    }
    @FXML
    void onSelectedButton(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        if (source == addDiving){
//            MainApp app = new MainApp();
//            app.changeScene("managerDivingInsert1.fxml");
            SwapPage.getInstance().gotoPage(INSERT_DIV_MAN);
        } else if (source == addEquip || source == divingEquip){
//            MainApp app = new MainApp();
//            app.changeScene("selectDivingMan.fxml");
            SwapPage.getInstance().gotoPage(SELECT_DIV_MAN);
        } else if (source == yourDivingCenter) {
//            MainApp app = new MainApp();
//            app.changeScene("showDivingMan.fxml");
            SwapPage.getInstance().gotoPage(SHOW_DIV_MAN);
        }
    }
}

