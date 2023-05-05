package com.example.prog1.boundary;

import com.example.prog1.bean.BuyerOrderBean;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RentEquipDecisionEmail {
    Logger logger = Logger.getLogger(RentEquipDecisionEmail.class.getName());
    private static final String BUYER_NOTIFY_FILE_NAME = "buyerNotifyFile";
    private String error = "IOException Error";

    public void notifyBuyersOK(BuyerOrderBean buyerOrderBean) {
        confSendEmail(buyerOrderBean);
    }
    public void notifyBuyersKO(BuyerOrderBean buyerOrderBean) {
        rejSendEmail(buyerOrderBean);
    }

    private void confSendEmail(BuyerOrderBean buyerOrderBean){
        try(FileWriter fileWriter = new FileWriter(BUYER_NOTIFY_FILE_NAME)){
            fileWriter.write(String.format("""
                    Dear %s, 
                    We are pleased to inform you that %s has accepted your order. 
                    
                    Best Wishes
                    DiversWorld's team
                    """, buyerOrderBean.getBuyer(), buyerOrderBean.getDivingOwner()));
        } catch (IOException ioException){
            logger.log(Level.INFO, error);
        }
    }
    private void rejSendEmail(BuyerOrderBean buyerOrderBean) {
        try (FileWriter fileWriter = new FileWriter(BUYER_NOTIFY_FILE_NAME)) {
            fileWriter.write(String.format("""
                    Dear %s, 
                    We are sorry to inform you that your order has been rejected.
                    If you want more information, please contact %s directly.  
                                        
                    Best Wishes
                    DiversWorld's team
                    """, buyerOrderBean.getBuyer(), buyerOrderBean.getDivingOwner()));
        } catch (IOException ioException) {
            logger.log(Level.INFO, error);
        }
    }
}
