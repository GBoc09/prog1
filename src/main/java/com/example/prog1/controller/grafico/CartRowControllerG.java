package com.example.prog1.controller.grafico;

import com.example.prog1.MainApp;
import com.example.prog1.bean.CartBean;
import com.example.prog1.bean.CominicationBean;
import com.example.prog1.bean.EquipBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.controller.applicativo.RentalEquipApplicativo;
import com.example.prog1.utilities.SwapPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CartRowControllerG { // fxml per inserimento quantità
    @FXML private Button addToCart;
    @FXML private Button addItems;
    @FXML private Button goToCart;
    @FXML private MenuItem cart;
    @FXML private Label equipType;
    @FXML private MenuItem home;
    @FXML private TextField insertQuantity;
    @FXML private MenuItem logbook;
    @FXML private MenuItem logout;
    @FXML private MenuBar menuBar;
    @FXML private Label priceLabel;
    @FXML private Label sizeLabel;
    @FXML private Label back;
    Logger logger = Logger.getLogger(CartRowControllerG.class.getName());

    private static final String RENT_EQUIP = "rentEquip1.fxml";
    private static final String CART_SCREEN = "cart1.fxml";
    private static final String SCUBA_HOME  = "scubaHome1.fxml";
    private static final String LOGIN_SCREEN = "login1.fxml";
    @FXML
    void back(MouseEvent event) {
        try{
//            MainApp app = new MainApp();
//            app.changeScene("rentEquip1.fxml");
            SwapPage.getInstance().gotoPage(RENT_EQUIP);
        }
        catch (Exception e){
            logger.log(Level.INFO, "Exception Error");
        }
    }
    @FXML
    void onButtonClicked(ActionEvent event) throws IOException {
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        Node source = (Node) event.getSource();
        checkQuantity();
        if (source == addToCart){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Insertion successful.");
            alert.showAndWait();
        } else if (source == addItems) {
//            MainApp app = new MainApp();
//            app.changeScene("rentEquip1.fxml");
            SwapPage.getInstance().gotoPage(RENT_EQUIP);
        } else if (source == goToCart) {
            loadCart(userBean);
//            MainApp app = new MainApp();
//            app.changeScene("cart1.fxml");
            SwapPage.getInstance().gotoPage(CART_SCREEN);
        }
    }
    @FXML
    void onMenuItemSelected(ActionEvent event) throws IOException {
        MenuItem sourceItem = (MenuItem) event.getSource();
        if (sourceItem == home){
//            MainApp app = new MainApp();
//            app.changeScene("scubaHome1.fxml");
            SwapPage.getInstance().gotoPage(SCUBA_HOME);
        } else if (sourceItem == logout){
//            MainApp app = new MainApp();
//            app.changeScene("login1.fxml");
            SwapPage.getInstance().gotoPage(LOGIN_SCREEN);
        } else if (sourceItem == cart){
//            MainApp app = new MainApp();
//            app.changeScene("cart1.fxml");
            SwapPage.getInstance().gotoPage(CART_SCREEN);
        }
    }
    /**
     * ORDINAMENTO ALFABETICO NELLA LISTA
     * 1. finns
     * 2. jacket
     * 3. mask
     * 4. regulator
     * 5. suit
     *
     * posso valutare l'indice per andare a selezionare dal db le cose che mi servono*/
    private static Integer val;
    public static int memoryIndex (){
        CominicationBean cominicationBean = InternalControllerGrafico.getInternalControllerInstance().getBean();
        val = cominicationBean.getIndex()+1;
        return val;
    }
    private String email;
    @FXML
    public void initialize (){
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        email = userBean.getUserEmail();
        EquipBean equipBean;
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        memoryIndex();
        equipBean = rentalEquipApplicativo.infoEquipGeneral(val);
        equipType.setText(equipBean.getType());
        sizeLabel.setText(equipBean.getSize());
        priceLabel.setText(String.valueOf(equipBean.getPrice()));
    }
    private Integer quantity;
    private Integer checkQuantity()  {
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        email = userBean.getUserEmail();
        if(insertQuantity.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please insert a valid quantity");
            alert.showAndWait();
        }else{
            Integer disp;
            RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
            disp = rentalEquipApplicativo.infoDispEquip(val);
            quantity = Integer.valueOf(insertQuantity.getText());
            if(disp < quantity){
                Alert alert = new Alert(Alert.AlertType.WARNING, "Quantity is bigger than our availabilities, please insert a valid quantitative.");
                alert.showAndWait();
                insertQuantity.setText("");
            } else if (disp >= quantity) {
                rentalEquipApplicativo.infoEquipCart(val, quantity, email);
            }
        }
        return quantity;
    }
    public List<CartBean> loadCart(UserBean userBean){
        email = userBean.getUserEmail();
        RentalEquipApplicativo rentalEquipApplicativo = new RentalEquipApplicativo();
        List<CartBean> cartBeans = rentalEquipApplicativo.showCart(email);
        return cartBeans;
    }
}
