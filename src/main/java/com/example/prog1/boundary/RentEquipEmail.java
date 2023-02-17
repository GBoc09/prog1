package com.example.prog1.boundary;

import com.example.prog1.bean.VendorOrderBean;
import com.example.prog1.exception.EmailSenderException;
import com.example.prog1.pattern.observer.Observer;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RentEquipEmail implements Observer {
    private static final String NOTIFY_DIVING_FILE = "emailText.txt";
    private VendorOrderBean vendorOrderBean;

    public RentEquipEmail (VendorOrderBean vendorOrderBean) throws EmailSenderException {
        checkFile();
        this.vendorOrderBean = vendorOrderBean;
    }

    @Override
    public void update() {
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(NOTIFY_DIVING_FILE)))) {

            checkFile();
            for (String email : vendorOrderBean.getSubscribedEmails()){
            printWriter.println("Rental placed by: "+vendorOrderBean.getOrderOwner());
            printWriter.println("Email sent to: "+email);
            }
        } catch (IOException e){
            Logger.getAnonymousLogger().log(Level.INFO,e.getMessage());
        } catch (EmailSenderException e) {

        }
    }
    private boolean checkFile() throws EmailSenderException {
        File f = new File(NOTIFY_DIVING_FILE);
        if(!f.exists()){ throw new EmailSenderException("File does not exist");
        }
        if (!f.canWrite()){
            throw new EmailSenderException("Can not write on file");
        }
        return true;
    }
}
