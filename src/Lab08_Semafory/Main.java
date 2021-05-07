package Lab08_Semafory;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Semaphore sem = new Semaphore(0,true);

        Semaphore semC = new Semaphore(2);
        Thread tA = new watekA(sem,semC);
        Thread tB = new watekB(sem,semC);
        Thread tC = new watekC(semC,sem);

        tA.start();
        tB.start();
        tC.start();

        Thread.sleep(1000);

        tA.interrupt();
        tB.interrupt();
        tC.interrupt();
    }
}


class watekA extends Thread{
    Semaphore sem,semC;
    public watekA(Semaphore sem,  Semaphore semC)
    {
        this.sem = sem;
        this.semC = semC;
    }
    @Override
    public void run()
    {
        try {
            while (true){

                sem.acquire();
                System.out.println("Watek A=");
                sleep((int)(Math.random()*20));
                semC.release();
            }
        } catch (InterruptedException e) {
            System.out.println("Koniec watku A");

        }
    }
}
class watekB extends Thread{

    Semaphore sem, semC;
    public watekB(Semaphore sem, Semaphore semC)
    {
        this.sem = sem;
        this.semC = semC;


    }
    @Override
    public void run()
    {
        try {
            while (true){

                sem.acquire();
                System.out.println("Watek B=");
                sleep((int)(Math.random()*20));
                semC.release();
            }
        } catch (InterruptedException e) {
            System.out.println("Koniec watku B");

        }
    }
}
class watekC extends Thread {

    Semaphore sem, semC;

    public watekC(Semaphore sem, Semaphore semC) {

        this.sem = sem;
        this.semC = semC;

    }

    @Override
    public void run() {
        try {
            while (true) {

                semC.acquire(2);
                System.out.println("Watek C=");
                sleep((int) (Math.random() * 20));
                sem.release(2);
            }
        } catch (InterruptedException e) {
            System.out.println("Koniec watku C");

        }
    }
}

