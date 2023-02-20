package com.example.prog1.controller.grafico;

import com.example.prog1.bean.CominicationBean;
import com.example.prog1.bean.UserBean;

public class InternalControllerGrafico {
    private static InternalControllerGrafico internalControllerGrafico;
    private UserBean loggedUser;
    private CominicationBean bean;

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
    public CominicationBean getBean() {return bean; }
    public void setBean(CominicationBean bean) {this.bean = bean;}
}
