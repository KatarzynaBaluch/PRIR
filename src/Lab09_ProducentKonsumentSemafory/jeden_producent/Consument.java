/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab09_ProducentKonsumentSemafory.jeden_producent;

/**
 *
 * @author Marcin
 */
public class Consument extends Thread {

    Magazyn<String> magazyn;

    public Consument(Magazyn<String> m) {
        this.magazyn = m;
    }

    public void run() {
        int i = 0;
        try {
            while (true) {
                String produkt = magazyn.get();
                System.out.println("Odczytałem z magazynu: " + produkt);
                int  j = Integer.parseInt(produkt);
                System.out.println(i + "  " + j);                
                if (i != j) {
                    throw new RuntimeException("Błąd, I i J nie są sobie równe");
                }
                i++;
                Thread.sleep((int) (Math.random() * 50));
            }
        } catch (InterruptedException e) {
            System.out.println("Koniec wątku konsumenta");
        }
    }
}
