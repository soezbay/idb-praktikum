/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.whs.idb.praktikum.entities;

//import jakarta.persistence.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;

/**
 *
 * @author Safak
 */
@Entity
public class Kategorie implements Serializable {

    @Id
    private String kurzel;
    private String name;
    
    public Kategorie() {
    }

    public String getKurzel() {
        return kurzel;
    }

    public void setKurzel(String kurzel) {
        this.kurzel = kurzel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
