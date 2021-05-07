package Lab18_JavaRMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class KlientZadanie {

    public static void main(String[] args) {
        // if (System.getSecurityManager()==null)
        //     System.getSecurityManager(new SecurityManager());
        try
        {
            String name= "Zdalne Zadanie";

            //MonteCarlo mc=new MonteCarlo(10000);

            Registry registry= LocateRegistry.getRegistry();

            IZdalneZadanie zad=(IZdalneZadanie) registry.lookup(name);
            System.out.println("Przygotowano program do działania");
            zad.policz(new MonteCarlo(100000));
            System.out.println("Pi: "+zad.wynikObliczen());



        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}

