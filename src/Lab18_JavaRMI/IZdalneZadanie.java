package Lab18_JavaRMI;

import java.io.ObjectOutputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IZdalneZadanie extends Remote {

    public void policz(IZadanie zadanie) throws RemoteException;
    public Object wynikObliczen() throws RemoteException;
}
