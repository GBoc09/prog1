package com.example.prog1.boundary;

import com.example.prog1.bean.DivingOrderBean;

import java.io.FileWriter;
import java.io.IOException;

public class RentEquipEmail {
    private static final String NOTIFY_DIVING_FILE = "notifyDivingFile";
    public void notifyDiving(DivingOrderBean divingOrderBean){
        sendEmail(divingOrderBean);
    }
    private void sendEmail (DivingOrderBean divingOrderBean){
        try(FileWriter fileWriter = new FileWriter(NOTIFY_DIVING_FILE)){
            fileWriter.write(String.format("""
                    Hi %d.
                    %s has rented something! 
                    Check your rental section. 
                    DiversWorld!""", divingOrderBean.getDiving(), divingOrderBean.getOrderOwner()));
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}
