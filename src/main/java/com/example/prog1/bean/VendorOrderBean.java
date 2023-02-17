package com.example.prog1.bean;

import com.example.prog1.boundary.RentEquipEmail;
import com.example.prog1.exception.EmailSenderException;
import com.example.prog1.pattern.observer.Subject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VendorOrderBean extends Subject implements Bean {
    private final Integer rentID;
    private String orderOwner; // colui che fa l'ordine
    private String orderOwnerEmail; // mail di chi fa l'ordine
    private List<String> divingEmail; // persona da notificare

    public VendorOrderBean (File file, int rentID) throws EmailSenderException {
        super(file);
        this.rentID = rentID;
        this.divingEmail = new ArrayList<>();

        RentEquipEmail rentEquipEmail = new RentEquipEmail(this);
        this.attach(rentEquipEmail);
    }
    public int getRentID(){return rentID;}
    public void notifyRental(){super.notifyObservers();}
    public String getOrderOwner(){return orderOwner;}
    public void setOrderOwner(String orderOwner){this.orderOwner = orderOwner;}

    public String getOrderOwnerEmail() {
        return orderOwnerEmail;
    }

    public void setOrderOwnerEmail(String orderOwnerEmail){
        this.orderOwnerEmail = orderOwnerEmail;
    }
    public List<String> getSubscribedEmails(){
        return divingEmail;
    }
    public void addSubscribedEmail(String email){
        divingEmail.add(email);
    }
}