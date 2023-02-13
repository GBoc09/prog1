package com.example.prog1.controller.grafico;

import com.example.prog1.MainApp;
import com.example.prog1.bean.CartBean;
import com.example.prog1.bean.EquipBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.RentalEquipApplicativo;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.prog1.controller.grafico.CartRowControllerG.quantity;

public class CartControllerGrafico implements Initializable {

    @FXML
    private Button addItems;
    @FXML
    private MenuItem cart;
    @FXML
    private ListView<String> cartView;
    @FXML
    private Button completeRental;
    @FXML
    private Button deleteItems;
    @FXML
    private MenuItem home;
    @FXML
    private MenuItem logbook;
    @FXML
    private MenuItem logout;
    @FXML
    private MenuBar menuBar;

    @FXML
    void onButtonClicked(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        if (source == addItems) {
            MainApp app = new MainApp();
            app.changeScene("rentEquip1.fxml");
        }
    }
    private RentalEquipApplicativo rentalEquipApplicativo;
    public CartControllerGrafico() {
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
//        rentalEquipApplicativo = new RentalEquipApplicativo(userBean);
    }
    @FXML
    void onMenuItemSelected(ActionEvent event) throws IOException {
        MenuItem sourceItem = (MenuItem) event.getSource();
        if (sourceItem == home) {
            MainApp app = new MainApp();
            app.changeScene("scubaHome1.fxml");
        } else if (sourceItem == logout) {
            MainApp app = new MainApp();
            app.changeScene("login1.fxml");
        } else if (sourceItem == cart) {
            MainApp app = new MainApp();
            app.changeScene("cart1.fxml");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        List<EquipBean> equipmentBeanList = rentalEquipApplicativo.getEquipForCart(val);
        Integer q = quantity;
        for (EquipBean d : equipmentBeanList) {
            String type = d.getType();
            String size = d.getSize();
            Double price = d.getPrice();
            cartView.getItems().add(type+ "   "+size+"   "+price+"   "+q+"\n\n");
        }
        //viewCart();
    }
    private static Integer val;
    public int memoryIndex (Integer selectionIndex){
        val = selectionIndex+1;
        return val;
    }
//    private void viewCart(){
//        CartBean cartBean = rentalEquipApplicativo.showCart();
//    }
}


