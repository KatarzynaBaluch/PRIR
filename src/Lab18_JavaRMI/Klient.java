package Lab18_JavaRMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Klient {
    public static void main(String[] args) {
       // if (System.getSecurityManager()==null)
       //     System.getSecurityManager(new SecurityManager());
        try
        {
            String name= "Moj Zdalny Przyklad";
            String name1="Kalkulator";
            Registry registry= LocateRegistry.getRegistry();

            JakisInterfejs zdalnyObiekt=(JakisInterfejs)registry.lookup(name);
            JakisInterfejs kalkulator=(JakisInterfejs)registry.lookup(name1);

            System.out.println("Przygotowano program do działania");
            zdalnyObiekt.wyslij("Hello serwer!");


            Scanner scan = new Scanner(System.in);
            System.out.println("Podaj liczbę, a=");
            double a = scan.nextDouble();

            System.out.println("Podaj liczbę, b=");
            double b = scan.nextDouble();

            System.out.println("Operacje na liczbach a="+a+", b="+b);
            System.out.println("Dodawanie a+b="+kalkulator.dodaj(a,b));
            System.out.println("Mnozenie a*b="+kalkulator.mnoz(a,b));
            System.out.println("Dzielenie a/b="+kalkulator.dziel(a,b));
            System.out.println("Odejmowanie a-b="+kalkulator.odejmij(a,b));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
