package com.example.prog1.controller.grafico;

import com.example.prog1.bean.CominicationBean;
import com.example.prog1.bean.EquipBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.RentalEquipApplicativo;
import com.example.prog1.utilities.SwapPage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
public class AddEquipManagerG {

    @FXML private TextField equipType;
    @FXML private Button addProduct;
    @FXML private TextField availability;
    @FXML private Label errorLabel;
    @FXML private TextField price;
    @FXML private ChoiceBox<String> size;
    @FXML private MenuItem courses;
    @FXML private MenuItem diving;
    @FXML private MenuItem equipment;
    @FXML private MenuItem home;
    @FXML private MenuItem logout;
    @FXML private MenuBar menuBar;
    @FXML private MenuItem rental;

    private static final String MANAGER_HOME = "managerHome1.fxml";
    private static final String LOGIN_SCREEN = "login1.fxml";
    private static final String SELECT_EQUIP = "selectDivingMan.fxml";
    private static final String SHOW_DIV_MAN = "showDivingMan.fxml";
    private static final String ACCEPT_REJECT = "accettazioneRentalManager.fxml";
    private boolean flag;
    ObservableList<String> sizeList = FXCollections.observableArrayList("none", "XS", "S", "M","L", "XL");
    @FXML
    private void initialize(){
        size.setItems(sizeList);
        size.getValue();
    }
    public AddEquipManagerG(){
        flag = true;
    }
    @FXML
    void addNewProduct(ActionEvent event) throws IOException {
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        CominicationBean cominicationBean = InternalControllerGrafico.getInternalControllerInstance().getBeanString();
        checkFields();
        EquipBean equipBean = new EquipBean(equipType.getText(), size.getValue(), Integer.parseInt(availability.getText()), Integer.parseInt(price.getText()));
        rentalEquipApplicativo.addProduct(equipBean,cominicationBean, userBean);
        if (flag) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Insertion Successful");
            alert.showAndWait();
            SwapPage.getInstance().gotoPage(MANAGER_HOME);
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
            SwapPage.getInstance().gotoPage(MANAGER_HOME);
        } else if (sourceItem == logout){
            SwapPage.getInstance().gotoPage(LOGIN_SCREEN);
        } else if (sourceItem == equipment){
            SwapPage.getInstance().gotoPage(SELECT_EQUIP);
        }else if (sourceItem == diving){
            SwapPage.getInstance().gotoPage(SHOW_DIV_MAN);
        } else if (sourceItem == rental){
            SwapPage.getInstance().gotoPage(ACCEPT_REJECT);
        }
    }
}

