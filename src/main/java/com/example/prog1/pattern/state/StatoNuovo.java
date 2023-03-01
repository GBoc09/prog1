package com.example.prog1.pattern.state;

import com.example.prog1.model.Rental;

public class StatoNuovo implements State{
    /** gestisce le transizioni di stato da in corso */
    @Override
    public void gestioneStatoRental (Rental rental, String stato){
        if (stato.equals("in_corso")){
            rental.setStatoRental(new StatoInCorso());
        }

    }
}
