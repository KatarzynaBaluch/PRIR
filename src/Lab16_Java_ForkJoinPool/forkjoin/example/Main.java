package Lab16_Java_ForkJoinPool.forkjoin.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Main {
    public static void main(String[] args) {
        int[] tab = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        ForkJoinPool forkJoin = null;
        try {
            forkJoin = new ForkJoinPool(); //Tworzymy instancję puli wątków
            RecursiveTaskExample1 task = new RecursiveTaskExample1(tab); //Tworzymy główne zadanie
            int wynik = forkJoin.invoke(task); //Dodajemy zadanie do rozwiązania i czekamy na wynik
            System.out.println(wynik);
        } finally {
            if (forkJoin!=null) forkJoin.shutdown();
        }
    }
}

class RecursiveTaskExample1 extends RecursiveTask<Integer> {
    int[] tab;

    public RecursiveTaskExample1(int[] tab) {
        this.tab = tab;
    }

    @Override
    protected Integer compute() {
        if (tab.length > 2){ //Obsługa gdy tablica jest zbyt duża aby wprost obliczyć wynik
            int polowa = tab.length/2;
            RecursiveTaskExample1 task1 = new RecursiveTaskExample1(Arrays.copyOfRange(tab,0,polowa)); //Tworzymy zadanie
            task1.fork(); //Uruchamiamy zadanie w nowym wątku
            RecursiveTaskExample1 task2 = new RecursiveTaskExample1(Arrays.copyOfRange(tab, polowa, tab.length)); //Tworzymy zadanie
            task2.fork(); //Uruchamiamy zadanie w nowym wątku
            //UWAGA: Bardziej efektywne jest czekanie w odwrotnej kolejności
            int res2 = task2.join(); //Czekamy na koniec
            int res1 = task1.join(); //Czekamy na koniec
            return res1+res2;
        } else { //Bezpośrednie obliczenie wyniku
            if (tab.length==1){
                return tab[0];
            } else {
                return tab[0] + tab[1];
            }
        }
    }
}


class RecursiveTaskExample2 extends RecursiveTask<Integer> {
    int[] tab;

    public RecursiveTaskExample2(int[] tab) {
        this.tab = tab;
    }

    @Override
    protected Integer compute() {
        if (tab.length > 2){ //Obsługa gdy tablica jest zbyt duża aby wprost obliczyć wynik
            int polowa = tab.length/2;
            List<RecursiveTaskExample2> list = new ArrayList<>();
            list.add( new RecursiveTaskExample2(Arrays.copyOfRange(tab,0,polowa)) ); //Tworzymy zadanie i dodajemy je do listy zadań
            list.add( new RecursiveTaskExample2(Arrays.copyOfRange(tab, polowa, tab.length)) ); //Tworzymy zadanie i dodajemy je do listy zadań
            Collection<RecursiveTaskExample2> results = this.invokeAll(list); //Uruchamiamy wszystkie zadania i czekamy na ich koniec
            int wynik = 0;
            for(RecursiveTaskExample2 taskRes : results){ //W pętli pobieramy wyniki z zadań, bo RecursiveTask i RecursiveAction są obiektami klasy Future
                   wynik += taskRes.join(); //Tutaj odczytujemy wynik, przy czym nie będziemy czekać bo wiemy że zadanie jest już skończone. Ponieważ RecursiveAction/RecursiveTask implementują  Future więc można tutaj użyć też polecenia get(), ale wóczas trzeba dorobić obsługę wyjątkó
            }
            return wynik;
        } else { //Bezpośrednie obliczenie wyniku
            if (tab.length==1){
                return tab[0];
            } else {
                return tab[0] + tab[1];
            }
        }
    }
}
