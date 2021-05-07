/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab09_ProducentKonsumentSemafory.jeden_producent;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Marcin
 */
public class MagazynImpl2 implements Magazyn<String> {
    String[] produkt;
    int size;
    Semaphore semProducent;
    Semaphore semKonsument;
    int licznikZapis=0;
    int licznikOdczyt=0;

    public MagazynImpl2(int size) {
        semProducent=new Semaphore(size);
        semKonsument=new Semaphore(0);
        produkt=new String[size];
        this.size=size;
    }

    @Override
    public void add(String produkt) throws InterruptedException {
            semProducent.acquire();
            this.produkt[licznikZapis] = produkt;
            licznikZapis++;
            licznikZapis=licznikZapis%size;
            semKonsument.release();

    }

    @Override
    public String get() throws InterruptedException {
        String tmp;


            semKonsument.acquire();
            tmp = produkt[licznikOdczyt];
            licznikOdczyt++;
            licznikOdczyt=licznikOdczyt%size;
            semProducent.release();
            return tmp;

        }

            }
    
