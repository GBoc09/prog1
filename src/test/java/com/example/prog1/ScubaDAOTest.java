package com.example.prog1;

import com.example.prog1.dao.ScubaDAO;
import com.example.prog1.exception.DuplicatedUserException;
import com.example.prog1.model.User;
import com.example.prog1.pattern.factory.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScubaDAOTest {
    Factory factory;
    public ScubaDAOTest(){
        factory = new Factory();
    }
    @Test
    void insertScubaTest(){
        String name = "giu";
        String last = "boc";
        String lic = "123456789";
        String email = "giuboc@gmail.com";
        String pw = "root1";
        User scuba = factory.createScuba(email, pw,name,last,lic);
        ScubaDAO scubaDAO = new ScubaDAO();
        try{
            scubaDAO.insertScuba(scuba);
            Assertions.fail(); // user already registered
        }catch (DuplicatedUserException e){
            e.getMessage();
        }
    }
}
