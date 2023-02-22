package com.example.prog1.model;
/**
 * SERIALIZABLE salvataggio dello stato interno di un oggetto -- serve per garantire la persistenza degli oggetti creati a runtime
 * la serializzazione prevede che venga salvata l'istanza considerata, ma anche tutti i riferimenti in essa contenuti
 * INTERFACCIA SERIALIZABLE: non definisce nessuna operazione, non richiede la definizione di nessun metodo della classe
 * che realizza */
public class Cart{
    private String equipType;
    private String equipSize;
    private Integer equipPrice;
    private Integer equipQuantity;

    public String getEquipType() {
        return equipType;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }

    public String getEquipSize() {
        return equipSize;
    }

    public void setEquipSize(String equipSize) {
        this.equipSize = equipSize;
    }

    public Integer getEquipPrice() {
        return equipPrice;
    }

    public void setEquipPrice(Integer equipPrice) {
        this.equipPrice = equipPrice;
    }

    public Integer getEquipQuantity() {
        return equipQuantity;
    }

    public void setEquipQuantity(Integer equipQuantity) {
        this.equipQuantity = equipQuantity;
    }
}
