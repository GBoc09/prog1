package com.example.prog1.model;

public interface InterfaceRental {
    void requestBooking(User user);
    void cancelRequest(User user);
    void broken(Equipment equipment);
    void acceptBooking(User user);
    void returnEquip(User user, Equipment equipment);
    void restore(Equipment equipment);
    void enterState();
    void exitState();

    //RentalInfo getInfo();

    Diving getDiving();
    Scuba getScuba();

}
