/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab09_ProducentKonsumentSemafory.wieleu_producentow;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Marcin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Magazyn m = new MagazynImpl(3);
        List<Thread> th = new ArrayList<>();
        for (int i=0; i<10;i++) {
            Thread producent = new Producent(m);
            th.add(producent);
        }
        Thread konsument = new Consument(m);
        th.add(konsument);
        th.forEach(t->t.start());
        Thread.sleep(1 * 1000);
        th.forEach(t -> t.interrupt());        
    }
    
}
