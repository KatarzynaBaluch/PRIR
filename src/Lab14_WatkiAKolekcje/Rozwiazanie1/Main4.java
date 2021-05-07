package Lab14_WatkiAKolekcje.Rozwiazanie1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Thread.sleep;

public class Main4 {
    public static void main(String[] args) throws InterruptedException {

        List<Integer> myList=new ArrayList<>();
        MyConsumentThread consument=new MyConsumentThread(myList);
        MyProducentThread producent=new MyProducentThread(myList);
        MySumThread sum=new MySumThread(myList);

        consument.start();
        producent.start();
        sum.start();

        sleep(2000);

        consument.interrupt();
        producent.interrupt();
        sum.interrupt();

    }

    private static class MyProducentThread extends Thread
    {
        List<Integer> myList;
        private int i=0;
        public MyProducentThread(List<Integer> myList)
        {
            this.myList=myList;
        }

        public void run()
        {
            while(true)
            {
                synchronized (myList) {
                    myList.add(i);
                }
                i++;
                //System.out.println(">> "+myList);
                if(this.isInterrupted())
                {
                    break;
                }
            }
        }

    }

    private static class MyConsumentThread extends Thread
    {
        List<Integer> myList;
        private int i=0;
        private int j=0;

        public MyConsumentThread(List<Integer> myList)
        {
            this.myList=myList;
        }

        public void run()
        {
            try{
                sleep(100);
                while (true)
                {
                    synchronized (myList) {
                        i = myList.remove(0);

                        System.out.println("i= " + i + "\tj= " + j);
                        assert j == i : "Blad asercji";

                    }
                    j++;

                    if(this.isInterrupted())
                    {
                        break;
                    }
                }
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static class MySumThread extends Thread
    {
        List<Integer> myList;

        public MySumThread(List<Integer> myList)
        {
            this.myList=myList;
        }

        public void run()
        {
            try {
                sleep(100);

                while(true)
                {
                    int s=0;
                    synchronized (myList) {
                        System.out.println("Size= "+myList.size());
                        for (int e : myList) {
                            s += e;
                        }
                        System.out.println("sum= " + s);

                    }
                    if(this.isInterrupted())
                    {
                        break;
                    }

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
