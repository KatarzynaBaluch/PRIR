package Lab05_Producent_Klient;

import java.util.LinkedList;
import java.util.Queue;

public class Lab05_Main_ver2 {
        public static void main (String[] avrgs) throws InterruptedException {
            Magazyn2 m=new Magazyn2(7);
            Thread t1=new Thread(new Producent2(m));
            Thread t2=new Thread(new Konsument2(m));
            t1.start();
            t2.start();
            Thread.sleep(5000);
            t1.interrupt();
            t2.interrupt();
            System.out.println("Koniec");
        }
    }

    class Magazyn2{
        Queue<String> queue= new LinkedList<String>();
        int ile;
        Magazyn2(int ile) {this.ile=ile;}

        synchronized void set(String towar) throws InterruptedException{
            while (this.queue.size()==this.ile) {this.wait();}
            this.queue.offer(towar);
            notifyAll();
            System.out.println("DODAŁEM - "+towar);
        }
        synchronized  String get() throws InterruptedException{
            while (this.queue.isEmpty()) {this.wait();}
            String towar2 = this.queue.poll();
            notifyAll();
            System.out.println("\t\t\t\t\t\t"+towar2 +"  - POBRAŁEM");
            return towar2;

        }
    }


    class Producent2 implements Runnable
    {
        Lab05_Producent_Klient.Magazyn2 m;
        int towarId;
        public  Producent2(Lab05_Producent_Klient.Magazyn2 m) {this.m=m;}
        public void run(){
            try {
                while (true)
                {
                    Thread.sleep((long)(250*Math.random()));
                    m.set("Towar id: "+towarId);

                    towarId++;
                }
            }catch (InterruptedException ex) {}

        }
    }


    class Konsument2 implements Runnable
    {
        Lab05_Producent_Klient.Magazyn2 m;
        public  Konsument2(Lab05_Producent_Klient.Magazyn2 m) {this.m=m;}
        public void run(){
            try {
                while (true)
                {
                    Thread.sleep((long)(500*Math.random()));
                    String s=m.get();

                }
            }catch (InterruptedException ex) {}

        }
    }



