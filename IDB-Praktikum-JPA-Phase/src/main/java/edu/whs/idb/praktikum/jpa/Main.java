/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.whs.idb.praktikum.jpa;

import edu.whs.idb.praktikum.dao.Datenzugriffsobjekt;
import edu.whs.idb.praktikum.dao.exception.InputException;
import edu.whs.idb.praktikum.entities.Artikel;
import edu.whs.idb.praktikum.entities.Bestelldetails;
import edu.whs.idb.praktikum.entities.Bestellung;
import edu.whs.idb.praktikum.entities.Kategorie;
import edu.whs.idb.praktikum.entities.Kunde;
import java.util.Date;
import java.util.List;

/**
 *
 * @author laarmann
 */
public class Main {

    // Hier statisch das Datenzugriffsobjekt hinterlegen, das in der 
    // main-Methode verwaltet wird, damit es innerhalb der Methoden zur Loesung
    // der einzelnen Artbeitsschritte verwendet werden kann.
    private static Datenzugriffsobjekt dao;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InputException {
        // Datenzugriffsobjekt instanziieren und in "dao" hinterlegen
        dao = new Datenzugriffsobjekt("IDB-Praktikum-JPA-PhasePU");

        // Aufgabenblatt 1:
//        loeschenDesArtikelkatalogs();
//        legeLeerenKatalogAn();
//        fuellenDesKatalogsMitArtikeln();
//        gezieltesSelektierenVonArtikeln();
//        loeschenDesArtikelkatalogs();
        // Aufgabenblatt 2:
        anlegenNeuerKunden();
        bestellungenAufgeben();
        bestellungenSuchen();
//        loeschenVonBestellungen();
        // Datenzugriffsobjekt schließen
        dao.close();
    }

    // ---> Beginn: AUFGABENBLATT 1 <---
    /**
     * Aufgabenblatt 1
     *
     * Arbeitsschritt 1 - Anlegen eines leeren Katalogs
     *
     * Ein leerer Katalog reduziert sich auf seine Kategorien. Artikel sind noch
     * nicht vorhanden. Um also einen leeren Katalog anzulegen, müssen wir nur
     * seinen Kategoriebestand definieren. Hier dürfen Sie ruhig kreativ sein!
     *
     * Schreiben Sie eine Methode in Ihrem ausführbaren Projekt, das die
     * Geschäftslogik liefert, mindestens sechs verschiedene Kategorien in die
     * Datenbank zu schreiben. Erweitern Sie dabei die Datenzugriffsklasse um
     * die benötigte Datenzugriffslogik.
     *
     * Hinweis: Berücksichtigen Sie dabei die zwingend notwendige
     * Transaktionsverwaltung!
     *
     */
    public static void legeLeerenKatalogAn() {

        Kategorie kleidung = new Kategorie();
        kleidung.setKurzel("KL");
        kleidung.setName("Kleidung");

        Kategorie haushaltsgeräte = new Kategorie();
        haushaltsgeräte.setKurzel("HG");
        haushaltsgeräte.setName("Haushaltsgeräte");

        Kategorie lebensmittel = new Kategorie();
        lebensmittel.setKurzel("LM");
        lebensmittel.setName("Lebensmittel");

        Kategorie spielzeug = new Kategorie();
        spielzeug.setKurzel("SP");
        spielzeug.setName("Spielzeug");

        Kategorie computer = new Kategorie();
        computer.setKurzel("PC");
        computer.setName("Computer");

        Kategorie buecher = new Kategorie();
        buecher.setKurzel("BU");
        buecher.setName("Bücher");

//        if (!dao.existiertTabelle("KATEGORIE")) {
//            
        dao.saveKategorie(kleidung);
        dao.saveKategorie(haushaltsgeräte);
        dao.saveKategorie(lebensmittel);
        dao.saveKategorie(spielzeug);
        dao.saveKategorie(computer);
        dao.saveKategorie(buecher);
//        } else {
//            System.out.println("----Tabelle 'KATEGORIE' existiert bereits!----");
//        }

    }

