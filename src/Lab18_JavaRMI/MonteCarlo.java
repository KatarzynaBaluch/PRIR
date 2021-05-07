package Lab18_JavaRMI;

import java.io.Serializable;

public class MonteCarlo implements IZadanie, Serializable {

    private double wynik;
    private int il_prob;

    public MonteCarlo(int il_prob) {
        this.il_prob = il_prob;
    }

    @Override
    public void licz() {
        int zawarte=0;
        for(int i=0;i<il_prob;i++)
        {
            double x, y;
            x=Math.random();
            y=Math.random();
            if(x*x+y*y<=1) zawarte++;
        }
        wynik=(4*((double)zawarte/il_prob));
    }

    @Override
    public Object getWynik() {
        return wynik;
    }
}
