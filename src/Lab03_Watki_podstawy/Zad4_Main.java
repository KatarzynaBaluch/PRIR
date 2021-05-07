package Lab03_Watki_podstawy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Zad4_Main {
    public static void main(String args[]) throws InterruptedException {

        int k=10;//ilosc watkow
        int t=1000000/(k);
        int min=0;
        int max=0;
        long time1, time2;
        Zad4_Thread watki[]=new Zad4_Thread[k];
        Zad4_Data dane=new Zad4_Data();


        time1=System.currentTimeMillis();


        for(int i=0;i<k;i++)
        {
            min=i*t;
            max=(i+1)*t-1;
            if (max>999999) max=999999;
            watki[i]=new Zad4_Thread(min, max,dane);
           watki[i].start();
            //System.out.println("min: "+min+", Max: "+max);
        }


      for(int i=0;i<k;i++) {
            watki[i].join();

        }


        time2=System.currentTimeMillis();

        System.out.println("Czas "+k+" wątków:  "+(time2-time1)+" ms");
        System.out.println("Podsumowujac - znalezione minimum to: "+watki[0].getMinimum()+", na pozycji: "+watki[0].getPosition());
    }
}