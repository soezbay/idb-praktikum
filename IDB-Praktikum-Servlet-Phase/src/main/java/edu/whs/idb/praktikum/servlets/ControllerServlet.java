/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.whs.idb.praktikum.servlets;

import edu.whs.idb.praktikum.dao.Datenzugriffsobjekt;
import edu.whs.idb.praktikum.entities.Artikel;
import edu.whs.idb.praktikum.entities.Kategorie;
import java.io.IOException;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller-Servlet, das die Geschaeftslogik im Sinne des MVC-Musters
 * uebernehmen soll.
 *
 * @author laarmann
 */
/**
 * Definition der Datenquelle: User, Password und Datenbankname muessen
 * angepasst werden
 */
@DataSourceDefinition(name = "java:app/jdbc/idb_ds",
        minPoolSize = 0,
        initialPoolSize = 0,
        className = "org.apache.derby.jdbc.ClientDataSource",
        transactional = false,
        portNumber = 1527,
        serverName = "localhost",
        user = "app",
        password = "app",
        databaseName = "idb_praktikum"
)
@WebServlet(name = "ControllerServlet", urlPatterns = {"/ControllerServlet"})
public class ControllerServlet extends HttpServlet {
        
    private int firstReqeustflag = 0;
    private int secondReqeustflag = 0;
    
    /**
     * Aufgabenblatt 3, 4 und 5
     *
     * Hier soll entlang der Aufgabenblaetter 3-5 sukzessive die Geschaeftslogik
     * entstehen.
     *
     *
     * Aufgabenblatt 3: Arbeitsschritt 3:
     *
     * - Datenzugriffsobjekt ermitteln und darueber alle Kategorien finden
     * lassen - Kollektion aller Kategorien in die Objektablage des
     * Request-Objekts (Request-Scope) ablegen
     *
     * Aufgabenblatt 4: Arbeitsschritt 1:
     *
     * - Ersetzen Sie den Aufruf der renderResponse-Methode durch eine
     * Weiterleitung (forwarding) an das ViewServlet.
     *
     * Aufgabenblatt 4: Arbeitsschritt 2:
     *
     * - Deklarieren Sie am Anfang eine Variable in der die Artikel der
     * ausgewaehlten Kategorie gespeichrt werden sollen und initialisieren Sie
     * sie mit null.
     *
     * - Lesen Sie zu Beginn den Parameter zur Identifizierung der ausgewaehlten
     * Kategorie aus.
     *
     * - Pruefen Sie, ob der Request eine Kategorieauswahl ist, indem sie den
     * Inhalt des ausgelesenen Paramter ueberpruefen:
     *
     * 1. Parameterwert ist null:
     *
     * - Es wurde keine Kategorie ausgewaehlt
     *
     * 2. Parameterwert liefert ein Kategoriekuerzel:
     *
     * - Ueber das Datenzugriffsobjekt die ausgewaehlte Kategorieentitaet
     * finden.
     *
     * - Finden Sie alle Artikel der ausgewaehlten kategorie und speichern Sie
     * die Liste in die entsprechende Variable.
     *
     * - Legen Sie die ausgewählte Kategorie in Request-Scope ab.
     *
     * - Legen Sie die Artikelliste in Request-Scope ab.
     *
     * Aufgabenblatt 5: Arbeitsschritt 1:
     *
     * - Erstellen Sie vor der eigentlichen Geschaeftslogik das Grund- geruest
     * zur Verwaltung der Session:
     *
     * 1. Deklarieren Sie vor der Session-Verwaltung eine lokale Variable als
     * Set fuer Ihre Kategorien, die die ausgewaehlten Kategorien speichern soll
     * und initialisieren Sie sie mit null.
     *
     * 2. Lesen Sie das Session-Objekt und speichern Sie es in eine lokale
     * Variable.
     *
     * 3. Pruefen Sie, ob das Session-Objekt neu ist:
     *
     * a) Das Session-Objekt ist neu:
     *
     * - Erzeugen Sie eine leeres Set (z.B. HashSet) und speichern Sie sie in
     * die Variable ausgewaehlter Kategorien.
     *
     * - Legen Sie das leere Set in den Session-Scope ab.
     *
     * b) Das Session-Objekt ist nicht neu:
     *
     * - Lesen Sie das Set aller ausgewaehlten Kategorien aus dem Session-Scope
     * und speichern Sie sie in die Variable ausgewaehlter Kategorien. *
     *
     * Aufgabenblatt 5: Arbeitsschritt 2:
     *
     * - Passen Sie den bestehen Code derart an, dass, wenn der Request einen
     * Kategorie-Parameter enthaelt, nun die entsprechende Kategorieentitaet des
     * Sets der ausgwaehlten Kategorien hinzugefuegt wird.
     *
     * - Finden Sie fuer jede ausgewaehlte Kategorieentitaet die zugehoerigen
     * Artikel und fuegen Sie diese der Liste aller ausgewaehlten Artikel hinzu.
     *
     * - Legen Sie das Set der ausgwaehlten Kategorien in den Request-scope
     *
     * - Legen Sie die Liste der ausgwaehlten Artikel in den Request-scope
     *
     * Aufgabenblatt 5: Arbeitsschritt 3:
     *
     * - Pruefen Sie nun zunaechst, ob die neu ausgewaehlte Kategorie bereits
     * dem Set ausgewaehlter Kategorien angehoert:
     *
     * 1. Kategorie befindet sich bereits im Set:
     *
     * - Entfernen Sie die neu ausgewaehlte Kategorie aus dem Set
     *
     * 2. Kategorie befindet sich nicht im Set:
     *
     * - Fuegen Sie die neu ausgewaehlte Kategorie dem Set hinzu.
     *
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        Datenzugriffsobjekt dao = getDao();
        /**
         * Sessionverwaltung (Aufgabenblatt 5)
         */
        /**
         * Beginn der eigentlichen Geschaeftslogik
         */

