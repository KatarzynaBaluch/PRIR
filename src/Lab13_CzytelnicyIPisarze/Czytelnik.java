package Lab13_CzytelnicyIPisarze;

public class Czytelnik extends Thread{
    Czytelnia czytelnia;
    Czytelnik(Czytelnia czytelnia)
    {
        this.czytelnia=czytelnia;
    }

    public void run()
    {
        try {
            while (true) {
                System.out.println("Czytelnik: " + getId() + " pozdrawia");
                sleep((int) (Math.random() * 100));
                System.out.println("Czytelnik: " + getId() + " chce czytać");
                czytelnia.chceCzytac();
                sleep((int) (Math.random() * 100));
                czytelnia.koniecCzytania();
                System.out.println("Czytelnik: " + getId() + " skończył czytać");
            }
        }
        catch (InterruptedException ex) {}
    }

}
