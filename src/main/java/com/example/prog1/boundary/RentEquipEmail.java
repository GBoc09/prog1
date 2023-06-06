package com.example.prog1.boundary;

import com.example.prog1.bean.VendorOrderBean;
import com.example.prog1.pattern.observer.Observer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RentEquipEmail implements Observer{
    Logger logger = Logger.getLogger(RentEquipEmail.class.getName());
    private static final String VENDOR_NOTIFY_FILE_NAME = "vendorNotifyFile";
    private String error = "IOException Error";
    private VendorOrderBean vendorOrderBean;
    public RentEquipEmail (VendorOrderBean vendorOrderBean){
        this.vendorOrderBean = vendorOrderBean;
        this.vendorOrderBean.attach(this);
    }
    @Override
    public void update() {
        try(FileWriter fileWriter = new FileWriter(VENDOR_NOTIFY_FILE_NAME)){
            fileWriter.write(String.format("""
                    Dear %s, 
                    We are pleased to inform you that %s has placed an order with you. 
                    Visit the details section to learn more. 
                    
                    Best Wishes
                    DiversWorld's team
                    """, vendorOrderBean.getVendor(), vendorOrderBean.getOrderOwner()));
        } catch (IOException ioException){
            logger.log(Level.INFO, error);
        }
    }
}
