package com.example.prog1.controller.grafico;

import com.example.prog1.MainApp;
import com.example.prog1.bean.EquipBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.ManagerApplicativo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddEquipManagerG {

    @FXML private TextField equipType;
    @FXML private Button addProduct;
    @FXML private TextField availability;
    @FXML private Label errorLabel;
    @FXML private Label goHome;
    @FXML private TextField price;
    @FXML private ChoiceBox<String> size;
    @FXML private MenuItem courses;
    @FXML private MenuItem diving;
    @FXML private MenuItem equipment;
    @FXML private MenuItem home;
    @FXML private MenuItem logout;
    @FXML private MenuBar menuBar;


    ObservableList<String> sizeList = FXCollections.observableArrayList("none", "XS", "S", "M","L", "XL");
    Logger logger = Logger.getLogger(AddEquipManagerG.class.getName());
    @FXML
    private void initialize(){
        size.setItems(sizeList);
        size.getValue();
    }
    private boolean flag;
    public AddEquipManagerG(){
        flag = true;
    }

    @FXML
    void addNewProduct(ActionEvent event) throws IOException {
        ManagerApplicativo managerApplicativo = new ManagerApplicativo();
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        checkFields();
        EquipBean equipBean = new EquipBean(equipType.getText(), size.getValue(), Integer.parseInt(availability.getText()), Integer.parseInt(price.getText()));
        managerApplicativo.addProduct(equipBean,userBean);
        if (flag) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Insertion Successful");
            alert.showAndWait();
            MainApp app = new MainApp();
            app.changeScene("managerHome1.fxml");
        }
    }
    private void checkFields(){
        if (equipType.getText().isEmpty() || size.getValue().isEmpty()||price.getText().isEmpty()||availability.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill all fields");
            alert.showAndWait();
        }
    }
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

}

