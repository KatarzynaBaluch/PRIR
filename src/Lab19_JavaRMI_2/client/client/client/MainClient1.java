package Lab19_JavaRMI_2.client.client.client;

public class MainClient1 {
    public static void main(String[] args) {
        Client client=new Client("Jacek", "jacek123");
        client.start();
    }
}
