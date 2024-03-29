package com.example.prog1.controller.grafico;

import com.example.prog1.bean.CartBean;
import com.example.prog1.bean.CominicationBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.RentalEquipApplicativo;
import com.example.prog1.utilities.SwapPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class CartControllerGrafico implements Initializable {
    @FXML private Button addItems;
    @FXML private MenuItem cart;
    @FXML private ListView<String> cartView;
    @FXML private Button completeRental;
    @FXML private Button deleteItems;
    @FXML private MenuItem home;
    @FXML private MenuItem logbook;
    @FXML private MenuItem logout;
    @FXML private MenuItem rent;
    @FXML private MenuBar menuBar;

    private static final String RENT_EQUIP = "rentEquip1.fxml";
    private static final String SCUBA_HOME = "scubaHome1.fxml";
    private static final String LOGIN_SCREEN = "login1.fxml";
    private static final String CART_SCREEN = "cart1.fxml";
    private static final String SUMMARY_RENT_SCUBA = "summaryRentalScuba.fxml";
    @FXML
    void onButtonClicked(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        if (source == addItems) {
            SwapPage.getInstance().gotoPage(RENT_EQUIP);
        } else if (source == deleteItems) {
            deleteTotCart();
        } else if (source == completeRental){
            saveOrder();
            /** MANDARE EMAIL AL DIVING PER NOTIFICA ORDINE */
        }
    }
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        List<CartBean> cartBeanList = null;
        cartBeanList = rentalEquipApplicativo.showCart(userBean.getUserEmail());

        for (CartBean c : cartBeanList){
            String type = c.getType();
            String size = c.getSize();
            Integer quant = c.getQuant();
            Integer price = c.getPrice();
            cartView.getItems().add(type+"   "+size+"   "+quant+"   "+price);
        }
    }
    /** salvataggio dell'ordine nella tabella RENTAL
     * communication bean = passaggio nome diving */
    private void saveOrder() throws IOException {
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        CominicationBean cominicationBean = InternalControllerGrafico.getInternalControllerInstance().getBeanString();
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        List<CartBean> cartBeanList = rentalEquipApplicativo.showCart(userBean.getUserEmail());
        rentalEquipApplicativo.saveItem(cartBeanList, userBean, cominicationBean);
        SwapPage.getInstance().gotoPage(SUMMARY_RENT_SCUBA);
    }
    private void deleteTotCart() throws IOException {
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        rentalEquipApplicativo.deleteItem(userBean);
        SwapPage.getInstance().gotoPage(CART_SCREEN );
    }
}


