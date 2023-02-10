package com.example.prog1.controller.grafico;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ScreenControllerGrafico {
    private static ScreenControllerGrafico screenControllerGrafico;
    private final ArrayList<Scene> prevScene;

    private ScreenControllerGrafico(){
        prevScene = new ArrayList<>();
    }
    @FXML
    public void onBackClick(Node sourceNode){
        Stage stage = (Stage) (sourceNode).getScene().getWindow();
        Scene scene = prevScene.remove(prevScene.size()-1);
        stage.setScene(scene);
    }
    public static ScreenControllerGrafico getInstance(){
        if(screenControllerGrafico == null){
            screenControllerGrafico = new ScreenControllerGrafico();
        }
        return screenControllerGrafico;
    }
    public void pushScreen(Scene sceneName){
        this.prevScene.add(sceneName);
    }
}
