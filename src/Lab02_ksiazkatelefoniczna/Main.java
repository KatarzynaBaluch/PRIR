/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab02_ksiazkatelefoniczna;

import java.util.Collection;

import static java.lang.System.*;

/**
 *
 * @author Marcin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IKsiazkaTelefoniczna kt = new KsiazkaTelefoniczna();
        IOsoba o1, o2, o3;
        //Dodajemy osoby
        o1 = new Osoba("Jan", "Kowalski", "31 123-345-543");
        kt.addOsoba(o1);
        o2 = new Osoba("Jan", "Nowak", "21 10-10-10-01");
        kt.addOsoba(o2);
        o3 = new Osoba("Marek", "Kowalski", "32 44-55-66-77");
        kt.addOsoba(o3);
        //Sprawdzamy wyszukiwanie po imieniu
        Collection<IOsoba> kol = kt.getByImie("Jan");
        if (kol.size()!=2)
        {
            out.println("Błąd - za duzo osob przy odczycie byImie");
            System.exit(1);
        }
        for (IOsoba o : kol) {
           // if (!"Jan".equals(o.getImie())) {
            String imie = o.getImie();
            if ("Jan".equals(imie)){ //equalsIgnoreCases - porównanie z pominięciem wielkości liter
                out.println("Błąd Jan.equals(o.getImie()");
                System.exit(1);
            }
        }
        //Sprawdzamy rozmiar
        if (kt.size()!=3){
            out.println("Błąd rozmiaru listy");
            System.exit(1);
        }
        //Usuwamy element
        kt.remove(o2.getId());
        //Sprawdzamy rozmiar
        if (kt.size()!=2){
            out.println("Błąd rozmiary Książki Adresowej");
            System.exit(1);
        }
        //Wyszukujemy po nazwisku - powinno być dwóch kowalskich
        String nazwisko = "Kowalski";
        kol = kt.getByNazwisko(nazwisko);
        int i = 0;
        if (kol.size()!=2)
        {
            out.println("Błąd - za duzo osob przy odczycie byNazwisko");
        }
        for(IOsoba o : kol){
            if (!"Kowalski".equals(o.getNazwisko())){
                out.println("Błąd osoba powinna mieć innego nazwiska niż Kowalski ");
                System.exit(1);
            }
            i++;
        }
    }
}
