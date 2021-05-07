package Lab19_JavaRMI_2.client.client.client;

import rmi.komunikatorSimple.CommunicatorService;

import java.rmi.RemoteException;

public class ReceivingThread extends Thread{

    private CommunicatorService service;
    private String username;
    private String password;

    public ReceivingThread(CommunicatorService service, String username, String password) {
        this.service = service;
        this.username = username;
        this.password = password;
    }

    public void run()
    {
        try {
        while (true)
        {

                String messageToParse=service.getMessage(username, password);
                if(messageToParse!=null)
                {
                    String[] userMessage=messageToParse.split(";");

                    if(userMessage.length==2)
                    {
                        String user=userMessage[0];
                        String message=userMessage[1];
                        System.out.println("["+user+"]"+message);
                    }
                    else System.out.println(messageToParse);
                }
            }
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
