package Lab12_Powtorzenie;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Semaphore semA = new Semaphore(1);
        Semaphore semB = new Semaphore(0);
        Semaphore semC = new Semaphore(0);

        WatekA th1 = new WatekA(semA, semB, semC);
        WatekB th2 = new WatekB(semA, semB, semC);
        WatekC th3 = new WatekC(semA, semB, semC);

        List<Thread> l = Arrays.asList(
                th1, th2, th3
        );

        l.forEach(t -> t.start());
        Thread.sleep(1000);
        l.forEach(t -> t.interrupt());
    }
}

    class WatekA extends Thread {
        Semaphore semA;
        Semaphore semB;
        Semaphore semC;

        public WatekA(Semaphore semA, Semaphore semB, Semaphore semC) {
            this.semA = semA;
            this.semB = semB;
            this.semC = semC;
        }

        public void run()
        {
            try {
            while(true)
            {
                semA.acquire();
                System.out.println("Watek A");
                Thread.sleep((int)(Math.random())*100);
                semB.release();

            }
            } catch (InterruptedException e) {
              //  e.printStackTrace();
            }
        }
    }

    class WatekB extends Thread {
        Semaphore semA;
        Semaphore semB;
        Semaphore semC;

        public WatekB(Semaphore semA, Semaphore semB, Semaphore semC) {
            this.semA = semA;
            this.semB = semB;
            this.semC = semC;
        }

        public void run()
        {
            try {
                while(true)
                {
                    semB.acquire();
                    System.out.println("Watek B");
                    Thread.sleep((int)(Math.random())*10);
                    semC.release();

                }
            } catch (InterruptedException e) {
               // e.printStackTrace();
            }
        }
    }

    class WatekC extends Thread {
        Semaphore semA;
        Semaphore semB;
        Semaphore semC;

        public WatekC(Semaphore semA, Semaphore semB, Semaphore semC) {
            this.semA = semA;
            this.semB = semB;
            this.semC = semC;
        }

        public void run()
        {
            try {
                while(true)
                {
                    semC.acquire();
                    System.out.println("Watek C");
                    Thread.sleep((int)(Math.random())*10);
                    semA.release();

                }
            } catch (InterruptedException e) {
               // e.printStackTrace();
            }
        }
    }

