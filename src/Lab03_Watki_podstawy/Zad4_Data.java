package Lab03_Watki_podstawy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Zad4_Data {
    public LinkedList<Double> lista=new LinkedList<Double>();


    Zad4_Data() {
        File plik = new File("dane.txt");
        try (
                Scanner sc = new Scanner(plik)) {
            Double d;
            String s;
            Integer i = 0;

            while (sc.hasNext()) {
                i++;
                s = sc.next();
                d = Double.parseDouble(s.substring(s.indexOf(";") + 1));

                lista.add(d);
            }


        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