        List<Kategorie> k = dao.gibAlleKategorien();
        Collections.sort(k, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        Kategorie startseite = new Kategorie();
        startseite.setKurzel("ST");
        startseite.setName("Startseite");
        k.add(0, startseite);
        /**
         * Daten für die Anzeigelogik im Request-Scope aufbereiten
         */
        for (int i = 0; i < k.size(); i++) {
            Kategorie kat = k.get(i);
            request.setAttribute("kat_" + i, kat);
        }

        /**
         * Weiterleitung an die Anzeiglogik:
         *
         * - Aufgabenblatt 3: An renderResponse(...)
         *
         * - Ab Aufgabenblatt 4: An das Anzeige-Servlet
         */
        RequestDispatcher rd = request.getRequestDispatcher("ViewServlet");
        if(this.firstReqeustflag == 0) {
            rd.include(request, response);
            this.firstReqeustflag = 1;           
        }
        
        
        String categoryIdentifier = request.getParameter("katkuerzel");
        System.out.println("categoryIdentifier TEST_________________________");
        System.out.println(categoryIdentifier);
        

//        String kuerzel = categoryIdentifier.substring(categoryIdentifier.length() - 2);
//        System.out.println(kuerzel);

        //Nur die Artikel mit dem gewünschtem Kürzel ausfiltern
        List<Artikel> selectedArtikel;
        if (categoryIdentifier != null) {
            selectedArtikel = dao.gibAlleArtikel()
                    .stream()
                    .filter(a -> a.getKategorie().getKurzel().contains(categoryIdentifier))
                    .collect(Collectors.toList());
        } else {
            selectedArtikel = Collections.emptyList();
        }
        
        System.out.println("SELECTED ARTIKEL TEST_________________________");
        selectedArtikel.stream()
                .map(Artikel::getName)
                .forEach(System.out::println);

        request.setAttribute("artikel_list", selectedArtikel);
        request.setAttribute("selectedCategory", categoryIdentifier);
        request.setAttribute("selectedCategoryFullName", k.stream().filter(a -> a.getKurzel().contains(categoryIdentifier)).toList().get(0).getName());

        rd.forward(request, response);

    }

    /**
     * Aufgabenblatt 3
     *
     * Hier soll im Rahmen des Aufgabenblatts 3 die Anzeigelogik platziert
     * werden, die in in den Aufgabenblaettern 4 und 5 ein eigenes
     * Anzeige-Servlet uebernehmen wird.
     *
     * Die Weiterleitung aus der Methode processRequest(..) kann durch einfaches
     * Aufrufen dieser Methode mit Uebergabe des request- und response-Objektes
     * erfolgen.
     *
     *
     * Aufgabenblatt 3-4:
     *
     * Aufgabenblatt 3: Arbeitsschritt 2:
     *
     * - Ueber out.println("HTML-Code")-Ausdruecke die Grundstruktur des
     * Artikelbrowsers anzeigen lassen.
     *
     * Aufgabenblatt 3: Arbeitsschritt 3:
     *
     * - Kollektion aller Kategorien aus der Objektablage des Request-Objekts
     * (Request-Scope) holen - Ueber die Kollektion iterieren und einzelnen
     * Kategorien als Anker-Element unter dem Eitrag "Kategorien" aufbereiten.
     * Sie koennen folgende Muster verwenden:
     *
     * <p>
     * <a href="">Kategoriename</a></p>
     *
     *
     * Aufgabenblatt 4: Arbeitsschritt 1:
     *
     * - Kopieren Sie den kompletten Source-Code der renderResponse- Methode in
     * die processRequest-Methode des ViewServlets#
     *
     * - Löschen Sie die renderResponse-Methode oder kommentieren Sei sie aus
     *
     *
     * @param request request-Objekt aus der methode processRequest
     * @param response response-Objekt aus der methode processRequest
     *
     * @throws ServletException
     * @throws IOException
     */
//    protected void renderResponse(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//
//            /**
//             * Der unten stehnde Code hat nur den Zweck, den Startzustand fuer
//             * das Aufgabenblatt 3 zu pruefen. Der Code kann natuerlich im Zuge
//             * der weiteren Arbeitsschritte als Grundlage verwendet und
//             * angepasst werden.
//             */
//
//        }
//
//    }
    /**
     * Sie koennen diese Methode verwenden, um den Zugriff auf das im
     * Application-Scope befindliche Datenzugriffsobjekt zu ermoeglichen.
     *
     * Sie koennen die auskommentierten Codeabschnitte ergaenzen.
     *
     * @return Das Datenzugriffsobjekt
     */
    private Datenzugriffsobjekt getDao() {

        Datenzugriffsobjekt dao;

        // Datenzugriffsobjekt im Servlet-Context finden ...
        dao = (Datenzugriffsobjekt) getServletContext().getAttribute("dao");
        // ... und zurueckgeben
        return dao;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "ControllerServlet";
    }// </editor-fold>

}
