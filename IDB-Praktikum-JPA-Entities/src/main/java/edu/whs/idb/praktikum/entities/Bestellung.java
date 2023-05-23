/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.whs.idb.praktikum.entities;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Safak
 */
@Entity
public class Bestellung implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "bestellung_sequence", sequenceName = "BESTELLUNG_SEQ", allocationSize = 1)
    private long bestellNr;

    private Date bestellDatum;
    
    private double gesamtpreis;
    
    private long anzahlPositionen;

    @ManyToOne
    private Kunde besteller;

    @OneToMany(mappedBy = "bestellung", cascade = CascadeType.ALL)
    private List<Bestelldetails> bestelldetails = new ArrayList<>();

    public Bestellung() {
    }

    public List<Bestelldetails> getBestelldetails() {
        return bestelldetails;
    }

    public void setBestelldetails(List<Bestelldetails> bestelldetails) {
        this.bestelldetails = bestelldetails;
    }

    public Kunde getBesteller() {
        return besteller;
    }

    public void setBesteller(Kunde besteller) {
        this.besteller = besteller;
    }

    public long getBestellNr() {
        return bestellNr;
    }

    public void setBestellNr(long bestellNr) {
        this.bestellNr = bestellNr;
    }

    public Date getBestellDatum() {
        return bestellDatum;
    }

    public void setBestellDatum(Date bestellDatum) {
        this.bestellDatum = bestellDatum;
    }

    public double getGesamtPreis() {
        return gesamtpreis;
    }

    public void setGesamtPreis(double gesamtPreis) {
        
        this.gesamtpreis = gesamtPreis;
    }

    public long getAnzahlPositionen() {
        return anzahlPositionen;
    }

    public void setAnzahlPositionen(long anzahlPositionen) {
        
        this.anzahlPositionen = anzahlPositionen;
    }
    
    
}
