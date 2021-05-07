package Lab17_JavaEgzekutory_cz2.src.threads.executors;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

class ResultsReciever implements Runnable {
    @Override
    public void run() {
        Singleton singleton =Singleton.getInstance();

          try {
              while(true) {
                  Wynik wynik=singleton.take().get();
                  System.out.println(wynik);
              }
          } catch (InterruptedException e) {
              e.printStackTrace();
          } catch (ExecutionException e) {
              e.printStackTrace();
          }
      }
    }



