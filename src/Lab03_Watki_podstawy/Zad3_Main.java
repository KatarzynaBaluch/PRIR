package Lab03_Watki_podstawy;

public class Zad3_Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Zad3_Thread.licznik.get());
        int ileWatkow=10;
        Zad3_Thread watki[]=new Zad3_Thread[ileWatkow];
        for (int i=0;i<ileWatkow;i++)
        {
            watki[i]=new Zad3_Thread(i+1);
            watki[i].start();
        }
        for (int i=0;i<ileWatkow;i++)
        {
            watki[i].join();
        }
        System.out.println(Zad3_Thread.licznik.get());
    }
}
