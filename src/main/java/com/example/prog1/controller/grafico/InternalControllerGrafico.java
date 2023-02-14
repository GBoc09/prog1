package com.example.prog1.controller.grafico;

import com.example.prog1.bean.UserBean;

public class InternalControllerGrafico {
    public static final String SCUBA_HOME = "scubaHome1.fxml";
    public static final String FREE_HOME = "freeHome1.fxml";
    public static final String MANAGER_HOME = "managerHome1.fxml";
    public static final Integer NOT_LOGGED = -1;
    private static InternalControllerGrafico internalControllerGrafico;
    private UserBean loggedUser;

    public static InternalControllerGrafico getInternalControllerInstance () {
        if(internalControllerGrafico == null){
            internalControllerGrafico = new InternalControllerGrafico();
        }
        return internalControllerGrafico;
    }
    public UserBean getLoggedUser(){
        return loggedUser;
    }

    public void setLoggedUser(UserBean loggedUser) {
        this.loggedUser = loggedUser;
    }

}
