package Lab16_Java_ForkJoinPool.forkjoin;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {



        int[] tab = {5, 7, 3, 2, 8, 0, 1, 6, 10};
        {
            System.out.println("------------------------------------");
            int[] tmpTab = Arrays.copyOf(tab, tab.length);
            System.out.println(Arrays.toString(tmpTab));
            Sort sort = new QuickSort();
            sort.sort(tmpTab);
            System.out.println(Arrays.toString(tmpTab));
        }
        {
            System.out.println("------------------------------------");
            int[] tmpTab = Arrays.copyOf(tab, tab.length);
            System.out.println(Arrays.toString(tmpTab));
            Sort sort = new PQuickSort();
            tmpTab=Arrays.copyOf(tab, tab.length);
            sort.sort(tmpTab);
            long t1=System.currentTimeMillis();
            sort.sort(tmpTab);
            long t2=System.currentTimeMillis();
            System.out.println(Arrays.toString(tmpTab));
        }

    }
}

