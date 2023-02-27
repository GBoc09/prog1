package com.example.prog1.controller.grafico;

import com.example.prog1.bean.CominicationBean;
import com.example.prog1.bean.DivingBean;
import com.example.prog1.bean.UserBean;
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

public class SelectDivingManG implements Initializable {
    @FXML private MenuItem courses;
    @FXML private MenuItem diving;
    @FXML private MenuItem equipment;
    @FXML private MenuItem home;
    @FXML private Label label;
    @FXML private MenuItem logout;
    @FXML private MenuBar menuBar;
    @FXML private ListView<String> listView;
    @FXML private Button addEquipButton;
    @FXML private Button showCatalogue;

    private static final String MANAGER_HOME = "managerHome1.fxml";
    private static final String LOGIN_SCREEN = "login1.fxml";
    private static final String SHOW_DIV_MAN = "showDivingMan.fxml";
    private static final String SELECT_DIV_MAN = "selectDivingMan.fxml";
    private static final String ADD_EQUIP_MAN = "addEquip1.fxml";
    private static final String SHOW_EQUIP_MAN = "showEquipManager.fxml";

    @FXML
    void onMenuItemSelected(ActionEvent event) throws IOException {
        MenuItem sourceItem = (MenuItem) event.getSource();
        if (sourceItem == home){
            SwapPage.getInstance().gotoPage(MANAGER_HOME);
        } else if (sourceItem == logout){
            SwapPage.getInstance().gotoPage(LOGIN_SCREEN);
        } else if (sourceItem == equipment){
            SwapPage.getInstance().gotoPage(SELECT_DIV_MAN);
        }else if (sourceItem == diving){
            SwapPage.getInstance().gotoPage(SHOW_DIV_MAN);
        }
    }
    private String str;
    private CominicationBean cominicationBean;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        List<DivingBean> divingBeanList = rentalEquipApplicativo.getDivingListMan(userBean);
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
                    InternalControllerGrafico.getInternalControllerInstance().setBean(cominicationBean);
                }
            });
        }
    }
    @FXML
    void onButtonClicked(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        if (source == addEquipButton){
            SwapPage.getInstance().gotoPage(ADD_EQUIP_MAN);
        } else if (source == showCatalogue){
            SwapPage.getInstance().gotoPage(SHOW_EQUIP_MAN);
        }
    }
}

