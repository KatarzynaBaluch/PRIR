package Lab19_JavaRMI_2.client.client.client;

import rmi.komunikatorSimple.CommunicatorService;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class Client extends Thread{
    CommunicatorService service;
    String user;
    String pass;
    private int port=4444;
    private String serviceName="komunikator";

    public Client(String user, String pass) {
        this.user = user;
        this.pass = pass;

    }

    public void start()
    {
        try{
            Registry rmiRegistry= LocateRegistry.getRegistry(port);
            service=(CommunicatorService) rmiRegistry.lookup(serviceName);

            System.out.println("Communicator service found");

            System.out.println("Trying to register user ...");
            System.out.println(">>>Username: "+user);
            System.out.println(">>>Password: "+pass);

            service.registerUser(user, pass);
            System.out.println("User registered");

            List<String> userList=service.getUsers();
            System.out.println("Avaliable users ("+userList.size()+")");
            System.out.println(userList);

            ReceivingThread receivingThread=new ReceivingThread(service, user, pass);
            receivingThread.start();
            startSending();



        }
        catch(Exception e)
        {
            System.err.println("Error!");
            e.printStackTrace();
        }
    }


    private void startSending() {
        System.out.println("Wpisz [użytkownik;wiadomość]: ");
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                String messageToParse = sc.nextLine();
                String[] userMessage = messageToParse.split(";");
                if (userMessage.length == 2) {
                    String destUser = userMessage[0];
                    String message = userMessage[1];

                    service.addMessage(user, pass, destUser, message);
                }
            }
        }
        catch (RemoteException e) {
            System.err.println("Brak użytkownika docelowego");
        }
    }
}
