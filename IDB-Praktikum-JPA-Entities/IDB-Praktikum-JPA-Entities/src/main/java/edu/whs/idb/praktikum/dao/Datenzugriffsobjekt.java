/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.whs.idb.praktikum.dao;

import jakarta.persistence.EntityManager;


/**
 * Datenzugriffsobjekt zur Implementierung der JPA-spezifischen Logik.
 * 
 * Die Entitaetsklassen sollen im package edu.whs.idb.praktikum.entities
 * definiert werden.
 * 
 * @author laarmann
 */
public class Datenzugriffsobjekt {
    
    /**
     * Hier koennen Sie den Entity-Manager hinterlegen, den das 
     * Datenzugriffsobjekt verwalten soll.
     */
    private EntityManager em;
    
    /**
     * Starten der Persistence-Unit mit dem uebergebenen Namen
     * 
     * @param persistenceUnit Der Name der Persistence-Unit, die gestartet 
     *                        werden soll.
     */
    public Datenzugriffsobjekt(String persistenceUnit) {
        /**
         * Entity-Manager auf Basis der uebergebenen Persistence-Unit 
         * instanziieren.
         * 
         * Z.B.:
         * em = Persistence.createEntityManagerFactory(persistenceUnit) ...
         */
        
        em = null; // null ersetzen!
    }
    
    /** ToDo: Public-Methoden fuer die Datenzugriffslogik auf Basis der 
     *        Jakarta-Persistence-API
     */
    
    
    
    
    
    /**
     * Entity-Manager schließen.
     */
    public void close() {
        // ToDo: Schließen des Entity-Managers
    }
}
