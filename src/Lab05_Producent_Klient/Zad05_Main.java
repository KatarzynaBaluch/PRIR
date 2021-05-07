package Lab05_Producent_Klient;

public class Zad05_Main {
    public static void main (String[] avrgs) throws InterruptedException {
        Magazyn m=new Magazyn();
        Thread t1=new Thread(new Producent(m));
        Thread t2=new Thread(new Konsument(m));
        t1.start();
        t2.start();
        Thread.sleep(5000);
        t1.interrupt();
        t2.interrupt();
        System.out.println("Koniec");
    }
    }

class Magazyn{
    String towar;
    boolean jestTowar=false;
    synchronized void set(String towar) throws InterruptedException{
        while (jestTowar) {this.wait();}
        this.towar=towar;
        jestTowar=true;
        notifyAll();
        System.out.println("Dodałem towar: "+towar);
   }
   synchronized  String get() throws InterruptedException{
        while (!jestTowar) {this.wait();}
        jestTowar=false;
        notifyAll();
       System.out.println("Pobrałem towar: "+towar);
        return towar;

   }
}


class Producent implements Runnable
{
    Magazyn m;
    int towarId;
    public  Producent(Magazyn m) {this.m=m;}
    public void run(){
        try {
            while (true)
            {
                Thread.sleep((long)(500*Math.random()));
                m.set("Towar id: "+towarId);

                towarId++;
            }
        }catch (InterruptedException ex) {}

    }
}


    class Konsument implements Runnable
    {
        Magazyn m;
        public  Konsument(Magazyn m) {this.m=m;}
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



