package com.example.prog1.controller.grafico;

import com.example.prog1.bean.CominicationBean;
import com.example.prog1.bean.EquipBean;
import com.example.prog1.controller.applicativo.RentalEquipApplicativo;
import com.example.prog1.utilities.SwapPage;
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
    private static final String CART_ROW = "cartRow.fxml";
    private static final String SCUBA_HOME = "scubaHome1.fxml";
    private static final String LOGIN_SCREEN = "login1.fxml";

    private Integer selectionIndex;
    @FXML
    void onButtonClicked(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        if(source == goToCart) {
            SwapPage.getInstance().gotoPage(CART_USER_SCREEN);
        }else {
            SwapPage.getInstance().gotoPage(CART_ROW);
        }
    }
    @FXML
    void onMenuItemSelected(ActionEvent event) throws IOException {
        MenuItem sourceItem = (MenuItem) event.getSource();
        if (sourceItem == home){
            SwapPage.getInstance().gotoPage(SCUBA_HOME);
        } else if (sourceItem == logout){
            SwapPage.getInstance().gotoPage(LOGIN_SCREEN);
        } else if (sourceItem == cart){
            SwapPage.getInstance().gotoPage(CART_USER_SCREEN);
        }
    }
    private CominicationBean cominicationBean1;
    /** settare la lista delle attrezzature disponibili in un diving scelto
     * name = divingName */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quantity.setDisable(true);
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        CominicationBean cominicationBean = InternalControllerGrafico.getInternalControllerInstance().getBean();
        String str = cominicationBean.getStr();
        List<EquipBean> equipBeanList = null;
        if (str != null){
            equipBeanList = rentalEquipApplicativo.getEquipsName(str);
        } else {
            equipBeanList = rentalEquipApplicativo.getEquips();
        }
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

