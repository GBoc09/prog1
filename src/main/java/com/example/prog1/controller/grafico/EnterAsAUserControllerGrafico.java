package com.example.prog1.controller.grafico;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class EnterAsAUserControllerGrafico{
    public static final int SCUBA_TYPE = 0;
    public static final int FREE_TYPE = 1;
    public static final int MANAGER_TYPE = 2;

    public static final String SCUBA_HOME = "scubaHome1.fxml";
    public static final String FREE_HOME = "freeHome1.fxml";
    public static final String MANAGER_HOME = "managerHome1.fxml";

    public void enterAsUser(int userType, Scene mainScene) throws IOException {
        BorderPane container = (BorderPane) mainScene.getRoot();
        String mainScreen = null;
        if (userType == SCUBA_TYPE){
            mainScreen = SCUBA_HOME;
        } else if (userType == FREE_TYPE) {
            mainScreen = FREE_HOME;
        } else if ( userType == MANAGER_TYPE) {
            mainScreen = MANAGER_HOME;
        }
        if (container != null && mainScreen != null ){
            FXMLLoader userHome = new FXMLLoader(getClass().getResource(mainScreen));
            container.setCenter(userHome.load());
        }
    }
}