    /**
     * Aufgabenblatt 1
     *
     * Arbeitsschritt 2 - Fuellen des Katalogs mit Artikeln
     *
     * Da der leere Katalog nun erstellt ist, wollen wir ihn mit Artikel füllen.
     * Dabei sollte jeder Kategorie mindestens zwei Artikel zugeordnet werden.
     * Um dies tun zu können, muss die betreffende Kategorie zunächst z.B. über
     * seinen Schlüssel gefunden worden sein. Bei der Artikel-Schaffung können
     * Sie Ihrer Kreativität wieder freien Lauf lassen.
     *
     * Schreiben Sie eine weitere Methode in Ihrem ausführbaren Projekt, das die
     * Geschäftslogik liefert, jeder Kategorie mindestens zwei Artikel
     * zuzuordnen und in die Datenbank zu schreiben. Erweitern Sie dabei die
     * Datenzugriffsklasse um die benötigte Datenzugriffslogik.
     *
     * Hinweis: Berücksichtigen Sie dabei wieder die zwingend notwendige
     * Transaktionsverwaltung!
     *
     */
    public static void fuellenDesKatalogsMitArtikeln() {

        Artikel schuh = new Artikel();
        schuh.setArtNr(1);
        schuh.setName("Nike Pandas Dunk Low");
        schuh.setBeschreibung("Nike Pandas Dunk Low, Kunstleder, atmunksaktiv, sehr angenehm zum laufen");
        schuh.setPreis(129.99);
        schuh.setBild("Kein Bild vorhanden");
        dao.setzeKategorie(schuh, "KL");

        Artikel jacke = new Artikel();
        jacke.setArtNr(2);
        jacke.setName("Jack & Jones Winterjacke");
        jacke.setBeschreibung("Winterjacke, wasserfest und gefuttert");
        jacke.setBild("Kein Bild vorhanden");
        jacke.setPreis(79.99);
        dao.setzeKategorie(jacke, "KL");

        Artikel mixer = new Artikel();
        mixer.setArtNr(3);
        mixer.setName("Phillips Standmixer");
        mixer.setBeschreibung("Standmixer 3L, Titanium");
        mixer.setBild("Kein Bild vorhanden");
        mixer.setPreis(89.49);
        dao.setzeKategorie(mixer, "HG");

        Artikel sauger = new Artikel();
        sauger.setArtNr(4);
        sauger.setName("Miele Staubsauger");
        sauger.setBeschreibung("Staubsauger, leise");
        sauger.setBild("Kein Bild vorhanden");
        sauger.setPreis(70);
        dao.setzeKategorie(sauger, "HG");

        Artikel essen1 = new Artikel();
        essen1.setArtNr(5);
        essen1.setName("Pringels Salzig");
        essen1.setBeschreibung("Pringels Salzig");
        essen1.setBild("Kein Bild vorhanden");
        essen1.setPreis(2.49);
        dao.setzeKategorie(essen1, "LM");

        Artikel essen2 = new Artikel();
        essen2.setArtNr(6);
        essen2.setName("Pringels BBQ");
        essen2.setBeschreibung("Pringels BBQ");
        essen2.setBild("Kein Bild vorhanden");
        essen2.setPreis(2.49);
        dao.setzeKategorie(essen2, "LM");

        Artikel spielzeug1 = new Artikel();
        spielzeug1.setArtNr(7);
        spielzeug1.setName("Lego Starwars");
        spielzeug1.setBeschreibung("Lego Starwars X-Flügler");
        spielzeug1.setBild("Kein Bild vorhanden");
        spielzeug1.setPreis(40.99);
        dao.setzeKategorie(spielzeug1, "SP");

        Artikel spielzeug2 = new Artikel();
        spielzeug2.setArtNr(8);
        spielzeug2.setName("Playdo Knete Lila");
        spielzeug2.setBeschreibung("Playdo Knete Lila Vegan");
        spielzeug2.setBild("Kein Bild vorhanden");
        spielzeug2.setPreis(4.99);
        dao.setzeKategorie(spielzeug2, "SP");

        Artikel grafikkarte = new Artikel();
        grafikkarte.setArtNr(9);
        grafikkarte.setName("Nvidia RTX 4090");
        grafikkarte.setBeschreibung("Nvidia RTX 4090");
        grafikkarte.setBild("Kein Bild vorhanden");
        grafikkarte.setPreis(1731.99);
        dao.setzeKategorie(grafikkarte, "PC");

        Artikel grafikkarte2 = new Artikel();
        grafikkarte2.setArtNr(10);
        grafikkarte2.setName("Nvidia RTX 3080 TI");
        grafikkarte2.setBeschreibung("Nvidia RTX 3080 TI");
        grafikkarte2.setBild("Kein Bild vorhanden");
        grafikkarte2.setPreis(659.00);
        dao.setzeKategorie(grafikkarte2, "PC");

        Artikel buch1 = new Artikel();
        buch1.setArtNr(11);
        buch1.setName("Java für Anfänger");
        buch1.setBeschreibung("Java für Anfänger und Amateure");
        buch1.setBild("Kein Bild vorhanden");
        buch1.setPreis(39.99);
        dao.setzeKategorie(buch1, "BU");

        Artikel buch2 = new Artikel();
        buch2.setArtNr(12);
        buch2.setName("Python für Anfänger");
        buch2.setBeschreibung("Python für Anfänger und Amateure");
        buch2.setBild("Kein Bild vorhanden");
        buch2.setPreis(29.99);
        dao.setzeKategorie(buch2, "BU");

    }

