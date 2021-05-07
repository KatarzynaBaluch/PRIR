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
public class Producent extends Thread {

    Magazyn magazyn;

    public Producent(Magazyn m) {
        magazyn = m;
    }

    public void run() {
        int i = 0;
        try {
            while (true) {                
                String produkt = "" + i;                
                magazyn.add(produkt);
                System.out.println("Wrzuciłem do magazynu: " + produkt);
                i++;
                Thread.sleep((int) (Math.random() * 10));

            }
        } catch (InterruptedException e) {
            System.out.println("Koniec wątu Producenta");
        }
    }

}
