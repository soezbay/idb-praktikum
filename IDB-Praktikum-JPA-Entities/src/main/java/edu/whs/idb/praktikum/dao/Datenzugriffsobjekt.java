/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.whs.idb.praktikum.dao;

//import edu.whs.idb.praktikum.dao.exception.InputException;
import edu.whs.idb.praktikum.dao.exception.InputException;
import edu.whs.idb.praktikum.entities.Artikel;
import edu.whs.idb.praktikum.entities.Bestelldetails;
import edu.whs.idb.praktikum.entities.Bestellung;
import edu.whs.idb.praktikum.entities.Kategorie;
import edu.whs.idb.praktikum.entities.Kunde;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

//import edu.whs.idb.praktikum.entities.Artikel;
//import edu.whs.idb.praktikum.entities.Kategorie;
//import edu.whs.idb.praktikum.entities.Kunde;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//import jakarta.persistence.Query;
//import jakarta.persistence.TypedQuery;
//import java.util.List;
//import java.util.StringTokenizer;
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
    private EntityManagerFactory emf;

    /**
     * Starten der uebrgebenen Persistence-Unit
     *
     * @param persistenceUnit Die Persistence-Unit, die gestartet werden soll
     */
    public Datenzugriffsobjekt(String persistenceUnitName) {
        /**
         * Entity-Manager auf Basis der uebergebenen Persistence-Unit
         * instanziieren.
         */
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        em = emf.createEntityManager();
    }
    
    public <T, ID> T findeEntity(Class<T> klasse, ID id) {
        
        T entityObj = em.find(klasse, id);
        
        return entityObj;
    }

    public void saveKategorie(Kategorie kategorie) {

        em.getTransaction().begin();
        em.persist(kategorie);
        System.out.println("___Kategorie: [" + kategorie.getName() + "] wurde persistiert___");
        em.getTransaction().commit();

    }

    public void setzeKategorie(Artikel artikel, String kategorieKurzel) {

        em.getTransaction().begin();

        Kategorie k = em.find(Kategorie.class, kategorieKurzel);
        artikel.setKategorie(k);
        System.out.println("___Kategorie für Artikel: [" + artikel.getName() + "] wurde gesetzt.___");

        em.persist(artikel);
        System.out.println("___Artikel: [" + artikel.getName() + "] wurde persistiert___");
        em.getTransaction().commit();

    }

    public void saveArtikel(Artikel artikel) {

        em.getTransaction().begin();
        em.persist(artikel);
        System.out.println("___Artikel: [" + artikel.getName() + "] wurde angelegt___");
        em.getTransaction().commit();
    }

    public void changeArtikel(Artikel artikel) {

        em.getTransaction().begin();
        em.find(Artikel.class, artikel.getArtNr())
                .setName("Jack&Jones Lederjacke");
        em.getTransaction().commit();
    }

    public void deleteArtikel(Artikel artikel) {

        em.getTransaction().begin();
        em.remove(em.find(Artikel.class, artikel.getArtNr()));
        em.getTransaction().commit();
    }


    public void deleteAll(String entityName) {

        Query query = em.createQuery("DELETE FROM " + entityName + " a");
        em.getTransaction().begin();
        int count = query.executeUpdate();
        em.getTransaction().commit();
        System.out.println("Es wurden " + count + " Einträge gelöscht.");
    }

    public List<Artikel> gibArtikel(String anfrage) {

        List<Artikel> result = em.createQuery(anfrage).getResultList();
        for (Artikel a : result) {
            System.out.println("Artikelnummer: " + a.getArtNr()
                    + ", Name: " + a.getName() + ", Preis: " + a.getPreis());
        }
        return result;
    }

    public List<Kategorie> gibKategorie(String anfrage) {

        List<Kategorie> result = em.createQuery(anfrage).getResultList();
        return result;
    }

    public List<Object[]> getEntityList(String anfrage) {

        List<Object[]> result = em.createQuery(anfrage).getResultList();
        return result;
    }
    
    public List<Bestellung> getEntitysBestellungen(String anfrage) {

        List<Bestellung> result = em.createQuery(anfrage).getResultList();
        
        for (Bestellung a : result) {
            System.out.println("_____BestellNr: " + a.getBestellNr()
                    + ", Name: " + a.getBesteller().getVorname() + " " + a.getBesteller().getName() + ", Artikelanzahl: " + a.getAnzahlPositionen()
                    + ", Gesamtpreis: " + a.getGesamtPreis() + ", Datum: " + a.getBestellDatum());
        }
        return result;
    }
    

    public void gibArtikelArray(String anfrage) {

        List<Artikel[]> result = em.createQuery(anfrage).getResultList();
        int i = 0;
        for (Artikel[] a : result) {
            System.out.println("Artikelnummer: " + a[i].getArtNr()
                    + ", Name: " + a[i].getName() + ", Preis: " + a[i].getPreis());
            i++;
        }

    }

    public void saveKunde(Kunde kunde) throws InputException {

        try {
            em.getTransaction().begin();
            em.persist(kunde);
            em.getTransaction().commit();

            System.out.println(
                    "___Kunde: ["
                    + kunde.getVorname()
                    + " "
                    + kunde.getEmail()
                    + "] wurde erfolgreich persistiert!");

        } catch (PersistenceException pe) {

            System.out.println("@@@@@@@@@@ Fehler beim Persistieren: @@@@@@@@@@@");
            pe.printStackTrace();
            em.getTransaction().rollback();

        } catch (ConstraintViolationException cvex) {

            Set<ConstraintViolation<?>> violations = cvex.getConstraintViolations();

            for (ConstraintViolation<?> violation : violations) {
                System.out.println(violation.getMessage());
            }

            em.getTransaction().rollback();

//            throw new InputException(violations);
        }

    }
    
    public void saveBestelldetail(Bestelldetails be) {

        em.getTransaction().begin();
        em.persist(be);
        System.out.println("___Bestelldetail: [" + be.getBestellung().getBestellNr() + "] wurde angelegt___");
        em.getTransaction().commit();
    }
    
    public void setzeBesteller(Bestellung be, String email) {
        
        Kunde k = em.find(Kunde.class, email);
        be.setBesteller(k);
        
        System.out.println("___Bestellung an Kunde: [" + k.getEmail() + "] wurde gesetzt.___");
        
        em.getTransaction().begin();
        em.persist(be); 
        em.getTransaction().commit();
        
        System.out.println("___Bestellung: [" + be.getBestellNr() + "] wurde persistiert___");
    }
    
//    public void setzeBestellung(Bestellung be, Kunde besteller) {
//        
//        em.getTransaction().begin();
//
//        Bestellung b = em.find(Bestellung.class, be.getBestellNr());
//        b.setBestelldetails();
//        System.out.println("___Kategorie für Artikel: [" + artikel.getName() + "] wurde gesetzt.___");
//
//        em.persist(artikel);
//        System.out.println("___Artikel: [" + artikel.getName() + "] wurde persistiert___");
//        em.getTransaction().commit();
//
//    }
    
//    public void setzeBestelldetail(Bestellung be, Kunde besteller) {
//        
//        em.getTransaction().begin();
//
//        Kunde k = em.find(Kunde.class, besteller.getEmail());
//        k.set(k);
//        System.out.println("___Kategorie für Artikel: [" + artikel.getName() + "] wurde gesetzt.___");
//
//        em.persist(artikel);
//        System.out.println("___Artikel: [" + artikel.getName() + "] wurde persistiert___");
//        em.getTransaction().commit();
//
//    }

    
    /**
     * Entity-Manager schließen.
     */
    public void close() {
//        em.flush();
        em.close();
        emf.close();
    }
}
