package com.example.prog1.controller.grafico;

import com.example.prog1.bean.CominicationBean;
import com.example.prog1.bean.RentalBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.RentalEquipApplicativo;
import com.example.prog1.exception.SqlException;
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
import java.util.logging.Level;
import java.util.logging.Logger;

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
    void onMenuItemSelected(ActionEvent event) throws IOException, SqlException {
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
    private void deleteRental() throws IOException, SqlException {
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        rentalEquipApplicativo.deleteRent(userBean);
        SwapPage.getInstance().gotoPage(SUMMARY_RENT_SCUBA);
    }
    Logger logger = Logger.getLogger(SummaryRentalScubaControllerG.class.getName());
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        List<RentalBean> rentalBeans = null;
        try {
            rentalBeans = rentalEquipApplicativo.summaryRental(userBean.getUserEmail());
        } catch (SqlException e) {
            logger.log(Level.INFO, e.getMessage());
        }
        for (RentalBean r : rentalBeans){
            String type = r.getEquipType();
            listView.getItems().add(type+"   "+"\n\n");
            rentLabel.setText(String.valueOf(r.getIdRental()));
            divingLabel.setText(r.getDiv());
            totalLabel.setText(String.valueOf(r.getTotal()));
        }
    }


}

