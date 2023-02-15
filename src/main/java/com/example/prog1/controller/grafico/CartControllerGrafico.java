package com.example.prog1.controller.grafico;

import com.example.prog1.MainApp;
import com.example.prog1.bean.CartBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.RentalEquipApplicativo;
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

public class CartControllerGrafico implements Initializable {

    @FXML private Button addItems;
    @FXML private MenuItem cart;
    @FXML private ListView<String> cartView;
    @FXML private Button completeRental;
    @FXML private Button deleteItems;
    @FXML private MenuItem home;
    @FXML private MenuItem logbook;
    @FXML private MenuItem logout;
    @FXML private MenuBar menuBar;
    @FXML
    void onButtonClicked(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        if (source == addItems) {
            MainApp app = new MainApp();
            app.changeScene("rentEquip1.fxml");
        } else if (source == deleteItems) {
            deleteCart();
        } else if (source == completeRental){
            /** MANDARE EMAIL AL DIVING PER NOTIFICA ORDINE
             * vedere possibile applicazione del pattern state */
        }
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
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        List<CartBean> cartBeanList = rentalEquipApplicativo.showCart(userBean.getUserEmail());
        for (CartBean c : cartBeanList){
            String type = c.getType();
            String size = c.getSize();
            Integer quant = c.getQuant();
            Integer price = c.getPrice();
            cartView.getItems().add(type+"   "+size+"   "+quant+"   "+price);
        }
    }
    private static Integer val;
    public static int memoryIndex (Integer selectionIndex){
        val = selectionIndex+1;
        return val;
    }
    private void deleteCart() throws IOException {
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        rentalEquipApplicativo.deleteItem(userBean);
        MainApp app = new MainApp();
        app.changeScene("cart1.fxml");

    }
}


