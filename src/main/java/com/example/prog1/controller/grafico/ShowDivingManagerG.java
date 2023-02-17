package com.example.prog1.controller.grafico;

import com.example.prog1.MainApp;
import com.example.prog1.bean.DivingBean;
import com.example.prog1.bean.EquipBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.ManagerApplicativo;
import com.example.prog1.model.Diving;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ShowDivingManagerG implements Initializable {

    @FXML private MenuItem courses;
    @FXML private MenuItem diving;
    @FXML private MenuItem equipment;
    @FXML private MenuItem home;
    @FXML private ListView<String> listView;
    @FXML private MenuItem logout;
    @FXML private MenuBar menuBar;
    @FXML void onMenuItemSelected(ActionEvent event) throws IOException {
        MenuItem sourceItem = (MenuItem) event.getSource();
        if (sourceItem == home){
            MainApp app = new MainApp();
            app.changeScene("managerHome1.fxml");
        } else if (sourceItem == logout){
            MainApp app = new MainApp();
            app.changeScene("login1.fxml");
        } else if (sourceItem == equipment){
//            MainApp app = new MainApp();
//            app.changeScene(/*pagina che mostra la lista dell'equip*/);
        }else if (sourceItem == diving){
            MainApp app = new MainApp();
            app.changeScene("showDivingMan.fxml");
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ManagerApplicativo  managerApplicativo = new ManagerApplicativo();
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        List<DivingBean> divingBeanList = ManagerApplicativo.getDivings(userBean);
        for (DivingBean d: divingBeanList){
            String name = d.getName();
            String loc = d.getLocation();
            String tel = d.getTelephone();
            listView.getItems().add(name+"   "+loc+"   "+tel+"\n\n");
        }
    }
}

