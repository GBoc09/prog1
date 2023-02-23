package com.example.prog1.controller.grafico;

import com.example.prog1.bean.CominicationBean;
import com.example.prog1.bean.EquipBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.ManagerApplicativo;
import com.example.prog1.utilities.SwapPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowEquipManagerG implements Initializable {
    @FXML private MenuItem courses;
    @FXML private MenuItem diving;
    @FXML private MenuItem equipment;
    @FXML private MenuItem home;
    @FXML private ListView<String> listView;
    @FXML private MenuItem logout;
    @FXML private MenuBar menuBar;
    @FXML private Button modify;
    Logger logger = Logger.getLogger(ShowEquipManagerG.class.getName());
    private static final String ADD_EQUIP_MAN = "addEquip1.fxml";
    private static final String MANAGER_HOME = "managerHome1.fxml";
    private static final String LOGIN_SCREEN = "login1.fxml";
    private static final String SELECT_EQUIP = "selectDivingMan.fxml";
    private static final String SHOW_DIV_MAN = "showDivingMan.fxml";
    @FXML
    void modify(ActionEvent event) {
        try{
            SwapPage.getInstance().gotoPage(ADD_EQUIP_MAN);
        }
        catch (Exception e){
            logger.log(Level.INFO, "Exception Error");
        }
    }
    @FXML
    void onMenuItemSelected(ActionEvent event) throws IOException {
        MenuItem sourceItem = (MenuItem) event.getSource();
        if (sourceItem == home){
            SwapPage.getInstance().gotoPage(MANAGER_HOME);
        } else if (sourceItem == logout){
            SwapPage.getInstance().gotoPage(LOGIN_SCREEN);
        } else if (sourceItem == diving){
            SwapPage.getInstance().gotoPage(SHOW_DIV_MAN);
        } else if (sourceItem == equipment){
            SwapPage.getInstance().gotoPage(SELECT_EQUIP);
        }
    }
    private ManagerApplicativo managerApplicativo;
    /** mostra l'equip disponibile in un diving a seconda del diving e del manager selezionati */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        managerApplicativo = new ManagerApplicativo();
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        CominicationBean cominicationBean = InternalControllerGrafico.getInternalControllerInstance().getBean();
        List<EquipBean> equipBeanList = managerApplicativo.getEquips(userBean, cominicationBean);
        for (EquipBean d: equipBeanList){
            String type = d.getType();
            String size = d.getSize();
            Integer avail = d.getAvail();
            Integer price = d.getPrice();
            listView.getItems().add(type+"   "+size+"   "+avail+"   "+price+"\n\n");
        }
    }
}

