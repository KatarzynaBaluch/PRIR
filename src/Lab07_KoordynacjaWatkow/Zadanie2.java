package Lab07_KoordynacjaWatkow;

public class Zadanie2 {
    public static void main(String[] args) throws InterruptedException {
        Koordynator2 koordynator = new Koordynator2();
        Thread tA = new WatekAA(koordynator);
        Thread tB = new WatekBB(koordynator);
        Thread tC = new WatekCC(koordynator);

        tA.start();
        tB.start();
        tC.start();
        Thread.sleep((int) (Math.random() * 5000));
        tA.interrupt();
        tB.interrupt();
        tC.interrupt();
    }
}


class Koordynator2 {
    boolean dzialaA = false;
    boolean dzialaB = false;
    boolean dzialaC = true;

    synchronized public void startA() throws InterruptedException {
        while (!dzialaA)
        {
            wait();
        }
        dzialaC=false;
    }

    synchronized public void koniecA() {
        dzialaA = false;
        if(!dzialaB) dzialaC=true;
        notifyAll();
    }

    synchronized public void startB() throws InterruptedException {
        while (!dzialaB) {
            wait();
        }
        dzialaC=false;
    }

    synchronized public void koniecB() {
        dzialaB = false;
        if(!dzialaA) {
            dzialaC=true;
        }
        notifyAll();
    }

    synchronized public void startC() throws InterruptedException {
        while (!dzialaC) {
            wait();
        }
        dzialaA=false;
        dzialaB=false;
    }

    synchronized public void koniecC() {
        dzialaC=false;
        dzialaB=true;
        dzialaA=true;
        notifyAll();
    }
}

class WatekAA extends Thread {
    private Koordynator2 koordynator;

    public WatekAA(Koordynator2 koordynator) {
        this.koordynator = koordynator;
    }


    @Override
    public void run() {

        try {
            while (true) {
                koordynator.startA();
                System.out.println("__Watek A"); //System.out.println("Watek B");
                sleep((int) (Math.random() * 20));
                koordynator.koniecA();
            }
        } catch (InterruptedException e) {
        }

    }
}

class WatekBB extends Thread {
    private Koordynator2 koordynator;

    public WatekBB(Koordynator2 koordynator) {
        this.koordynator = koordynator;
    }

    @Override
    public void run() {
        try {
            while (true) {
                koordynator.startB();
                System.out.println("_Watek B");
                sleep((int) (Math.random() * 20));
                koordynator.koniecB();
            }
        } catch (InterruptedException e) {
        }
    }
}

class WatekCC extends Thread {
    private Koordynator2 koordynator;

    public WatekCC(Koordynator2 koordynator) {
        this.koordynator = koordynator;
    }

    @Override
    public void run() {
        try {
            while (true) {
                koordynator.startC();
                System.out.println("_________Watek C");
                sleep((int) (Math.random() * 20));
                koordynator.koniecC();
            }
        } catch (InterruptedException e) {
        }
    }
}