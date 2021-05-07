package Lab03_Watki_podstawy;

public class Zad1i2_Thread1 implements Runnable {

    @Override
    public void run() {
        for (int i=0;i<=20;i++)
        {
            System.out.println("Wątek 1, i: "+i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
