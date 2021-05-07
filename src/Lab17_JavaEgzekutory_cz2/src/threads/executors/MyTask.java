package Lab17_JavaEgzekutory_cz2.src.threads.executors;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;

public class MyTask implements Callable<Wynik> {

    String nazwa_pliku;
    ArrayList<Wynik> wyniki = new ArrayList<Wynik>();

    public MyTask(String s) {
        this.nazwa_pliku=s;
    }


    @Override
    public Wynik call() throws Exception {
        boolean newOne=true;
        try(BufferedReader br=new BufferedReader(new FileReader((nazwa_pliku))))
        {
            String slowo=br.readLine();
            while(slowo!=null)
            {
                newOne=true;
                Iterator<Wynik> iterator=wyniki.iterator();
                while (iterator.hasNext())
                {
                    Wynik singleWynik=iterator.next();
                    if(singleWynik.getWyraz().equals(slowo))
                    {
                        singleWynik.setLicznik(singleWynik.getLicznik()+1);
                        newOne=false;
                        break;
                    }
                }
                if(newOne)
                {
                    wyniki.add(new Wynik(nazwa_pliku,slowo,1));
                }
                slowo=br.readLine();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        Wynik zwracany=new Wynik("", "", 0);
        Iterator<Wynik> iterator=wyniki.iterator();
        while(iterator.hasNext())
        {
            Wynik singleWynik=iterator.next();
            if(singleWynik.getLicznik()>zwracany.getLicznik())
            {
                zwracany=singleWynik;
            }
        }
        return zwracany;
    }
}
