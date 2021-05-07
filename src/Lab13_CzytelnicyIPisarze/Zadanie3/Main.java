package Lab13_CzytelnicyIPisarze.Zadanie3;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String fileName = "text,txt";
        ReadWriteLock lock = new ReadWriteLock();
        List<Thread> readers = Arrays.asList(
                new MyThreadReader(lock,fileName),
                new MyThreadReader(lock,fileName),
                new MyThreadReader(lock,fileName),
                new MyThreadReader(lock,fileName)
        );
        List<Thread> writers = Arrays.asList(
                new MyThreadWrite(lock, fileName),
                new MyThreadWrite(lock, fileName)
        );
        readers.stream().forEach((t)-> lock.registerReader(t));
        writers.stream().forEach((t)->t.start());
        readers.stream().forEach((t)->t.start());
        Thread.sleep(300);
        writers.stream().forEach(t->t.interrupt());
        readers.stream().forEach(t->t.interrupt());
    }
}