    /**
     * Aufgabenblatt 1
     *
     * Arbeitsschritt 3 - Gezieltes Selektieren von Artikeln
     *
     * Nun wollen wir den frisch erstellten Artikelkatalog nutzen, um gezielt
     * Artikel zu selektieren. Dazu soll im wesentlichen das Konzept der Jakarta
     * Persistence Query Language (JPQL) zum Einsatz kommen.
     *
     * Schreiben Sie eine Methode in Ihrem ausführbaren Projekt, das die
     * Geschäftslogik liefert, die nachfolgenden Abfragen auszuführen und die
     * Ergebnisse in System.out zu schreiben. Erweitern Sie wie gehabt dabei die
     * Datenzugriffsklasse um die benötigte Datenzugriffslogik.
     *
     * 1. Finden Sie alle Artikel (artNr, name, preis), die mit „A“ beginnen. 2.
     * Finden Sie den teuersten Artikel (artNr, name, preis). 3. Finden Sie für
     * alle Kategorien (name) die jeweils zugehörigen Artikel (artNr, name,
     * preis) und geben Sie das Ergebnis entsprechend strukturiert aus.
     *
     */
    public static void gezieltesSelektierenVonArtikeln() {

        System.out.println("----------------------------------NO.1----------------------------------------");

        dao.gibArtikel("SELECT a FROM Artikel a WHERE a.name LIKE 'N%'");

        System.out.println("----------------------------------NO.2---------------------------------------");

        dao.gibArtikel("SELECT a FROM Artikel a WHERE a.preis = (SELECT MAX(a2.preis) FROM Artikel a2)");

        System.out.println("----------------------------------NO.3----------------------------------------");

        List<Object[]> resultList = dao.getEntityList("SELECT k.name, a.artNr, a.name, a.preis"
                + " FROM Artikel a, Kategorie k WHERE k.name = a.kategorie.name"
                + " ORDER BY k.name");

        for (Object[] result : resultList) {
            String kategorieName = (String) result[0];
            long artNr = (long) result[1];
            String artikelName = (String) result[2];
            double preis = (double) result[3];

            System.out.println(kategorieName + ": " + artNr + " - " + artikelName + " - " + preis);
        }
    }

    /**
     * Aufgabenblatt 1
     *
     * Arbeitsschritt 4 - Loeschen des Artikelkatalogs
     *
     * Nun ist es an der Zeit, den Artikelkatalog wieder zu löschen. Der Katalog
     * lässt sich ja jederzeit über die im Rahmen der Arbeitsschritte 1 und 2
     * erarbeiteten Geschäftsmethoden wieder neu anlegen. Berücksichtigen Sie
     * bei dem Löschvorgang Abhängigkeiten zwischen Artikel und Kategorien.
     *
     * Schreiben Sie eine Methode in Ihrem ausführbaren Projekt, das die
     * Geschäftslogik liefert, alle Artikel und Kategorien zu löschen. Erweitern
     * Sie dabei die Datenzugriffsklasse um die benötigte Datenzugriffslogik.
     *
     * Hinweis: Berücksichtigen Sie dabei wieder die zwingend notwendige
     * Transaktionsverwaltung!
     */
    public static void loeschenDesArtikelkatalogs() {

        dao.deleteAll("Artikel");
        dao.deleteAll("Kategorie");
    }

