package Lab10_ProblemUcztujacychFilozofow;

import java.util.concurrent.Semaphore;

public class Filozof extends Thread {
    private Semaphore[] locks;
    private int filozofID;
    private Semaphore extraLock;

    public Filozof(int nr, Semaphore[] paleczki, Semaphore stol)
    {
    this.filozofID=nr;
    this.locks=paleczki;
    this.extraLock=stol;
    }

    public void run()
    {
        try
        {
            //filozof mysli
            System.out.println("Mysle "+this.filozofID);
            //idzie jesc
            extraLock.acquireUninterruptibly();
            locks[filozofID].acquireUninterruptibly();
            locks[(filozofID+1)%locks.length].acquireUninterruptibly();

            System.out.println("Zaczyna jesc "+this.filozofID);
            Thread.sleep((long)(5*Math.random()));
            System.out.println("Konczy jesc "+this.filozofID);
            locks[filozofID].release();
            locks[(filozofID+1)%locks.length].release();
            extraLock.release();

        }

        catch (InterruptedException e){}
    }
}
