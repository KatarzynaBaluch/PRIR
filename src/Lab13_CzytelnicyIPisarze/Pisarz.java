package Lab13_CzytelnicyIPisarze;

public class Pisarz extends Thread {
    Czytelnia czytelnia;

    public Pisarz(Czytelnia czytelnia) {
        this.czytelnia = czytelnia;
    }

    public void run()
    {
        try {
            while (true)
            {
                System.out.println("\t\t\tPisarz: "+ getId() + " pozdrawia");
                sleep((int) (Math.random() * 100));
                System.out.println("\t\t\tPisarz: "+ getId() + " chce pisać");
                czytelnia.chcePisac();
                sleep((int) (Math.random() * 100));
                czytelnia.koniecPisania();
                System.out.println("\t\t\tPisarz: "+ getId() + " skończył pisać");

            }
        }
        catch (InterruptedException ex) {}
    }
}
