package Lab13_CzytelnicyIPisarze.Zadanie3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MyThreadReader extends Thread {

    static int ids=0;
    ReadWriteLock lock;
    int id;
    String filename;

    public MyThreadReader(ReadWriteLock lock, String fileName) {
        this.lock=lock;
        this.id=ids;
        ids++;
        this.filename=fileName;
    }

    public void run()
    {
        try {
            lock.lockRead();
            try(Scanner sc=new Scanner(new File(filename)))
            {
                System.out.println("Wątek: "+id+" czyta");
                while (sc.hasNext())
                {
                    String s=sc.nextLine();
                    System.out.println("==> "+s);
                }
            }
            catch (FileNotFoundException e)
            {
                System.out.println("Brak pliku");
            }
            finally {
                lock.unlockRead();
            }
            Thread.sleep((long) Math.random()*100);
        }
        catch (InterruptedException ex)
        {
            System.out.println("Koniec wątku czytania: " + id);
        }
    }
}
