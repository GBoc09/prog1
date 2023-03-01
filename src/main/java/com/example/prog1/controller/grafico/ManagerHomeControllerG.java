package com.example.prog1.controller.grafico;

import com.example.prog1.utilities.MenuBarManegerManagement;
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
    @FXML private MenuItem rental;

    private static final String SHOW_DIV_MAN = "showDivingMan.fxml";
    private static final String SELECT_DIV_MAN = "selectDivingMan.fxml";
    private static final String INSERT_DIV_MAN = "managerDivingInsert1.fxml";
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
    @FXML
    void onSelectedButton(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        if (source == addDiving){
            SwapPage.getInstance().gotoPage(INSERT_DIV_MAN);
        } else if (source == addEquip || source == divingEquip){
            SwapPage.getInstance().gotoPage(SELECT_DIV_MAN);
        } else if (source == yourDivingCenter) {
            SwapPage.getInstance().gotoPage(SHOW_DIV_MAN);
        }
    }
}

