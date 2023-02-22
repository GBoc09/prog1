package com.example.prog1.controller.grafico;

import com.example.prog1.MainApp;
import com.example.prog1.bean.CominicationBean;
import com.example.prog1.bean.DivingBean;
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

    @FXML
    void onMenuItemSelected(ActionEvent event) throws IOException {
        MenuItem sourceItem = (MenuItem) event.getSource();
        if (sourceItem == home){
            MainApp app = new MainApp();
            app.changeScene("managerHome1.fxml");
        } else if (sourceItem == logout){
            MainApp app = new MainApp();
            app.changeScene("login1.fxml");
        } else if (sourceItem == equipment){
            MainApp app = new MainApp();
            app.changeScene("selectDivingMan.fxml");
        }else if (sourceItem == diving){
            MainApp app = new MainApp();
            app.changeScene("showDivingMan.fxml");
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
                    String[] column = row.split("   ");
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
            MainApp app = new MainApp();
            app.changeScene("addEquip1.fxml");
        } else if (source == showCatalogue){
            MainApp app = new MainApp();
            app.changeScene("showEquipManager.fxml");
        }
    }
}

