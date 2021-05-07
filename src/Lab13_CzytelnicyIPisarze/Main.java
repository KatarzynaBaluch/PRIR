package Lab13_CzytelnicyIPisarze;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws  InterruptedException{
        Czytelnia czytelnia = new CzyteniaC();
        Pisarz pisarz = new Pisarz(czytelnia);
        Czytelnik[] czytelnicy=new Czytelnik[5];

        for (int i=0;i<czytelnicy.length;i++)
        {
            czytelnicy[i]=new Czytelnik(czytelnia);
        }

        pisarz.start();
        for (Czytelnik c: czytelnicy) {c.start();};


        sleep(1000);
        for(int i=0;i< czytelnicy.length;i++){
            czytelnicy[i].interrupt();
        }
        pisarz.interrupt();

    }
}
