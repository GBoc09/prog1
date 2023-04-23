package com.example.prog1.controller.grafico;

import com.example.prog1.bean.CominicationBean;
import com.example.prog1.bean.IntegerComunicationBean;
import com.example.prog1.bean.UserBean;

public class InternalControllerGrafico {
    private static InternalControllerGrafico internalControllerGrafico;
    private UserBean loggedUser;
    private CominicationBean bean;
    private IntegerComunicationBean intBean;
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

    public CominicationBean getBeanString() {return bean; }
    public void setBeanString(CominicationBean bean) {this.bean = bean;}

    public IntegerComunicationBean getIntBean(){return intBean;}
    public void setBeanInt(IntegerComunicationBean intBean){this.intBean = intBean;}

}
