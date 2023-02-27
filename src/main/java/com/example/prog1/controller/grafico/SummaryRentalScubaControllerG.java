package com.example.prog1.controller.grafico;

import com.example.prog1.bean.CominicationBean;
import com.example.prog1.bean.RentalBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.RentalEquipApplicativo;
import com.example.prog1.utilities.SwapPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SummaryRentalScubaControllerG implements Initializable {
    @FXML private MenuItem cart;
    @FXML private MenuItem home;
    @FXML private ListView<String> listView;
    @FXML private MenuItem logbook;
    @FXML private MenuItem logout;
    @FXML private MenuBar menuBar;
    @FXML private MenuItem rent;
    @FXML private Label divingLabel;
    @FXML private Label rentLabel;
    @FXML private Label totalLabel;

    private static final String LOGIN_SCREEN = "login1.fxml";
    private static final String SCUBA_HOME = "scubaHome1.fxml";
    private static final String CART_SCREEN = "cart1.fxml";
    private static final String SUMMARY_RENT_SCUBA = "summaryRentalScuba.fxml";

    @FXML
    void onMenuItemSelected(ActionEvent event) throws IOException {
        MenuItem sourceItem = (MenuItem) event.getSource();
        if (sourceItem == home){
            SwapPage.getInstance().gotoPage(SCUBA_HOME);
        } else if (sourceItem == logout){
            SwapPage.getInstance().gotoPage(LOGIN_SCREEN);
        } else if (sourceItem == cart){
            SwapPage.getInstance().gotoPage(CART_SCREEN);
        } else if (sourceItem == rent) {
            SwapPage.getInstance().gotoPage(SUMMARY_RENT_SCUBA);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        CominicationBean cominicationBean = InternalControllerGrafico.getInternalControllerInstance().getBeanString();
        String str = cominicationBean.getStr();
        System.out.println(str);
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        List<RentalBean> rentalBeans = rentalEquipApplicativo.summaryRental(userBean.getUserEmail());
        for (RentalBean r : rentalBeans){
            String type = r.getEquipType();
            listView.getItems().add(type+"   "+"\n\n");
            rentLabel.setText(String.valueOf(r.getIdRental()));
            divingLabel.setText(r.getDiv());
            totalLabel.setText(String.valueOf(r.getTotal()));
        }
    }


}

