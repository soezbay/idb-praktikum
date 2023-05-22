/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.whs.idb.praktikum.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.io.Serializable;


/**
 *
 * @author Safak
 *
 */
//class BestelldetailsId implements Serializable {
//    
//    private Long bestellungID;
//    private Long artikelID;
//
//    public BestelldetailsId() {
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 5;
//        hash = 83 * hash + (int) (this.bestellungID ^ (this.bestellungID >>> 32));
//        hash = 83 * hash + (int) (this.artikelID ^ (this.artikelID >>> 32));
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final BestelldetailsId other = (BestelldetailsId) obj;
//        if (!Objects.equals(this.bestellungID, other.bestellungID)) {
//            return false;
//        }
//        return Objects.equals(this.artikelID, other.artikelID);
//    }
//
//    public long getBestellung() {
//        return bestellungID;
//    }
//
//    public void setBestellung(long bestellung) {
//        this.bestellungID = bestellung;
//    }
//
//    public long getArtikel() {
//        return artikelID;
//    }
//
//    public void setArtikel(long artikel) {
//        this.artikelID = artikel;
//    }
//
//}

@Entity
@IdClass(BestelldetailsId.class)
public class Bestelldetails implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "bestellung_id")
    private Bestellung bestellung;
    
    @Id
    @OneToOne
    @JoinColumn(name = "artikel_id")
    private Artikel artikel;
    
    private double preis;
    private int anzahl;

    public Bestelldetails() {
    }

    public Bestellung getBestellung() {
        return bestellung;
    }

    public void setBestellung(Bestellung bestellung) {
        this.bestellung = bestellung;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }
}

//@Entity
//public class Bestelldetails implements Serializable {
//
//    @EmbeddedId
//    private BestelldetailsId id;
//
//    @ManyToOne
//    @JoinColumn(name = "BESTELLUNG_ID", updatable = false, insertable = false)
//    private Bestellung bestellung;
//
//    @OneToOne
//    @JoinColumn(name = "ARTIKEL_ID", updatable = false, insertable = false)
//    private Artikel artikel;
//
//    private double preis;
//    private int anzahl;
//
//    public Bestelldetails() {
//    }
//
//    public BestelldetailsId getId() {
//        return id;
//    }
//
//    public void setId(BestelldetailsId id) {
//        this.id = id;
//    }
//
//    public Bestellung getBestellung() {
//        return bestellung;
//    }
//
//    public void setBestellung(Bestellung bestellung) {
//        this.bestellung = bestellung;
//    }
//
//    public Artikel getArtikel() {
//        return artikel;
//    }
//
//    public void setArtikel(Artikel artikel) {
//        this.artikel = artikel;
//    }
//
//    public double getPreis() {
//        return preis;
//    }
//
//    public void setPreis(double preis) {
//        this.preis = preis;
//    }
//
//    public int getAnzahl() {
//        return anzahl;
//    }
//
//    public void setAnzahl(int anzahl) {
//        this.anzahl = anzahl;
//    }
//
//}
