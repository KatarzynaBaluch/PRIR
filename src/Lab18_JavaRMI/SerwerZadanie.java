package Lab18_JavaRMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class SerwerZadanie {


    public static void main(String[] args) {
        //if (System.getSecurityManager()==null)
       // {
       //     System.setSecurityManager(new RMISecurityManager());
      //  }
        try
        {
            String name="Zdalne Zadanie";

            IZdalneZadanie implementacjaSerwera=new ZdalneZadanie();

            IZdalneZadanie engine= (IZdalneZadanie) UnicastRemoteObject.exportObject(implementacjaSerwera,0);

            Registry registry= LocateRegistry.createRegistry(1099);

            registry.rebind(name, engine);

            System.out.println("Uruchomiono i podpięto Zadanie");
        }
        catch (Exception e)
        {
            System.err.println("Wyjątek podczas włączania:");
            e.printStackTrace();
        }
    }
}
