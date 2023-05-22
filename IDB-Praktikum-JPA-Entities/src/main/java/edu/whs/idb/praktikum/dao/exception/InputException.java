/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.whs.idb.praktikum.dao.exception;

import jakarta.validation.ConstraintViolation;
import java.util.Set;

/**
 * Ausnahme, die dem Aufrufer des Datenzugriffsobnjekts Eingabefehler 
 * signalisieren soll. 
 * 
 * Das Datenzugriffsobjekt soll diese Ausnahme verwenden, um Aufrufer der 
 * dieser DAO-Schnittstelle ueber Validierungsfehler zu informieren.
 * 
 * Beispiel: 
 *    Innerhalb des Datenzugriffsobjekts wird eine spezifische Ausnahme 
 *    (z.B. ConstraintViolationException) per try-catch gefangen. Diese 
 *    spezifische Ausnahme soll nicht direkt an den Aufrufer weiter gegeben 
 *    sondern als InputException "ubersetzt" werden, um von der JPA zu 
 *    abstrahieren.
 * 
 *       ...
 *       } catch (ConstraintViolationException cve) {
 *          // Verarbeitung der Ausnahme
 *  
 *          // Uebersetzen in diese Ausnahme
 *          // z.B.:
 *          // InputException ie = new InputException("passende Fehlermeldung")
 * 
 *          throw ie;
 *       }
 * 
 * @author laarmann
 */
public class InputException extends Exception {
    
    Set<ConstraintViolation<?>> violations;
    
    public InputException(Set<ConstraintViolation<?>> violations) {
        
        this.violations = violations;
    }
    
    

    
}
