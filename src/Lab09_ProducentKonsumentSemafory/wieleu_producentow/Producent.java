package Lab09_ProducentKonsumentSemafory.wieleu_producentow;

import Lab09_ProducentKonsumentSemafory.wieleu_producentow.Magazyn;

import java.util.concurrent.atomic.AtomicInteger;

public class Producent extends Thread {

    Magazyn magazyn;
    static int counter;

    public Producent(Magazyn m) {
        magazyn = m;
    }

    public void run() {
        try {
            while (true) {
                String produkt;
                synchronized(Producent.class) {
                    produkt = "" + counter;
                    counter++;
                } //UWAGA tutaj ciągle może dojść do wyścigu ponieważ różne operacja magazyn.add() nie jest synchronizowana, więc inny wątek może dodać wcześniej niż nasz
                magazyn.add(produkt);
                System.out.println("Wrzuciłem do magazynu: " + produkt);
                Thread.sleep((int) (Math.random() * 10));
            }
        } catch (InterruptedException e) {
            System.out.println("Koniec wątu Producenta");
        }
    }
}
