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
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SelectedDivingScubaG implements Initializable {
    @FXML
    private MenuItem cart;
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
    private Button selectDiving;
    @FXML
    private Label label;

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

    @FXML
    void selectDiving(ActionEvent event) throws IOException {
        MainApp app = new MainApp();
        app.changeScene("rentEquip1.fxml");
    }

    public SelectedDivingScubaG() {
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
    }
    private static String str;
    public static CominicationBean cominicationBean;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectDiving.setDisable(true);
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        List<DivingBean> divingBeanList = rentalEquipApplicativo.getDivingList();
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
            selectDiving.setDisable(false);
        }
    }
}

