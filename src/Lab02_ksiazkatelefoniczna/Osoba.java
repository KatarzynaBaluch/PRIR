package Lab02_ksiazkatelefoniczna;

import java.util.Objects;

public class Osoba implements IOsoba{
    String imie;
    String nazwisko;
    String nrTelefonu;
    int id;
    static int counter=0;

    public Osoba(String imie, String nazwisko, String nrTelefonu) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nrTelefonu = nrTelefonu;
        id=counter;
        counter++;

    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getImie() {
        return this.imie;
    }

    @Override
    public String getNazwisko() {
        return this.nazwisko;
    }

    @Override
    public String getNrTelefonu() {
        return this.nrTelefonu;
    }

    @Override
    public void setImie(String imie) {
        this.imie=imie;
    }

    @Override
    public void setNazwisko(String nazwisko) {
            this.nazwisko=nazwisko;
    }

    @Override
    public void setNrTelefonu(String nrTelefonu) {
this.nrTelefonu = this.nrTelefonu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Osoba osoba = (Osoba) o;
        return id == osoba.id &&
                imie.equals(osoba.imie) &&
                nazwisko.equals(osoba.nazwisko);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imie, nazwisko, id);
    }
}
