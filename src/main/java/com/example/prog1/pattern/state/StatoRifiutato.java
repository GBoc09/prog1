package com.example.prog1.pattern.state;

import com.example.prog1.model.Rental;

public class StatoRifiutato implements RentalState{
    @Override
    public void gestioneStatoRental(Rental rental, String stato) {
        /** MANDARE MAIL AL CLIENTE */
    }
}
