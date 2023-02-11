package com.example.prog1.controller.grafico;

import com.example.prog1.bean.EquipBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.RentalEquipApplicativo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RentEquipScubaControllerG implements Initializable {

    @FXML
    private Button addToCart;

    @FXML
    private Label avail;

    @FXML
    private MenuItem cart;

    @FXML
    private Label equipType;

    @FXML
    private Button goToCart;

    @FXML
    private Label goToLogin;

    @FXML
    private MenuItem home;

    @FXML
    private ListView<String> listView;

    @FXML
    private MenuItem logbook;

    @FXML
    private MenuItem logout;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Label price;

    @FXML
    private Label size;
    private final RentalEquipApplicativo rentalEquipApplicativo;
    public static final String CART_USER_SCREEN = "cart1.fxml";

    public RentEquipScubaControllerG (){
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        rentalEquipApplicativo = new RentalEquipApplicativo(userBean);
    }


    @FXML
    void goToLogin(MouseEvent event) {

    }

    @FXML
    void onButtonClicked(ActionEvent event) {

    }

    @FXML
    void onMenuItemSelected(ActionEvent event) {

    }
    /**
     * CAPIRE COME INSERIRE IL VALORE DEI PARAMETRI CHIESTI DALLE LABEL
     * PASSARE I PARAMETRI DALLA BEAN */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addToCart.setDisable(true);
        final String[] str1 = new String[1];

        List<EquipBean> equipmentBeanList = rentalEquipApplicativo.getEquips();
        for (EquipBean d : equipmentBeanList) {
            listView.getItems().add(d.getId()+d.getType()+d.getSize()+d.getAvail()+d.getPrice());
            listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
                equipType.setText((newValue == null) ? "" : newValue.);
            })
        }
    }
}

