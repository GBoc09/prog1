package com.example.prog1.controller.grafico;

import com.example.prog1.bean.CominicationBean;
import com.example.prog1.bean.EquipBean;
import com.example.prog1.bean.IntegerComunicationBean;
import com.example.prog1.controller.applicativo.RentalEquipApplicativo;
import com.example.prog1.exception.SqlException;
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
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    @FXML private MenuItem rent;
    public static final String CART_USER_SCREEN = "cart1.fxml";
    private static final String CART_ROW = "cartRow.fxml";
    private static final String SCUBA_HOME = "scubaHome1.fxml";
    private static final String LOGIN_SCREEN = "login1.fxml";
    private static final String SUMMARY_RENT_SCUBA = "summaryRentalScuba.fxml";

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
        } else if (sourceItem == rent) {
            SwapPage.getInstance().gotoPage(SUMMARY_RENT_SCUBA);
        }
    }
    private IntegerComunicationBean cominicationBean1;
    Logger logger = Logger.getLogger(RentEquipScubaControllerG.class.getName());
    /** settare la lista delle attrezzature disponibili in un diving scelto
     * name = divingName */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quantity.setDisable(true);
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        CominicationBean cominicationBean = InternalControllerGrafico.getInternalControllerInstance().getBeanString();
        String str = cominicationBean.getStr();
        List<EquipBean> equipBeanList = null;
        if (str != null){
            try {
                equipBeanList = rentalEquipApplicativo.getEquipsName(str);
            } catch (SqlException e) {
                logger.log(Level.INFO, e.getMessage());
            }
        } else {
            try {
                equipBeanList = rentalEquipApplicativo.getEquips();
            } catch (SqlException e) {
               logger.log(Level.INFO, e.getMessage());
            }
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
                    cominicationBean1 = new IntegerComunicationBean(selectionIndex);
                    InternalControllerGrafico.getInternalControllerInstance().setBeanInt(cominicationBean1);
                }
            });
            quantity.setDisable(false);
        }
    }
    /** IL PASSAGGIO DEI DATI NON PUO' AVVENIRE FRA DUE CONTROLLER GRAFICI DEVE AVVENIRE: GRAFICO -> BEAN -> APPLICATIVO
     * -> BEAN -> GRAFICO.
     */
}

