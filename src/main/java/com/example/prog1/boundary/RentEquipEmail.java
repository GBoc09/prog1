package com.example.prog1.boundary;

import com.example.prog1.bean.VendorOrderBean;

import java.io.FileWriter;
import java.io.IOException;

public class RentEquipEmail {
    private static final String NOTIFY_DIVING_FILE = "notifyDivingFile";
    public void notifyDiving(VendorOrderBean vendorOrderBean){
        sendEmail(vendorOrderBean);
    }
    private void sendEmail (VendorOrderBean vendorOrderBean){
        try(FileWriter fileWriter = new FileWriter(NOTIFY_DIVING_FILE)){
            fileWriter.write(String.format("""
                    Hi %s.
                    %s has rented something! 
                    Check your rental section. 
                    DiversWorld!""", vendorOrderBean.getVendor(), vendorOrderBean.getOrderOwner()));
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}
