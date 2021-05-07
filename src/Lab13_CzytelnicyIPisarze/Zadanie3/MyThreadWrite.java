package Lab13_CzytelnicyIPisarze.Zadanie3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class MyThreadWrite extends Thread {
    ReadWriteLock lock;
    static int ids=0;
    static int msgId=0;
    int id;
    String fileName;

    public MyThreadWrite(ReadWriteLock lock, String fileName) {
        this.lock=lock;
        this.id=ids;
        ids++;
        this.fileName=fileName;
    }

    public void run()
    {
        try
        {
            while (true)
            {
                lock.lockWrite();
                System.out.println("Watek: "+id+" pisarza");
                try(PrintStream ps=new PrintStream(fileName))
                {
                    String mess="Wiadomość od ("+id+"): "+msgId++;
                    ps.println(mess);
                    System.out.println("<=="+mess);
                }
                catch (FileNotFoundException e)
                {
                    System.out.println("Brak pliku");
                }
                finally {
                    lock.unlockWrite();
                }
                Thread.sleep((long)Math.random()*100);
            }
        }
        catch(InterruptedException ex)
        {
            System.out.println("Koniec wątku pisarza: "+id);
        }
    }
}
