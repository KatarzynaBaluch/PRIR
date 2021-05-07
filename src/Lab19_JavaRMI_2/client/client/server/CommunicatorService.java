package Lab19_JavaRMI_2.client.client.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CommunicatorService extends Remote {
    void registerUser(String userName, String pass) throws RemoteException;

    void addMessage(String srcUser, String pass, String dstUser, String message)
            throws RemoteException;

    String getMessage(String user, String pass) throws RemoteException;

    List<String> getUsers() throws RemoteException;
}
