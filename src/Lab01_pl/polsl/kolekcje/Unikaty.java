package Lab01_pl.polsl.kolekcje;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

public class Unikaty {
    public static void main(String[] args){

        File plik = new File("baza.txt");

        try (Scanner in=new Scanner(plik); PrintWriter pw=new PrintWriter("wyniki.txt")) {

            Collection<String> list = new HashSet<>();
           int i=0;
            while(in.hasNext()) {
                String s = in.next();
                if (i % 3 == 1) {
                    System.out.println(s);
                    list.add(s);
                }
                i++;
            }
            System.out.println("________________");
            for(String s: list) {
                System.out.println(s);
            pw.println(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}


