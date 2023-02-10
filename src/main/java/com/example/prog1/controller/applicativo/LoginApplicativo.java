package com.example.prog1.controller.applicativo;

import com.example.prog1.bean.AccessInfoBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.DAO.UserDAO;
import com.example.prog1.exception.NotExistentUserException;

public class LoginApplicativo {
    public UserBean verifyUser(AccessInfoBean accessInfoBean) throws NotExistentUserException {
        UserDAO userDAO = new UserDAO();
        Integer type = userDAO.loadUserByCredentials(accessInfoBean.getUserEmail(), accessInfoBean.getUserPass());
        return new UserBean(accessInfoBean.getUserEmail(), type);

    }
}
