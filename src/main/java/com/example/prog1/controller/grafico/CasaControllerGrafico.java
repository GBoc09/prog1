package com.example.prog1.controller.grafico;

import com.example.prog1.utilities.SwapPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CasaControllerGrafico {
   private static final String LOGIN_SCREEN = "login1.fxml";
   @FXML private Button access;
   Logger logger = Logger.getLogger(CasaControllerGrafico.class.getName());
   @FXML
    public void accessButtonClicked(ActionEvent event) throws IOException {
      try{
          SwapPage.getInstance().gotoPage(LOGIN_SCREEN);
      }
      catch (Exception e){
         logger.log(Level.INFO, "Exception Error");
      }
   }
}