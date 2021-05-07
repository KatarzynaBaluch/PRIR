package Lab12_Powtorzenie;

import java.util.Arrays;
import java.util.List;


public class MainSynchronized {
    public static void main(String[] args) throws InterruptedException {

        Synchronizator synch = new Synchronizator();

        WatekAA th1 = new WatekAA(synch);
        WatekBB th2 = new WatekBB(synch);
        WatekCC th3 = new WatekCC(synch);

        List<Thread> l = Arrays.asList(
                th1, th2, th3
        );

        l.forEach(t -> t.start());
        Thread.sleep(1000);
        l.forEach(t -> t.interrupt());
    }
}

class Synchronizator {
    boolean dzialaA = true;
    boolean dzialaB = false;
    boolean dzialaC = false;

    synchronized void startA() throws InterruptedException {
        while (!dzialaA) {
            this.wait();
        }
        System.out.println("Start A");
    }

    synchronized void startB() throws InterruptedException {
        while (!dzialaB) {
            this.wait();
        }
        System.out.println("Start B");
    }

    synchronized void startC() throws InterruptedException {
        while (!dzialaC) {
            this.wait();
        }
        System.out.println("Start C");
    }

    synchronized void koniecA() {
            this.dzialaA = false;
            this.dzialaB = true;
            System.out.println("Koniec A");
            notifyAll();
    }

    synchronized void koniecB()  {
            this.dzialaB = false;
            this.dzialaC = true;
            System.out.println("Koniec B");
            notifyAll();
    }

    synchronized void koniecC()  {
            this.dzialaC = false;
            this.dzialaA = true;
            System.out.println("Koniec C");
            notifyAll();
    }
}

class WatekAA extends Thread {

    Synchronizator synch;

    public WatekAA(Synchronizator synch) {
        this.synch = synch;
    }

    public void run() {
        try {
            while (true) {
                synch.startA();
                for (int i=1;i<=20;i++)
                {
                    System.out.print(i+",");
                }
                System.out.println();
                //.out.println("\t\tWatek A");
                //Thread.sleep((int) (Math.random()) * 100);
                synch.koniecA();

            }
        } catch (InterruptedException e) {
            //  e.printStackTrace();
        }
    }
}

class WatekBB extends Thread {

    Synchronizator synch;

    public WatekBB(Synchronizator synch) {
        this.synch = synch;
    }

    public void run() {
        try {
            while (true) {
                synch.startB();
                for (int i=100;i<=120;i=i+2)
                {
                    System.out.print(i+",");
                }
                System.out.println();
                //System.out.println("\t\tWatek B");
                //Thread.sleep((int) (Math.random()) * 10);
                synch.koniecB();

            }
        } catch (InterruptedException e) {
            // e.printStackTrace();
        }
    }
}

class WatekCC extends Thread {

    Synchronizator synch;

    public WatekCC(Synchronizator synch) {
        this.synch = synch;
    }

    public void run() {
        try {
            while (true) {
                synch.startC();
                for (int i=200;i<=220;i++)
                {
                    System.out.print(i+",");
                }
                System.out.println();
                //System.out.println("\t\tWatek C");
                //Thread.sleep((int) (Math.random()) * 10);
                synch.koniecC();

            }
        } catch (InterruptedException e) {
            // e.printStackTrace();
        }
    }
}


