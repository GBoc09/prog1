package com.example.prog1.model;

import com.example.prog1.pattern.state.State;
import com.example.prog1.pattern.state.StatoNuovo;

public class Rental {
    private Integer idRent;
    private String equipType;
    private String scuba;
    private String diving;
    private Integer total;
    private Integer equipPrice;
    public Rental(){}
    public Rental (Integer id, String type, String diving, Integer tot){
        this.idRent =id;
        this.equipType = type;
        this.total = tot;
        this.diving = diving;
    }
    /** applicazione del pattern state all'interno dello stato dell'ordine di un cliente:
     * IL MANAGER DI UN DIVING PUò ACCETTARE O RIFIUTARE L'ORDINE FATTO, SE LO ACCETTA VIENE MANDATA UNA MAIL DI NOTIFICA
     * AL CLIENTE, ANALOGAMENTE SE LO RIFIUTA
     *
     * lo stato è un oggetto di tipo RENTAL */
    private State  statoRental;
    /**public Rental (){
        this.statoRental = new StatoNuovo();
    }
     da errore in dao cercare di capire come risolvere  */

    public State getStatoRental() {
        return statoRental;
    }

    public void setStatoRental(State statoRental) {
        this.statoRental = statoRental;
    }

    public Integer getIdRent() {
        return idRent;
    }

    public void setIdRent(Integer idRent) {
        this.idRent = idRent;
    }

    public String getEquipType() {
        return equipType;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }

    public String getScuba() {
        return scuba;
    }

    public void setScuba(String scuba) {
        this.scuba = scuba;
    }

    public String getDiving() {
        return diving;
    }

    public void setDiving(String diving) {
        this.diving = diving;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getEquipPrice() {
        return equipPrice;
    }

    public void setEquipPrice(Integer equipPrice) {
        this.equipPrice = equipPrice;
    }
}
