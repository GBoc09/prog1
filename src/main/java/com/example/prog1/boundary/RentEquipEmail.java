package com.example.prog1.boundary;

import com.example.prog1.bean.VendorOrderBean;

import java.io.FileWriter;
import java.io.IOException;

public class RentEquipEmail {
    private static final String VENDOR_NOTIFY_FILE_NAME = "vendorNotifyFile";

    public void notifyVendors(VendorOrderBean vendorOrderBean) {
        sendEmail(vendorOrderBean);
    }

    private void sendEmail(VendorOrderBean vendorOrderBean){
        try(FileWriter fileWriter = new FileWriter(VENDOR_NOTIFY_FILE_NAME)){
            fileWriter.write(String.format("""
                    Dear %s, 
                    We are pleased to inform you that %s has placed an order with you. 
                    Visit the details section to learn more. 
                    
                    Best Wishes
                    DiversWorld's team
                    """, vendorOrderBean.getVendor(), vendorOrderBean.getOrderOwner()));
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}
