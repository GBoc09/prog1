package com.example.prog1.controller.grafico;

import com.example.prog1.bean.UserBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.prog1.controller.grafico.EnterAsAUserControllerGrafico.*;

public class InternalControllerGrafico {
    public static final String SCUBA_HOME = "scubaHome1.fxml";
    public static final String FREE_HOME = "freeHome1.fxml";
    public static final String MANAGER_HOME = "managerHome1.fxml";
    public static final Integer NOT_LOGGED = -1;

    private final ArrayList<Node> internalNode;
    private static InternalControllerGrafico internalControllerGrafico;
    private UserBean loggedUser;

    private InternalControllerGrafico(){
        this.internalNode = new ArrayList<>();
        loggedUser = null;
    }
    public static InternalControllerGrafico getInternalControllerInstance () {
        if(internalControllerGrafico == null){
            internalControllerGrafico = new InternalControllerGrafico();
        }
        return internalControllerGrafico;
    }
    public void emptyStack (){
        internalNode.remove(internalNode);
    }
    public void backToHome (Node source){
        BorderPane sceneRoot = getBorderPane(source);
        Node home = null;
        while (! internalNode.isEmpty()){
            home = internalNode.remove(internalNode.size()-1);
        }
        if (home != null){
            sceneRoot.setCenter(home);
        }
    }
    public void onBackClicked(ActionEvent event){
        Node source = (Node) event.getSource();
        BorderPane sceneRoot = getBorderPane(source);
        if (! internalNode.isEmpty()){
            Node next = internalNode.remove(internalNode.size()-1);
            sceneRoot.setCenter(next);
        }
    }
    public void onNextScreen(Node source){
        this.internalNode.add(getBorderPane(source).getCenter());
    }
    public void onMenuItemClicked(){
        Node home = null;
        while (!internalNode.isEmpty()){
            home = internalNode.remove(internalNode.size()-1);
        }
        if (home != null){
            internalNode.add(home);
        }
    }
    private BorderPane getBorderPane(Node source){
        Scene scene = source.getScene();
        return (BorderPane) scene.getRoot();
    }
    public UserBean getLoggedUser(){
        return loggedUser;
    }

    public void setLoggedUser(UserBean loggedUser) {
        this.loggedUser = loggedUser;
    }
    public void enterAsUser (UserBean userBean, Stage stage) throws IOException {
        String homeScreen = null;
        setLoggedUser(userBean);
        if(userBean == null || userBean.getUserType() == SCUBA_TYPE ){
            homeScreen = SCUBA_HOME;
        } else if (userBean == null || userBean.getUserType() == FREE_TYPE) {
            homeScreen = FREE_HOME;
        } else if (userBean == null || userBean.getUserType() == MANAGER_TYPE) {
            homeScreen = MANAGER_HOME;
        }
        FXMLLoader userScreen = new FXMLLoader(getClass().getClassLoader().getResource(homeScreen));
        Scene homeScene = new Scene(userScreen.load());
        BorderPane homePane = (BorderPane) homeScene.getRoot();
        homePane.setCenter(userScreen.load());
        stage.setScene(homeScene);
    }
}
