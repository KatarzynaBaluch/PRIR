package Lab03_Watki_podstawy;

import java.util.LinkedList;

public class Zad4_Thread extends Thread {

    Zad4_Data dane;
    int min, max, position;
    double minimum;

    Zad4_Thread(int minn, int maxx, Zad4_Data d)
    {
        this.min=minn;
        this.max=maxx;
        this.dane=d;
    }

    public double getMinimum() {return  this.minimum;}
    public int getPosition() {return position;}
    @Override
    public void run() {

        minimum=dane.lista.get(min);
        position=min;

        for(int i=min+1;i<=max;i++)
        {


            if(dane.lista.get(i)<minimum) {
                minimum=dane.lista.get(i);
                position=i;
                //System.out.println(min+" - "+max+" - i: "+i+", Minimum: "+minimum);

            }
        }
        //System.out.println(min+" - "+max+" - Minimum - po skonczonym watku w run(): "+minimum);

    }


}
