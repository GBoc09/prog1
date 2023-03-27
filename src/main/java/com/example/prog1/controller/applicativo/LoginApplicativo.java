package com.example.prog1.controller.applicativo;

import com.example.prog1.bean.AccessInfoBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.dao.UserDAO;
import com.example.prog1.exception.NotExistentUserException;
import com.example.prog1.exception.SqlException;

public class LoginApplicativo {
    public UserBean verifyUser(AccessInfoBean accessInfoBean) throws NotExistentUserException, SqlException {
        UserDAO userDAO = new UserDAO();
        Integer type = userDAO.loadUserByCredentials(accessInfoBean.getUserEmail(), accessInfoBean.getUserPass());
        return new UserBean(accessInfoBean.getUserEmail(), type);
    }
}
