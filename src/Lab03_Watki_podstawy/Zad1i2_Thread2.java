package Lab03_Watki_podstawy;

public class Zad1i2_Thread2 implements Runnable{
    @Override
    public void run() {
        for(int i=100;i<=120;i++)
        {
            System.out.println("Wątek 2, i: "+i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
