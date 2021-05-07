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
public class MagazynImpl implements Magazyn<String> {
    String produkt;
    Semaphore semProducent;
    Semaphore semKonsument;

    public MagazynImpl() {
        semProducent=new Semaphore(1);
        semKonsument=new Semaphore(0);
    }

    @Override
    public void add(String produkt) throws InterruptedException {
        semProducent.acquire();
       this.produkt=produkt;
       semKonsument.release();
    }

    @Override
    public String get() throws InterruptedException {
        String tmp;

        semKonsument.acquire();
        tmp=produkt;
        semProducent.release();


        return tmp;

            }
    
}
