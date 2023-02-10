package com.example.prog1.controller.grafico;

import com.example.prog1.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CasaControllerGrafico {
   private static final String LOGIN_SCREEN = "login1.fxml";
   @FXML
   private Button access;
   Logger logger = Logger.getLogger(CasaControllerGrafico.class.getName());
   @FXML
    public void accessButtonClicked(ActionEvent event) throws IOException {
      try{
        MainApp app = new MainApp();
         app.changeScene("login1.fxml");
      }
      catch (Exception e){
         logger.log(Level.INFO, "Exception Error");
      }
   }

}