package Lab17_JavaEgzekutory_cz2.src.threads.executors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        generateFiles();
        Thread taskProvider = new Thread(new TaskProvider());
        Thread resultsReciever = new Thread(new ResultsReciever());
        taskProvider.start();
        resultsReciever.start();
    }

    private static void generateFiles() {
        String[] files = {"plikA4.txt", "plikB4.txt", "plikC4.txt", "plikD4.txt", "plikE4.txt", "plikF4.txt", "plikG4.txt", "plikH4.txt"};
        for (String s : files) {
            try (PrintStream ps = new PrintStream(s);) {
                Random r = new Random();
                int iter = r.nextInt(10000);
                for (int i = 0; i < iter; i++) {
                    int val = r.nextInt(100);
                    ps.println(val);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}


