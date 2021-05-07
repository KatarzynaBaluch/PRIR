package Lab16_Java_ForkJoinPool.forkjoin;

import Lab16_Java_ForkJoinPool.forkjoin.Sort;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class PQuickSort implements Sort {

    @Override
    public void sort(int[] tab) {
        ForkJoinPool forkJoin = new ForkJoinPool();
        try {
            MyParallelTask task = new MyParallelTask(tab, 0, tab.length - 1);
            forkJoin.invoke(task);
        } finally {
            forkJoin.shutdown();
        }

    }

    static class MyParallelTask extends RecursiveAction {
        int[] tab;
        int start;
        int koniec;

        public MyParallelTask(int[] tab, int start, int koniec) {
            this.tab = tab;
            this.start = start;
            this.koniec = koniec;
        }

        @Override
        protected void compute() {
            int j = split(tab, start, koniec);
            MyParallelTask task_l = null;
            MyParallelTask task_r = null;

            if (start < j) {
                task_l = new MyParallelTask(tab, start, j - 1);
                task_l.fork();
            }
            if (j + 1 < koniec) {
                task_r = new MyParallelTask(tab, j + 1, koniec);
                task_r.fork();
            }

            if (task_l != null) {
                task_l.join();
            }

            if (task_r != null) {
                task_r.join();
            }
        }


        private int split(int[] tab, int start, int koniec) {
            int i = (start + koniec) / 2;
            int pivot = tab[i];
            swap(tab, i, koniec);
            int j = start;
            for (i = start; i < koniec; i++) {
                if (tab[i] < pivot) {
                    swap(tab, i, j);
                    j++;
                }
            }
            tab[koniec] = tab[j];
            tab[j] = pivot;
            return j;
        }

        private void swap(int[] tab, int i, int j) {
            int tmp = tab[i];
            tab[i] = tab[j];
            tab[j] = tmp;
        }
    }
}
