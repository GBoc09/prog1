package com.example.prog1.pattern.state;

import com.example.prog1.model.Rental;

public interface RentalState {
    /** definisce i metodi di gestione dello stato, le logiche di transizione vengono gestite in modo distribuito
     * dai singoli oggetti distato */
    public void gestioneStatoRental(Rental rental, String stato);
}
