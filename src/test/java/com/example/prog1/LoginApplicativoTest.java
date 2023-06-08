package com.example.prog1;

import com.example.prog1.bean.AccessInfoBean;
import com.example.prog1.controller.applicativo.LoginApplicativo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginApplicativoTest {
    @Test
    void verifyUserTest(){
        LoginApplicativo loginApplicativo = new LoginApplicativo();
        String userTest = "giuboc@gmail.com";
        String pwTest = "root1";
        try{
            AccessInfoBean accessInfoBean = new AccessInfoBean(userTest, pwTest);
        } catch (Exception e){
            Assertions.fail(e.getMessage());
        }
    }
}