    // ---> Ende: AUFGABENBLATT 1 <---
    // ---> Beginn: AUFGABENBLATT 2 <---
    /**
     * Aufgabenblatt 2
     *
     * Arbeitsschritt 1 - Anlegen neuer Kunden
     *
     * Das Anlegen eines neuen Kunden ist natürlich nichts neues mehr und
     * erfolgt nach dem selben Prinzip, das bereits für Artikel und Kategorien
     * angewandt wurde. Neu dazu kommt allerdings, dass wir nun
     * Validierungsregeln berücksichtigen wollen. Diese sind bereits in der
     * Entitätsklasse Kunde definiert, müssen aber bei jedem Schreibprozess
     * geprüft werden. Da die Prüfung selbst implizit vom Entity-Manager
     * übernommen wird, besteht Ihre Aufgabe darin, im Rahmen der
     * Transaktionsverwaltung mögliche Validierungsfehler abzufangen und richtig
     * zu verarbeiten.
     *
     * Schreiben Sie eine Methode in Ihrem ausführbaren Projekt, das die
     * Geschäftslogik liefert, beliebig viele Kunden mit gültigen und ungültigen
     * Werten für die Attribute „name“, „vorname“ und „email“ in die Datenbank
     * zu schreiben. Erweitern Sie dabei die Datenzugriffsklasse um die
     * benötigte Datenzugriffslogik. Diese muss im Rahmen der bereits bekannten
     * Transaktionsabwicklung nun zusätzlich prüfen, ob Validierungsfehler
     * aufgetreten sind. Um der Geschäftslogik Validierungsfehler mitteilen zu
     * können, möchten wir eine eigens dafür konzipierte Exception (z.B.
     * InputException, in der Projektvorlage bereits vordefiniert) einführen,
     * die alle auftretenden Validierungsfehlermeldungen zusammenfasst und an
     * die aufrufende Geschäftslogik geworfen wird. Innerhalb der Geschäftslogik
     * muss diese Exception abfangen und die entsprechenden Fehlermeldungen in
     * System.out ausgeben werden.
     *
     * Hinweis: Berücksichtigen Sie dabei wieder die zwingend notwendige
     * Transaktionsverwaltung!
     *
     */
    public static void anlegenNeuerKunden() throws InputException {

        Kunde kunde1 = new Kunde();
        kunde1.setName("Mustermann");
        kunde1.setVorname("Max");
        kunde1.setEmail("max.mustermann@studmail.w-hs.de");
        kunde1.setAdresse("Vogelstraße 1, 45968, Gladbeck");

        Kunde kunde2 = new Kunde();
        kunde2.setName("Kruger");
        kunde2.setVorname("Kruger");
        kunde2.setEmail("kruger.kruger@studmail.w-hs.de");
        kunde2.setAdresse("Vogelstraße 2, 46943, Gelsenkirchen");

        Kunde kunde3 = new Kunde();
        kunde3.setName("Jean");
        kunde3.setVorname("Kirschstein");
        kunde3.setEmail("jean.kirschstein@studmail.w-hs.de");
        kunde3.setAdresse("Vogelstraße 3, 47234, Bottrop");

        Kunde kunde4 = new Kunde();
        kunde4.setName("Jean");
        kunde4.setVorname("Kirschstein");
        kunde4.setEmail("jean.kirschsteinstudmail.w-hs.de");
        kunde4.setAdresse("Vogelstraße 3, 47234, Bottrop");

        Kunde kunde5 = new Kunde();
        kunde5.setName("Jean");
        kunde5.setVorname("");
        kunde5.setEmail("jean.kirschstein@studmail.w-hs.de");
        kunde5.setAdresse("Vogelstraße 3, 47234, Bottrop");

        Kunde kunde6 = new Kunde();
        kunde6.setName("");
        kunde6.setVorname("Kirschstein");
        kunde6.setEmail("jean.kirschstein@studmail.w-hs.de");
        kunde6.setAdresse("Vogelstraße 3, 47234, Bottrop");

        dao.deleteAll("Bestelldetails");
        dao.deleteAll("Bestellung");
        dao.deleteAll("Kunde");
        dao.saveKunde(kunde1);
        dao.saveKunde(kunde2);
        dao.saveKunde(kunde3);
//        dao.saveKunde(kunde4);
//        dao.saveKunde(kunde5);
//        dao.saveKunde(kunde6);

    }

