package Lab15_JavaEgzekutory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main1 {
    public static void main(String[] args) {

        List<String> srcFiles =
                List.of("plikA.txt","plikB.txt","plikC.txt","plikD.txt","plikE.txt",
                        "plikF.txt","plikG.txt");

        List<String> dstFiles =
                List.of("plikADst.txt","plikBDst.txt","plikCDst.txt","plikDDst.txt",
                        "plikEDst.txt","plikFDst.txt","plikGDst.txt");

        //generateFiles(srcFiles);
        //System.out.println("Files - generated");

        Iterator<String> srcIterator=srcFiles.listIterator();
        Iterator<String> dstIterator=dstFiles.listIterator();



        int n=srcFiles.size();

        ExecutorService es=null;
        long time1=System.currentTimeMillis();
        try {
            es=Executors.newFixedThreadPool(n);

            while(srcIterator.hasNext() && dstIterator.hasNext()) {
                es.submit(new MyTaskCopy(srcIterator.next(), dstIterator.next()));
            }

        }
        finally {
            if(es!=null) {
                es.shutdown();
            }
        }

        long time2=System.currentTimeMillis();
        System.out.println("Czas trwania dla "+n+" wątków: "+(time2-time1));


        //Thread myTaskCopy=new Thread(new MyTaskCopy("plikA.txt", "kopiaaaa.txt"));
        //myTaskCopy.start();

        System.out.println("Finish!");
    }

    public static class MyTaskCopy implements Runnable
    {
        String srcName;
        String copyName;

        public MyTaskCopy(String srcName, String copyName)
        {
            this.srcName=srcName;
            this.copyName=copyName;
        }


        @Override
        public void run() {

            try(PrintWriter pw = new PrintWriter(copyName); Scanner in=new Scanner(new File(srcName));) {

                while(in.hasNext())
                {
                    pw.println(in.nextLine());
                }

                System.out.println(srcName+">>"+copyName+" - copied!, Thread: "+Thread.currentThread().getId());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    public static void generateFiles(List<String> files){
        for(String s : files){
            try(PrintStream ps = new PrintStream(s);) {
                Random r = new Random();
                for (int i = 0; i < 100000; i++) {
                    double val = r.nextDouble() * 100;
                    ps.println(val);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
