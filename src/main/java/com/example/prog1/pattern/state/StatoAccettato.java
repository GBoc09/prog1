package com.example.prog1.pattern.state;

import com.example.prog1.model.Rental;

public class StatoAccettato implements RentalState{
    @Override
    public void gestioneStatoRental(Rental rental, String stato) {
        /** MANDARE EMAIL DI CONFERMA ALL'UTENTE PER L'ACCETTAZIONE DELL'ORDINE */
    }
}
