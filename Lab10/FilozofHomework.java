package Lab10_ProblemUcztujacychFilozofow;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class FilozofHomework extends Thread {

    static int N=5; //ile plikow do utworzenia
    int id;
    static File[] files = new File[N];
    static String[] fileNames = new String[N];

    static {
        for (int i = 0; i < 5; i++) {
            fileNames[i] = "plik" + (i) + ".txt";
            files[i] = new File(fileNames[i]);
            String tekst = "start " + (i) + "\n";
            try {
                Files.writeString(Paths.get(fileNames[i]), tekst, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException ioException) {

            }
        }
    }

    public FilozofHomework(int id) {
        this.id = id;
    }

    public int los() {
        return ((int) (Math.random() * 10)) % N;
    }

    public void run() {

        while(true) {
            int losOdczyt = los();
            int losZapis = los();
            String toWrite = "";

            try {

                synchronized (files[losOdczyt]) {
                    toWrite = "wątek: " + id + ": " + Files.readString(Paths.get(fileNames[losOdczyt]));
                    Thread.sleep((long) (5 * Math.random()));
                }

                synchronized (files[losZapis]) {
                    Files.writeString(Paths.get(fileNames[losZapis]), toWrite, StandardOpenOption.APPEND);
                    Thread.sleep((long) (5 * Math.random()));
                }

            } catch (IOException | InterruptedException ioException) {
                break;
            }

        }

    }



}

