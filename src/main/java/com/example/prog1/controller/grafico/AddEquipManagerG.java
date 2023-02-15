package com.example.prog1.controller.grafico;

import com.example.prog1.MainApp;
import com.example.prog1.bean.EquipBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.ManagerEquipApplicativo;
import com.example.prog1.exception.DuplicatedEquipException;
import com.example.prog1.exception.InvalidInsertionEquipException;
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

    @FXML
    private TextField EquipType;

    @FXML
    private Button addProduct;

    @FXML
    private TextField availability;

    @FXML
    private Label errorLabel;

    @FXML
    private Label goHome;

    @FXML
    private TextField price;

    @FXML
    private ChoiceBox<String> size;
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
        ManagerEquipApplicativo managerEquipApplicativo = new ManagerEquipApplicativo();
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        String email  = userBean.getUserEmail();
        System.out.println("add new product controller grafico -- verifica che prenda utente: "+email);
        checkFields();
        EquipBean equipBean = new EquipBean(EquipType.getText(), size.getValue(), Integer.parseInt(availability.getText()), Integer.parseInt(price.getText()));
        managerEquipApplicativo.addProduct(equipBean,userBean);
        if (flag) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Insertion Successful");
            alert.showAndWait();
            MainApp app = new MainApp();
            app.changeScene("managerHome1.fxml");
        }
    }
    private void checkFields(){
        if (EquipType.getText().isEmpty() || size.getValue().isEmpty()||price.getText().isEmpty()||availability.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill all fields");
            alert.showAndWait();
        }
    }

    @FXML
    void goHome(MouseEvent event) {
        try{
            MainApp app = new MainApp();
            app.changeScene("managerHome1.fxml");
        }
        catch (Exception e){
            logger.log(Level.INFO, "Exception Error");
        }
    }

}

