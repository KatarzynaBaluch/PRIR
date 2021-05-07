package Lab18_JavaRMI;

import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Serwer {


    public static void main(String[] args) {
        //if (System.getSecurityManager()==null)
       // {
       //     System.setSecurityManager(new RMISecurityManager());
      //  }
        try
        {
            String name="Moj Zdalny Przyklad";
            String name1="Kalkulator";

            JakisInterfejs implementacjaSerwera=new ImplementacjaInterfejsu();
            JakisInterfejs implementacjaSerwera1=new ImplementacjaInterfejsu();

            JakisInterfejs engine= (JakisInterfejs) UnicastRemoteObject.exportObject(implementacjaSerwera,0);
            JakisInterfejs engine1= (JakisInterfejs) UnicastRemoteObject.exportObject(implementacjaSerwera1,0);


            Registry registry= LocateRegistry.createRegistry(1099);

            registry.rebind(name, engine);
            registry.rebind(name1, engine1);

            System.out.println("Uruchomiono i podpięto zdalnyKalkulator");
        }
        catch (Exception e)
        {
            System.err.println("Wyjątek podczas włączania:");
            e.printStackTrace();
        }
    }
}
