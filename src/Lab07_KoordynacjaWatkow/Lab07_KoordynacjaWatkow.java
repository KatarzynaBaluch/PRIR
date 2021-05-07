package Lab07_KoordynacjaWatkow;

import java.util.regex.Matcher;

import static java.lang.Thread.sleep;

public class Lab07_KoordynacjaWatkow {
    public static void main(String[] args) throws InterruptedException {
        Koordynator koordynator = new Koordynator();
        Thread tA = new WatekA(koordynator);
        Thread tB = new WatekB(koordynator);

        tA.start();
        tB.start();
        Thread.sleep((int) (Math.random() * 10000));
        tA.interrupt();
        tB.interrupt();
    }


}


class Koordynator {
    boolean blokujA = false;
    boolean blokujB = true;

    synchronized public void startA() throws InterruptedException {
        while (blokujA) {
            wait();
        }
        blokujB = true;
    }

    synchronized public void koniecA() {
        this.blokujA = true;
        this.blokujB = false;
        notifyAll();
    }

    synchronized public void startB() throws InterruptedException {
        while (blokujB) {
            wait();
        }
        blokujA = true;
    }

    synchronized public void koniecB() {
        blokujB = true;
        blokujA = false;
        notifyAll();
    }
}

class WatekA extends Thread {
    private Koordynator koordynator;

    public WatekA(Koordynator koordynator) {
        this.koordynator = koordynator;
    }


    @Override
    public void run() {

        try {
            while (true) {
                koordynator.startA();
                System.out.println("= Watek A ="); //System.out.println("Watek B");
                sleep((int) (Math.random() * 20));
                koordynator.koniecA();
            }
        } catch (InterruptedException e) {
            System.out.println("Koniec watku A");
        }

    }
}
class WatekB extends Thread {
    private Koordynator koordynator;

    public WatekB(Koordynator koordynator) {
        this.koordynator = koordynator;
    }

    @Override
    public void run() {
        try {
            while (true) {
                koordynator.startB();
                System.out.println("Watek B ==");
                sleep((int) (Math.random() * 20));
                koordynator.koniecB();
            }
        } catch (InterruptedException e) {
            System.out.println("Koniec watku B");
        }
    }
}
