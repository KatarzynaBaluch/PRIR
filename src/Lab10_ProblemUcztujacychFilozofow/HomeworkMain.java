package Lab10_ProblemUcztujacychFilozofow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class HomeworkMain {
    public static void main(String[] args) throws InterruptedException {

        List<Thread> threads = new ArrayList<>();
        int n = 10;
        for (int i = 0; i < n; i++) {
            threads.add(new FilozofHomework(i));
        }
        threads.forEach(t -> t.start());
        Thread.sleep(100);
        threads.forEach(t -> t.interrupt());
    }


}
