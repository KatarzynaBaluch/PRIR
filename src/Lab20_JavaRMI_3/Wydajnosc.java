package Lab20_JavaRMI_3;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Wydajnosc extends Remote
{
    byte[] get10B() throws RemoteException;
    byte[] get10KB() throws RemoteException;

    void setData(byte[] data) throws RemoteException;
}