    /**
     * Aufgabenblatt 2
     *
     * Arbeitsschritt 2 - Bestellungen aufgeben
     *
     * Wir haben nun die Voraussetzung geschaffen, endlich die erste Bestellung
     * aufgeben zu können. Dies klingt trivial, birgt aber eine nicht zu
     * unterschätzende Komplexität. Denn eine Bestellung im Ganzen verknüpft
     * mehrere Entitäten über deren Beziehungen miteinander und es muss dringen
     * die Datenintegrität sichergestellt sein. Diese wird konkret verletzt,
     * wenn z.B. beim Persistieren einer Bestellung seine Bestelldetails selbst
     * noch nicht persistent sind. Dies muss also berücksichtigt werden. Eine
     * geeignete Lösung wäre z.B. die Nutzung von Persist-Kaskaden.
     *
     * Schreiben Sie eine weitere Methode in Ihrem ausführbaren Projekt, das die
     * Geschäftslogik liefert, mindestens sechs Bestellungen über beliebige
     * Artikel abzusetzen. Lassen Sie die Kunden bestellen, die Sie im
     * vorangegangenen Arbeitsschritt eingepflegt haben. Erweitern Sie wieder
     * die Datenzugriffsklasse um die benötigte Datenzugriffslogik.
     *
     * Hinweis: Berücksichtigen Sie dabei wieder die zwingend notwendige
     * Transaktionsverwaltung!
     */
    public static void bestellungenAufgeben() {

        long lo = 1;
        Long l = lo;
        long ll = 1;
        {
            Bestellung b = new Bestellung();
            b.setBestellNr(lo);
            b.setBestellDatum(new Date(20, 12, 12));

            Bestelldetails bd = new Bestelldetails();
            Artikel a = dao.findeEntity(Artikel.class, ll);
            bd.setArtikel(a);
            bd.setAnzahl(3);
            bd.setPreis(a.getPreis() * bd.getAnzahl());
            bd.setBestellung(b);

            Bestelldetails bd2 = new Bestelldetails();
            Artikel a2 = dao.findeEntity(Artikel.class, ll + 1);
            bd2.setArtikel(a2);
            bd2.setAnzahl(3);
            bd2.setPreis(a2.getPreis() * bd2.getAnzahl());
            bd2.setBestellung(b);

            b.setAnzahlPositionen(bd2.getAnzahl() + bd.getAnzahl());
            b.setGesamtPreis(bd2.getPreis() + bd.getPreis());

            dao.setzeBesteller(b, "max.mustermann@studmail.w-hs.de");
            dao.saveBestelldetail(bd);
            dao.saveBestelldetail(bd2);

        }

        {
            Bestellung b = new Bestellung();
            b.setBestellNr(lo + 1);
            b.setBestellDatum(new Date(20, 3, 3));

            Bestelldetails bd = new Bestelldetails();
            Artikel a = dao.findeEntity(Artikel.class, ll + 3);
            bd.setArtikel(a);
            bd.setAnzahl(10);
            bd.setPreis(a.getPreis() * bd.getAnzahl());
            bd.setBestellung(b);

            b.setAnzahlPositionen(bd.getAnzahl());
            b.setGesamtPreis(bd.getPreis());

            dao.setzeBesteller(b, "kruger.kruger@studmail.w-hs.de");
            dao.saveBestelldetail(bd);

        }

        {

            Bestellung b = new Bestellung();
            b.setBestellNr(lo + 2);
            b.setBestellDatum(new Date(2020, 4, 4));

            Bestelldetails bd = new Bestelldetails();
            Artikel a = dao.findeEntity(Artikel.class, ll);
            bd.setArtikel(a);
            bd.setAnzahl(3);
            bd.setPreis(a.getPreis() * bd.getAnzahl());
            bd.setBestellung(b);

            b.setAnzahlPositionen(bd.getAnzahl());
            b.setGesamtPreis(bd.getPreis());

            dao.setzeBesteller(b, "jean.kirschstein@studmail.w-hs.de");
            dao.saveBestelldetail(bd);

        }
    }

