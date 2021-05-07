package Lab17_JavaEgzekutory_cz2.src.threads.executors;

import java.util.concurrent.*;

public class Singleton {

    private static Singleton instance;

    private ExecutorService executor=Executors.newFixedThreadPool(10);

    public final CompletionService<Wynik> completionService=new ExecutorCompletionService<Wynik>(executor);

    private Singleton()
    {

    }

    synchronized public static Singleton getInstance()
    {
        if(instance==null)
            instance=new Singleton();

        return instance;
    }

    public void submit(Callable<Wynik> task)
    {
        completionService.submit(task);
    }

    public Future<Wynik> take() throws InterruptedException
    {
        return completionService.take();
    }
}
