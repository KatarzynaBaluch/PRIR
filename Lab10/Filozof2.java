package Lab10_ProblemUcztujacychFilozofow;

import java.util.concurrent.Semaphore;

public class Filozof2 extends Thread {
    private Semaphore[] locks;
    private int filozofID;


    public Filozof2(int nr, Semaphore[] paleczki)
    {
    this.filozofID=nr;
    this.locks=paleczki;

    }

    public void run()
    {
        try
        {
            //filozof mysli
            System.out.println("Mysle "+this.filozofID);
            //idzie jesc
            if(filozofID==locks.length-1)
            {
                locks[(filozofID+1)%locks.length].acquireUninterruptibly();
                locks[filozofID].acquireUninterruptibly();
            }
            else
            {
                locks[filozofID].acquireUninterruptibly();
                locks[(filozofID+1)%locks.length].acquireUninterruptibly();
            }

            System.out.println("Zaczyna jesc "+this.filozofID);
            Thread.sleep((long)(5*Math.random()));
            System.out.println("Konczy jesc "+this.filozofID);
            locks[filozofID].release();
            if(filozofID==locks.length-1)
            {
                locks[(filozofID+1)%locks.length].release();
                locks[filozofID].release();
            }
            else
            {
                locks[filozofID].release();
                locks[(filozofID+1)%locks.length].release();
            }
        }

        catch (InterruptedException e){}
    }
}
