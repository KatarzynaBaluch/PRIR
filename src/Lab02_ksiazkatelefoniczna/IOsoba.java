/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab02_ksiazkatelefoniczna;

/**
 *
 * @author Marcin
 */
public interface IOsoba {

    int getId();

    String getImie();

    String getNazwisko();

    String getNrTelefonu();

    void setImie(String imie);

    void setNazwisko(String nazwisko);

    void setNrTelefonu(String nrTelefonu);
    
}
