package Lab10_ProblemUcztujacychFilozofow;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        int LICZBA_PALECZEK=5;
        Semaphore[] paleczki=new Semaphore[LICZBA_PALECZEK];
        Semaphore stol=new Semaphore(LICZBA_PALECZEK-1);

        for(int i=0;i<LICZBA_PALECZEK; i++)
        {
            paleczki[i]=new Semaphore(1);
        }

        for (int i=0;i<LICZBA_PALECZEK;i++)
        {
            (new Filozof(i, paleczki, stol)).start();
        }
    }
}
