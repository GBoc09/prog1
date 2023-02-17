package com.example.prog1.controller.grafico;

import com.example.prog1.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowDivingManagerG implements Initializable {

    @FXML private MenuItem courses;
    @FXML private MenuItem diving;
    @FXML private MenuItem equipment;
    @FXML private MenuItem home;
    @FXML private ListView<String> listView;
    @FXML private MenuItem logout;
    @FXML private MenuBar menuBar;
    @FXML void onMenuItemSelected(ActionEvent event) throws IOException {
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

