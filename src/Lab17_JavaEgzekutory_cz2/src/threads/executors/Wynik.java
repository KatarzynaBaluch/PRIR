package Lab17_JavaEgzekutory_cz2.src.threads.executors;

public class Wynik {

    private String nazwaPliku;
    private String wyraz;
    private int licznik;

    @Override
    public String toString() {
        return "Wynik{" +
                "nazwaPliku='" + nazwaPliku + '\'' +
                ", wyraz='" + wyraz + '\'' +
                ", licznik=" + licznik +
                '}';
    }

    public Wynik(String nazwaPliku, String wyraz, int licznik) {
        this.nazwaPliku = nazwaPliku;
        this.wyraz = wyraz;
        this.licznik = licznik;
    }

    public String getNazwaPliku() {
        return nazwaPliku;
    }

    public String getWyraz() {
        return wyraz;
    }

    public int getLicznik() {
        return licznik;
    }

    public void setNazwaPliku(String nazwaPliku) {
        this.nazwaPliku = nazwaPliku;
    }

    public void setWyraz(String wyraz) {
        this.wyraz = wyraz;
    }

    public void setLicznik(int licznik) {
        this.licznik = licznik;
    }
}
