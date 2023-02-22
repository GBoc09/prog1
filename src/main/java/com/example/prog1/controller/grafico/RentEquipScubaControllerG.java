package com.example.prog1.controller.grafico;

import com.example.prog1.MainApp;
import com.example.prog1.bean.CominicationBean;
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
    @FXML private Button quantity;
    @FXML private MenuItem cart;
    @FXML private Label equipType;
    @FXML private Button goToCart;
    @FXML private MenuItem home;
    @FXML private ListView<String> listView;
    @FXML private MenuItem logbook;
    @FXML private MenuItem logout;
    @FXML private MenuBar menuBar;
    public static final String CART_USER_SCREEN = "cart1.fxml";

    private Integer selectionIndex;
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
    private CominicationBean cominicationBean1;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quantity.setDisable(true);
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        CominicationBean cominicationBean = InternalControllerGrafico.getInternalControllerInstance().getBean();
        String str = cominicationBean.getStr();
        List<EquipBean> equipBeanList = rentalEquipApplicativo.getEquipsName(str);
        for (EquipBean d : equipBeanList) {
            String type = d.getType();
            String size = d.getSize();
            Integer avail = d.getAvail();
            Integer price = d.getPrice();

            listView.getItems().add(type+ "   "+size+ "   "+avail+"   "+price+"\n\n");
            listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    equipType.setText(listView.getSelectionModel().getSelectedItem());
                    selectionIndex = listView.getSelectionModel().getSelectedIndex();
                    cominicationBean1 = new CominicationBean(selectionIndex);
                    InternalControllerGrafico.getInternalControllerInstance().setBean(cominicationBean1);
                }
            });
            quantity.setDisable(false);
        }
    }
    /** IL PASSAGGIO DEI DATI NON PUO' AVVENIRE FRA DUE CONTROLLER GRAFICI DEVE AVVENIRE: GRAFICO -> BEAN -> APPLICATIVO
     * -> BEAN -> GRAFICO.
     */
}

