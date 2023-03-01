package com.example.prog1.pattern.state;

import com.example.prog1.model.Rental;

public class StatoInCorso implements State{

    @Override
    public void gestioneStatoRental(Rental rental, String stato) {
        if (stato.equals("accept")){
            rental.setStatoRental(new StatoAccettato());
        } else if (stato.equals("reject")) {
            rental.setStatoRental(new StatoRifiutato());
        }
    }
}
