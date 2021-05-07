package Lab18_JavaRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface JakisInterfejs extends Remote {
    public void wyslij(String s) throws RemoteException;
    public double dodaj(double a, double b) throws RemoteException;
    public double mnoz(double a, double b) throws RemoteException;
    public double dziel(double a, double b) throws RemoteException;
    public double odejmij(double a, double b) throws RemoteException;
   void policz(IZadanie t) throws RemoteException;
   Object wynikObliczen() throws RemoteException;
}
