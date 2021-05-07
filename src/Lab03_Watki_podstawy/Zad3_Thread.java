package Lab03_Watki_podstawy;

public class Zad3_Thread extends Thread {
    private int numer;
    static Zad3_Licznik licznik = new Zad3_Licznik();
    Zad3_Thread(int id)
    {
        numer=id;
    }
    public void run(){
        System.out.println("Wątek: "+numer);
        for(int i=0;i<100000;++i){
            licznik.increment();
        }
    }

}
