package com.example.prog1.controller.grafico;

import com.example.prog1.bean.UserBean;

public class InternalControllerGrafico {
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
    /**
     * */
}
