/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.whs.idb.praktikum.entities;

import java.io.Serializable;
import java.util.Objects;

public class BestelldetailsId implements Serializable {
    
    private long bestellung;
    private long artikel;

    public BestelldetailsId() {
    }

    public long getBestellung() {
        return bestellung;
    }

    public void setBestellung(long bestellung) {
        this.bestellung = bestellung;
    }

    public long getArtikel() {
        return artikel;
    }

    public void setArtikel(long artikel) {
        this.artikel = artikel;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BestelldetailsId that = (BestelldetailsId) o;

        if (!Objects.equals(bestellung, that.bestellung)) return false;
        return Objects.equals(artikel, that.artikel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bestellung, artikel);
    }
}