package com.example.prog1.controller.grafico;

import com.example.prog1.bean.CominicationBean;
import com.example.prog1.bean.DivingBean;
import com.example.prog1.controller.applicativo.RentalEquipApplicativo;
import com.example.prog1.utilities.SwapPage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class SelectedDivingScubaG implements Initializable {
    @FXML private MenuItem cart;
    @FXML private MenuItem home;
    @FXML private ListView<String> listView;
    @FXML private MenuItem logbook;
    @FXML private MenuItem logout;
    @FXML private MenuBar menuBar;
    @FXML private Button selectDiving;
    @FXML private Label label;
    @FXML private MenuItem rent;

    private static final String SCUBA_HOME = "scubaHome1.fxml";
    private static final String LOGIN_SCREEN = "login1.fxml";
    private static final String CART_SCREEN = "cart1.fxml";
    private static final String RENT_EQUIP = "rentEquip1.fxml";
    private static final String SUMMARY_RENT_SCUBA = "summaryRentalScuba.fxml";
    private String str;
    private CominicationBean cominicationBean;
    @FXML
    void onMenuItemSelected(ActionEvent event) throws IOException {
        MenuItem sourceItem = (MenuItem) event.getSource();
        if (sourceItem == home) {
            SwapPage.getInstance().gotoPage(SCUBA_HOME);
        } else if (sourceItem == logout) {
            SwapPage.getInstance().gotoPage(LOGIN_SCREEN);
        } else if (sourceItem == cart) {
            SwapPage.getInstance().gotoPage(CART_SCREEN);
        } else if (sourceItem == rent) {
            SwapPage.getInstance().gotoPage(SUMMARY_RENT_SCUBA);
        }
    }
    @FXML
    void selectDiving(ActionEvent event) throws IOException {
        SwapPage.getInstance().gotoPage(RENT_EQUIP);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectDiving.setDisable(true);
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        List<DivingBean> divingBeanList = null;
        divingBeanList = rentalEquipApplicativo.getDivingList();

        for (DivingBean d : divingBeanList) {
            String name = d.getName();
            String loc = d.getLocation();
            String tel = d.getTelephone();
            listView.getItems().add(name + "   " + loc + "   " + tel + "\n\n");
            listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    String row = listView.getSelectionModel().getSelectedItem();
                    String[] column = row.split("\s\s\s");
                    label.setText(column[0]);
                    str = label.getText();
                    cominicationBean = new CominicationBean(str);
                    InternalControllerGrafico.getInternalControllerInstance().setBeanString(cominicationBean);
                }
            });
            selectDiving.setDisable(false);
        }
    }
}

