package com.example.prog1.controller.applicativo;

import com.example.prog1.dao.FreeDAO;
import com.example.prog1.dao.ManagerDAO;
import com.example.prog1.dao.ScubaDAO;
import com.example.prog1.bean.UserBean;
import com.example.prog1.exception.DuplicatedUserException;
import com.example.prog1.exception.SqlException;
import com.example.prog1.model.User;
import com.example.prog1.pattern.factory.Factory;

public class RegistrationApplicativo {
    Factory factory;
    public RegistrationApplicativo(){
        factory = new Factory();
    }
    public void registration (UserBean userBean) throws DuplicatedUserException, SqlException {
        User scuba;
        User free;
        User manager;
        int type = userBean.getUserType();
        String name = userBean.getName();
        String last = userBean.getSurname();
        String lic = userBean.getLicense();
        String email = userBean.getUserEmail();
        String pw = userBean.getPass();

        if (type == 0){
            scuba = factory.createScuba(email,pw,name,last,lic);
            ScubaDAO scubaDAO = new ScubaDAO();
            scubaDAO.insertScuba(scuba);
        } else if (type == 1) {
            free = factory.createFree(email,pw,name,last,lic);
            FreeDAO freeDAO = new FreeDAO();
            freeDAO.insertFree(free);
        } else if (type == 2) {
            manager = factory.createManager(email,pw,name,last,lic);
            ManagerDAO managerDAO = new ManagerDAO();
            managerDAO.insertManager(manager);
        }
    }
}
