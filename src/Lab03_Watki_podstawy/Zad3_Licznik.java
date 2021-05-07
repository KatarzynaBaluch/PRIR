package Lab03_Watki_podstawy;

public class Zad3_Licznik {
    int licznik;
    Zad3_Licznik() {licznik=0;}
    synchronized void increment()
    {
        licznik++;
    }
    int get()
    {
        return licznik;
    }
}
