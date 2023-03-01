package com.example.prog1.utilities;

import javafx.scene.control.MenuItem;

import java.io.IOException;
public class MenuBarManegerManagement {
    private static final String MANAGER_HOME = "managerHome1.fxml";
    private static final String LOGIN_SCREEN = "login1.fxml";
    private static final String SELECT_EQUIP = "selectDivingMan.fxml";
    private static final String SHOW_DIV_MAN = "showDivingMan.fxml";
    private static final String ACCEPT_REJECT = "accettazioneRentalManager.fxml";
    private static MenuBarManegerManagement instance = null;

    public static MenuBarManegerManagement getMenuBarManagerInstance () {
        if(instance == null){
            instance = new MenuBarManegerManagement();
        }
        return instance;
    }
    public void homeMan(MenuItem item) throws IOException {
        SwapPage.getInstance().gotoPage(MANAGER_HOME);
    }
    public void logOut(MenuItem item) throws IOException {
        SwapPage.getInstance().gotoPage(LOGIN_SCREEN);
    }
    public void selectDivingMan(MenuItem item) throws IOException {
        SwapPage.getInstance().gotoPage(SELECT_EQUIP);
    }
    public void showDivingMan(MenuItem item) throws IOException {
        SwapPage.getInstance().gotoPage(SHOW_DIV_MAN);
    }
    public void acceptReject(MenuItem item) throws IOException {
        SwapPage.getInstance().gotoPage(ACCEPT_REJECT);
    }

}
