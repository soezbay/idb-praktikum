/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.whs.idb.praktikum.servlets;

import edu.whs.idb.praktikum.entities.Artikel;
import edu.whs.idb.praktikum.entities.Kategorie;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Aufgabenblaetter 4 und 5
 *
 * Die Klasse ViewServlet wird erst ab dem Aufgabenblatt 4 relevant und kann im
 * Zuge der Bearbeitung des Aufgabenblatts 3 noch ignoriert werden
 *
 * Das ViewServlet soll die Rolle der View im Rahmen des MVC-Architekturmusters
 * uebernehmen. In diesem Sinne wird die Request-Verarbeitung nach Ausfuehrung
 * der Geschaeftslogik des Controllers an dieses Servlets weitergeleitet.
 *
 * Die Aufgabe des Anzeige-Servlets ist es, den von der Geschaeftslogik
 * aufbereiteten Zustand in eine HTML-Antwort zu rendern.
 *
 * @author laarmann
 */
@WebServlet(name = "ViewServlet", urlPatterns = {"/ViewServlet"})
public class ViewServlet extends HttpServlet {

    /**
     * Aufgabenblatt 4 und 5
     *
     * Hier soll entlang der Aufgabenblaetter 4 und 5 sukzessive die
     * Anzeigelogik entstehen.
     *
     *
     * Aufgabenblatt 4: Arbeitsschritt 1:
     *
     * - Kopieren Sie den kompletten Source-Code der aus der
     * renderResponse-Methode des ControllerServlets hier hinein
     *
     * Aufgabenblatt 4: Arbeitsschritt 2:
     *
     * - Definieren Sie im Parameter href des Anker-Tags zur Auswahl der
     * Kategorien einen GET-Request mit dem Kategoriekuerzel als Parameter
     *
     * - Pruefen Sie, ob im Request-Scope ein Attribut mit der ausgewaehlten
     * Kategorie liegt:
     *
     * 1. Ja:
     *
     * - Fuegen Sie vor dem angezeigten Namen der ausgwaehlten Kategorie ein '>'
     * ein.
     *
     * - Stellen Sie die Artikel der ausgewaehlten Kategorie in Tabellenform
     * dar.
     *
     * 2. Nein:
     *
     * - Die Kategorieliste ohne Markierung anzeigen
     *
     * - Die Artikeltabelle leer oder komplett weg lassen.
     *
     * Aufgabenblatt 5: Arbeitsschritt 2:
     *
     * - Passen Sie den Code derart an, dass die Markierung der ausgewaehlten
     * Kategorien nun auf Basis der Liste erfolgt.
     *
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public String getStyleString() {
        return "<style>"
                + "    body {"
                + "      margin: 0;"
                + "      padding: 0;"
                + "    }"
                + "    \n"
                + "    .navbar {"
                + "      width: 200px;"
                + "      height: 100vh;"
                + "      background-color: #f1f1f1;"
                + "      position: fixed;"
                + "      top: 0;"
                + "      left: 0;"
                + "    }"
                + "    \n"
                + "    .content {"
                + "      margin-left: 200px;"
                + "      padding: 20px;"
                + "    }"
                + "    \n"
                + "    ul {"
                + "      list-style-type: none;"
                + "      padding: 0;"
                + "      margin: 0;"
                + "    }"
                + "    \n"
                + "    li {"
                + "      padding: 10px;"
                + "    }"
                + "    \n"
                + "    a {"
                + "      text-decoration: none;"
                + "      color: #333;"
                + "    }"
                + "    \n"
                + "    .active {"
                + "      background-color: #ddd;"
                + "    }"
                + "  </style>";

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (PrintWriter out = response.getWriter()) {

            /**
             * Ab Aufgabenblatt 4 hier die Anzeigelogik platzieren
             */
            out.println("<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + getStyleString()
                    + "</head>"
                    + "<body>"
                    + "  <div class=\"navbar\">"
                    + "    <h2> Kategorien</h2>"
                    + "    <ul>");

            // Anzeigen der Kategorien
            String selectedCategory = (String) request.getAttribute("selectedCategory");
            for (int i = 0; request.getAttribute("kat_" + i) != null; i++) {
                Kategorie k = (Kategorie) request.getAttribute("kat_" + i);
                String kat_kurzel = "ControllerServlet?katkuerzel=" + k.getKurzel();
                String kat_name = k.getName();

                // Überprüfen, ob die aktuelle Kategorie ausgewählt ist
                if (selectedCategory != null && selectedCategory.equals(k.getKurzel())) {
                    out.println("<li class=\"active\"><a href=\"" + kat_kurzel + "\"> > " + kat_name + " </a></li>");
                } else {
                    out.println("<li><a href=\"" + kat_kurzel + "\">" + kat_name + " </a></li>");
                }
            }

            out.println("</ul>");
            out.println("</div>");

            String selectedCategoryFullName = (String) request.getAttribute("selectedCategoryFullName");
            List<Artikel> artikel = (List<Artikel>) request.getAttribute("artikel_list");

            if (selectedCategory != null && !selectedCategory.contains("ST")) {
                out.println(
                        "<div class=\"content\">"
                        + "<h1>" + selectedCategoryFullName + "</h1>"
                        + "<table border=\"1\" width=\"500\">");
                out.println(
                        "<tr>"
                        + "    <th>Nr.</th>\n"
                        + "    <th>Name</th>\n"
                        + "    <th>Beschreibung</th>\n"
                        + "    <th>Preis</th>\n"
                        + "  </tr>");

                for (int i = 0; i < artikel.size(); i++) {

                    out.println(
                            "<tr>\n"
                            + "<td>" + artikel.get(i).getArtNr() + "</td>"
                            + "<td>" + artikel.get(i).getName() + "</td>"
                            + "<td width=\"300\">" + artikel.get(i).getBeschreibung() + "</td>"
                            + "<td>" + artikel.get(i).getPreis() + "</td>"
                            + "</tr>");
                }

                out.println(
                        "</div>"
                        + "</body>"
                        + "</html>");

            } else {
                out.println(
                        "<div class=\"content\">"
                        + "    <h1>Willkommen auf unserer Website!</h1>"
                        + "    <h2>Klicken Sie auf eine Bestimmte Kategorie um Artikel anzusehen!<h2>\n"
                        + "  </div>"
                        + "</body>"
                        + "</html>");
            }

        }

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
        return "Short description";
    }// </editor-fold>

}
