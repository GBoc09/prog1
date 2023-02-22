package com.example.prog1.controller.grafico;

import com.example.prog1.MainApp;
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
            MainApp app = new MainApp();
            app.changeScene("showEquipManager.fxml");
        }else if (sourceItem == diving){
            MainApp app = new MainApp();
            app.changeScene("showDivingMan.fxml");
        }

    }
    @FXML
    void onSelectedButton(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        if (source == addDiving){
            MainApp app = new MainApp();
            app.changeScene("managerDivingInsert1.fxml");
        } else if (source == addEquip){
            MainApp app = new MainApp();
            app.changeScene("selectDivingMan.fxml");
        } else if (source == divingEquip) {
            MainApp app = new MainApp();
            app.changeScene("showEquipManager.fxml");
        } else if (source == yourDivingCenter) {
            MainApp app = new MainApp();
            app.changeScene("showDivingMan.fxml");
        }
    }
}

