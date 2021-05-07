package Lab01_pl.polsl.kolekcje;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Histogram {
    public static void main(String[] args) {
        File plik=new File("tekst.txt");
        Map<String, Integer> map=new TreeMap<>();
        try (Scanner sc=new Scanner(plik)) {
            String s;

            while (sc.hasNext())
            {
                s=sc.next();
                s=s.toLowerCase();
                if(map.containsKey(s)) {
                    int i=map.get(s);
                    i++;
                    map.put(s, i);
                }
                else  map.put(s, 1);
            }


        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Set<String> set=map.keySet();
        int i=0;
        for (String s: set)
        {
            System.out.println(s +": "+map.get(s));
            i++;
            if(i>10) break;

        }

    }
}
