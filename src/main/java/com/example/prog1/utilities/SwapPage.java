package com.example.prog1.utilities;

import com.example.prog1.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SwapPage {
    private static SwapPage instance = null;
    protected Stage stg;
    public Stage getStg(){
        return this.stg;
    }
    protected SwapPage(Stage stg){
        this.stg = stg;
    }
    public static synchronized SwapPage getInstance(Stage stage){
        if(SwapPage.instance == null){
            SwapPage.instance = new SwapPage(stage);
        }
        return instance;
    }
    public static synchronized SwapPage getInstance(){
        return instance;
    }
    public void gotoPage(String fxml) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(MainApp.class.getResource(fxml)));
        stg.getScene().setRoot(root);
    }
}
