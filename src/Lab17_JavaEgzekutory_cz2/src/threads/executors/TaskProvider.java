package Lab17_JavaEgzekutory_cz2.src.threads.executors;

class TaskProvider implements Runnable {

    @Override
    public void run() {
        String[] srcFiles = {"plikA4.txt", "plikB4.txt", "plikC4.txt", "plikD4.txt", "plikE4.txt", "plikF4.txt", "plikG4.txt", "plikH4.txt"};

        Singleton mySingleton=Singleton.getInstance();

        for(String s: srcFiles)
        {
            mySingleton.submit(new MyTask(s));
        }
    }
}