    /**
     * Aufgabenblatt 2
     *
     * Arbeitsschritt 3 - Bestellungen suchen
     *
     * Nun wollen wir die soeben aufgenommenen Bestelldaten als Basis für
     * Abfragen nutzen, wozu wieder das Konzept der Jakarta Persistence Query
     * Language (JPQL) zum Einsatz kommen soll. Das komplexere Entitätenmodell
     * unserer Bestellung mit mehrstufigen Beziehungen eignet sich gut, um
     * Abfrageformulierungen unter Einbezug von Pfad-Navigation zu üben.
     * Benutzen Sie Pfad-Navigation soweit es möglich ist.
     *
     * Hinweis: Pfad-Navigationen über Kollektionen sind nur über
     * JOIN-Verknüpfungen in der FROM-Klausel möglich!
     *
     * Schreiben Sie eine Methode in Ihrem ausführbaren Projekt, das die
     * Geschäftslogik liefert, die nachfolgenden Abfragen auszuführen und die
     * Ergebnisse in System.out zu schreiben. Erweitern Sie wie gehabt dabei die
     * Datenzugriffsklasse um die benötigte Datenzugriffslogik.
     *
     * 1. Finden Sie alle Bestellungen, über einen konkreten Artikel Ihrer Wahl.
     * 2. Finden Sie die Bestellung mit dem höchsten Gesamtpreis. 3. Welche
     * Bestellung oder Bestellungen verfügen über die meisten bestellten
     * Artikelpositionen.
     */
    public static void bestellungenSuchen() {

        System.out.println("");
        System.out.println("@@@ Bestellung Suchen @@@");
        System.out.println("Bestimmtes Artikel:");
        List<Bestellung> resultList
                = dao.getEntitysBestellungen("SELECT b FROM Bestellung b JOIN b.bestelldetails bd WHERE bd.artikel.name = 'Nike Pandas Dunk Low'");

        System.out.println("Bestellung mit höchster Gesamtpreis:");
        List<Bestellung> resultList2
                = dao.getEntitysBestellungen("SELECT b FROM Bestellung b WHERE b.gesamtpreis = "
                        + "(SELECT MAX(bd.gesamtpreis) FROM Bestellung bd)");

//        List<Bestellung> resultList3
//                = dao.getEntitysBestellungen("SELECT b FROM Bestellung b WHERE b.anzahlPositionen = "
//            + "(SELECT MAX(bd.anzahlPositionen) FROM Bestellung bd)");
        List<Bestellung> resultList3
                = dao.getEntitysBestellungen("SELECT b FROM Bestellung b WHERE b.anzahlPositionen = "
                        + "(SELECT MAX(bd.anzahlPositionen) FROM Bestellung bd)");
    }

    /**
     * Aufgabenblatt 2
     *
     * Arbeitsschritt 4 - Loeschen von Bestellungen
     *
     * Zu guter Letzt möchten wir wieder alle Bestellungen aus der Datenbank
     * löschen. Dabei müssen Sie – wie in Arbeitsschritt 2 auch – die
     * Datenintegrität berücksichtigen. Eine Bestellung darf demnach erst
     * gelöscht werden, wenn all seinen Bestelldetails bereits entfernt wurden.
     * Auch in diesem Fall ist der Einsatz von Lösch-Kaskaden eine geeignete
     * Lösung.
     *
     * Schreiben Sie eine Methode in Ihrem ausführbaren Projekt, das die
     * Geschäftslogik liefert, alle Bestellungen inklusive der zugehörigen
     * Bestelldetails zu löschen. Erweitern Sie dabei die Datenzugriffsklasse um
     * die benötigte Datenzugriffslogik.
     *
     * Hinweis: Berücksichtigen Sie dabei wieder die zwingend notwendige
     * Transaktionsverwaltung!
     */
    public static void loeschenVonBestellungen() {

    }

    // ---> Ende: AUFGABENBLATT 2 <---
}
