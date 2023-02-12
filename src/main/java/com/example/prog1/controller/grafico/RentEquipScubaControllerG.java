package com.example.prog1.controller.grafico;

import com.example.prog1.MainApp;
import com.example.prog1.bean.EquipBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.RentalEquipApplicativo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RentEquipScubaControllerG implements Initializable {

    @FXML
    private Button quantity;

    @FXML
    private MenuItem cart;

    @FXML
    private Label equipType;

    @FXML
    private Button goToCart;

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

    private final RentalEquipApplicativo rentalEquipApplicativo;
    public static final String CART_USER_SCREEN = "cart1.fxml";
    public static Integer selectionIndex;
    public RentEquipScubaControllerG (){
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        rentalEquipApplicativo = new RentalEquipApplicativo(userBean);
    }

    @FXML
    void onButtonClicked(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        if(source == goToCart) {
            MainApp app = new MainApp();
            app.changeScene(CART_USER_SCREEN);
        }else {
            MainApp app = new MainApp();
            app.changeScene("cartRow.fxml");
        }
    }

    @FXML
    void onMenuItemSelected(ActionEvent event) throws IOException {
        MenuItem sourceItem = (MenuItem) event.getSource();
//        BorderPane scubaBorderPane = (BorderPane) menuBar.getScene().getRoot();
//        InternalControllerGrafico.getInternalControllerInstance().onNextScreen(scubaBorderPane);
        if (sourceItem == home){
            MainApp app = new MainApp();
            app.changeScene("scubaHome1.fxml");
        } else if (sourceItem == logout){
            MainApp app = new MainApp();
            app.changeScene("login1.fxml");
        } else if (sourceItem == cart){
            MainApp app = new MainApp();
            app.changeScene(CART_USER_SCREEN);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quantity.setDisable(true);
        List<EquipBean> equipmentBeanList = rentalEquipApplicativo.getEquips();
        for (EquipBean d : equipmentBeanList) {
            Integer id = d.getId();
            String type = d.getType();
            String size = d.getSize();
            Integer avail = d.getAvail();
            Double price = d.getPrice();

            listView.getItems().add(id+"   "+type+ "   "+size+ "   "+avail+"   "+price+"\n\n");
            listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                      equipType.setText(listView.getSelectionModel().getSelectedItem());
                    selectionIndex = listView.getSelectionModel().getSelectedIndex();
                    CartControllerGrafico cartControllerGrafico = new CartControllerGrafico();
                    CartRowControllerG cartRowControllerG = new CartRowControllerG();
                    cartControllerGrafico.memoryIndex(selectionIndex);
                    cartRowControllerG.memoryIndex(selectionIndex);
                }
            });
            quantity.setDisable(false);
        }
    }
}

