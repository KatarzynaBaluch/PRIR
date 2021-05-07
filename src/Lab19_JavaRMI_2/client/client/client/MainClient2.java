package Lab19_JavaRMI_2.client.client.client;

public class MainClient2 {
    public static void main(String[] args) {
        Client client=new Client("Placek", "placek123");
        client.start();
    }
}
