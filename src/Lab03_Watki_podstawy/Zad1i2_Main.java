package Lab03_Watki_podstawy;

public class Zad1i2_Main {
    public static void main(String[] args) throws InterruptedException {
        Zad1i2_Thread1 th1=new Zad1i2_Thread1();
        Zad1i2_Thread2 th2=new Zad1i2_Thread2();

        Thread t1=new Thread(th1);
        Thread t2=new Thread(th2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("Wątki "+t1.getName()+" i "+t2.getName()+" zakończone");
    }
}
