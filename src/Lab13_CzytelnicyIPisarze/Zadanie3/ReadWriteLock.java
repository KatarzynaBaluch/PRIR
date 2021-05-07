package Lab13_CzytelnicyIPisarze.Zadanie3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadWriteLock {

    Map<Long, Boolean> readers=new HashMap<>();
    boolean write=false;


    public void registerReader(Thread t) {
        readers.put(t.getId(), false);
    }

    public void  unregisterReader(Thread t){
        readers.remove(t.getId());
    }


    // jeśli ktokolwiek czyta zwraca true, jeśli nikt nie czyta zwraca false
    private boolean isSomeoneReading()
    {
        for (boolean x: readers.values()) {
            if(x==true) return true;
        }
        return false;
    }

    //jeśli wcześniej pisał, to teraz kończy pisać (ustawia write na false) i ustawia czytelników, żeby czytali na true
    public synchronized void unlockWrite() {
        try {
            while (write == false) {
                wait();
            }
            write = false;
            for (Long k: readers.keySet()) {
                readers.replace(k, true);
            }
        }
        catch (InterruptedException e) {}
    }

    //jeśli ktoś pisze, albo ktoś czyta - czeka
    // w innym wypadku zaczyna pisać (ustawia write na true)
    public synchronized void lockWrite() {

        try {
            while (write == true || isSomeoneReading()==true) {
                wait();
            }
            write = true;
        }
        catch (InterruptedException e) {}
    }

    //jeśli czytelnik nie czyta, albo pisarz pisze - czekaj,
    //w innym wypadku kończy czytać
    public synchronized void unlockRead() {
        try {
        while(readers.get(Thread.currentThread().getId())==false || write==true)
        {
            wait();
        }
            readers.replace(Thread.currentThread().getId(),false);
        }
        catch (InterruptedException e) {}
    }

    //jeśli pisarz pisze lub czytelnik nie może czytać - czeka
    public synchronized void lockRead() {

        try {
            while(write==true || readers.get(Thread.currentThread().getId())==false)
            {
                wait();
            }
        }
        catch (InterruptedException e) {}
    }

    public long getId(){
        return Thread.currentThread().getId();
    }
}
