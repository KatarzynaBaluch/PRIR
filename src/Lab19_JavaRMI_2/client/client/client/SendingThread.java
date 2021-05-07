package Lab19_JavaRMI_2.client.client.client;

import rmi.komunikatorSimple.CommunicatorService;

import java.rmi.RemoteException;
import java.util.Scanner;

public class SendingThread {

    private CommunicatorService service;
    private String username;
    private String password;

    public SendingThread(CommunicatorService service, String username, String password) {
        this.service = service;
        this.username = username;
        this.password = password;
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

                    service.addMessage(username, password, destUser, message);
                }
            }
        }
        catch (RemoteException e) {
            System.err.println("Brak użytkownika docelowego");
        }
    }
}
