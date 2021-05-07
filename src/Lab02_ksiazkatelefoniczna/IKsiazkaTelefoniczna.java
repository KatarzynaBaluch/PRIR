/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab02_ksiazkatelefoniczna;

import java.util.Collection;

/**
 *
 * @author Marcin
 */
public interface IKsiazkaTelefoniczna {

    void addOsoba(IOsoba osoba);

    Collection<IOsoba> getByImie(String imie);

    Collection<IOsoba> getByNazwisko(String nazwisko);

    IOsoba remove(int id);
    
    int size();
    
}
