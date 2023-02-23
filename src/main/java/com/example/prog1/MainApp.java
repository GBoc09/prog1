package com.example.prog1;

import com.example.prog1.utilities.SwapPage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MainApp extends Application {
//    private static Stage stg;
//    public static void setStg(Stage stage) {
//        stg = stage;
//    }
    @Override
    public void start(Stage primaryStage) throws IOException {
//        setStg(primaryStage);
//        Parent root =  FXMLLoader.load(getClass().getResource("casa1.fxml"));
        String homePage = "casa1.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(homePage));
        Scene scene = new Scene(root);
        Image image = new Image(new File("src/main/resources/com/example/prog1/img/LogoGiulia.jpg").toURI().toString());
//        primaryStage.getIcons().add(image);
//
//        primaryStage.setTitle("DiversWorld");
//        primaryStage.setScene(scene);
//        primaryStage.show();
        SwapPage swapPage = SwapPage.getInstance(primaryStage);
        swapPage.getStg().setTitle("DiversWorld");
        swapPage.getStg().getIcons().add(image);
        swapPage.getStg().setScene(scene);
        swapPage.getStg().show();
    }
//    public void changeScene(String fxml) throws IOException {
//        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
//        stg.getScene().setRoot(pane);
//    }
    public static void main(String[] args) {
        launch();
    }
}