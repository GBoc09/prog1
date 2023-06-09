package com.example.prog1.controller.grafico;

import com.example.prog1.bean.CartBean;
import com.example.prog1.bean.IntegerComunicationBean;
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
    @FXML private MenuItem deleteRent;

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
        } else if (sourceItem == deleteRent){
            deleteRental();
        }
    }
    private void deleteRental() throws IOException {
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        rentalEquipApplicativo.deleteRent(userBean);
        SwapPage.getInstance().gotoPage(SUMMARY_RENT_SCUBA);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        IntegerComunicationBean integerBean = InternalControllerGrafico.getInternalControllerInstance().getIntBean();
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        List<RentalBean> rentalBeans = null;
        rentalBeans = rentalEquipApplicativo.summaryRental(userBean.getUserEmail(), integerBean.getIndex());
        for (RentalBean r : rentalBeans){
            rentLabel.setText(String.valueOf(r.getIdRental()));
            divingLabel.setText(r.getDiv());
            totalLabel.setText(String.valueOf(r.getTotal()));
       }
        List<CartBean> cartBeanList = null;
        cartBeanList = rentalEquipApplicativo.showCartMan();
        for (CartBean c : cartBeanList) {
            String type = c.getType();
            String size = c.getSize();
            Integer quant = c.getQuant();
            Integer price = c.getPrice()*quant;
            listView.getItems().add(type + "   " + size + "   " + quant + "   " + price);
        }
    }
}

