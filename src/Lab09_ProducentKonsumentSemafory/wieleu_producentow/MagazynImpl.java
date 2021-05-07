
package Lab09_ProducentKonsumentSemafory.wieleu_producentow;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Marcin
 */
public class MagazynImpl implements Magazyn<String> {
    String[] produkt;
    int size;
    Semaphore semProducent;
    Semaphore semKonsument;
    Semaphore semAdd=new Semaphore(1);
    Semaphore semGet=new Semaphore(1);
    int licznikZapis=0;
    int licznikOdczyt=0;

    public MagazynImpl(int size) {
        semProducent=new Semaphore(size);
        semKonsument=new Semaphore(0);
        produkt=new String[size];
        this.size=size;
    }

    @Override
    public void add(String produkt) throws InterruptedException {
        semProducent.acquire();

        //synchronized (this) {
        semAdd.acquire();
            this.produkt[licznikZapis] = produkt;
            licznikZapis++;
            licznikZapis = licznikZapis % size;
        semAdd.release();
        //} semafory albo sekcja synchronized, ale synchronized nie działa "całkowiecie"
        semKonsument.release();
    }

    @Override
    public String get() throws InterruptedException {
        String tmp;

        semKonsument.acquire();
        //synchronized (this) {
        semGet.acquire();
            tmp = produkt[licznikOdczyt];
            licznikOdczyt++;
            licznikOdczyt = licznikOdczyt % size;
        //}
        semGet.release();
        semProducent.release();
        return tmp;

    }

}

