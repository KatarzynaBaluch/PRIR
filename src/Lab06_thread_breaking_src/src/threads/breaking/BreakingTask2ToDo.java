package Lab06_thread_breaking_src.src.threads.breaking;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Zmodyfikuj metodę run w klasie BreakingTask2ToDo tak aby po wystąpieniu przerwaniu wątka, metoda run zakończyła swoją
 * pracę a jednocześnie wypisała na ekranie zawartość mapy
 */

public class BreakingTask2ToDo implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new BreakingTask2ToDo());
        t.start();
        sleep(500);
        t.interrupt();
    }


    @Override
    public void run() {
        Map<String, Integer> map = new HashMap<>();
        Random r = new Random();
        while (true) {
            String key = "" + r.nextInt(10);
            Integer val = map.get(key);
            val = val == null ? 1 : val + 1;
            map.put(key, val);

            //albo try wywalić przed pętlę, catcha za pętlę, wtedy nie trzeba break
            //gdyby było więcej możliwości wywalenia wyjątków to lepsza opja wywalenia tego poza while
            try {
                sleep(5);
            } catch (InterruptedException e) {
                System.out.println("Wyjątek");
                break;
            }
        }
        System.out.println("przerwano while\n"+map.toString());
    }
}

