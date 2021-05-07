package Lab04_Algorytm;

public class Lab04_Main {


    public static  void main (String args[]) throws InterruptedException {
        Licznik l=new Licznik();
        Monitor m=new MonitorImpl();

        Thread th1=new Watek1(l,m);
        Thread th2=new Watek2(l,m);
        th1.start();
        th2.start();

        th1.join();
        th2.join();
        System.out.println(l.get());
    }

}

interface Monitor{
void proceduraWejsciowaWatek1();
    void proceduraWejsciowaWatek2();
    void proceduraWyjsciowaWatek1();
    void proceduraWyjsciowaWatek2();
}

class MonitorImpl implements Monitor{

    private volatile Boolean[] flaga={true,false};
    private volatile int turn;
    @Override
    public void proceduraWejsciowaWatek1() {
        flaga[0]=true;
        turn=2;
        do
        {
        }while(!(turn==1||flaga[1]==false));
    }

    @Override
    public void proceduraWejsciowaWatek2() {
        flaga[1]=true;
        turn=1;
        do
        {
        }while(!(turn==2||flaga[0]==false));
    }

    @Override
    public void proceduraWyjsciowaWatek1() {
        flaga[0]=false;
    }

    @Override
    public void proceduraWyjsciowaWatek2() {
        flaga[1]=false;
    }
}


class Watek1 extends Thread
{
    Licznik l;
    Monitor m;
    public Watek1(Licznik l, Monitor m)
    {
        this.l=l;
        this.m=m;
    }
    public void run()
    {
        try{
            for(int i=0;i<100;i++){
               //m.proceduraWejsciowaWatek1();
                synchronized (l) { //albo synchronized(Thread.class)
                    l.increment();
                    System.out.println("W1, increment");
                    sleep(2);
                }
               // m.proceduraWyjsciowaWatek1();
            }
        }catch (InterruptedException e){ e.printStackTrace();}
    }
}

class Watek2 extends Thread
{
    Licznik l;
    Monitor m;
    public Watek2(Licznik l, Monitor m)
    {
        this.l=l;
        this.m=m;
    }
    public void run()
    {
        try{
            for(int i=0;i<100;i++){
                //m.proceduraWejsciowaWatek2();
                synchronized (l) { //albo synchronized(Thread.class)
                    l.increment();
                    System.out.println("W2, increment");
                    sleep(2);
                }
                //m.proceduraWyjsciowaWatek2();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class Licznik
{
    public int i=0;

public void increment()
{
    i++;
}
    public int get() {
        return i;
    }
}