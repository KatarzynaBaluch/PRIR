package Lab15_JavaEgzekutory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.*;

public class Main2 {
    public static void main(String[] args) {

        List<String> srcFiles =
                List.of("plikA.txt","plikB.txt","plikC.txt","plikD.txt","plikE.txt",
                        "plikF.txt","plikG.txt");

        List<String> dstFiles =
                List.of("plikADst.txt","plikBDst.txt","plikCDst.txt","plikDDst.txt",
                        "plikEDst.txt","plikFDst.txt","plikGDst.txt");

        //generateFiles(srcFiles);
        //System.out.println("Files - generated");

        ExecutorService es=null;
        try {
            Iterator<String> srcIterator = srcFiles.listIterator();
            List<Callable<Double>> tasks = new ArrayList<>();
            while (srcIterator.hasNext()) {
                String src = srcIterator.next();
                tasks.add(new MyTaskMax(src));
            }

            es = Executors.newFixedThreadPool(3);
            List<Future<Double>> res = es.invokeAll(tasks);
            double result = 0;
            for (Future<Double> future : res) {
                result += future.get();
            }


            System.out.println("Result: " + result);
        }
        catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }
        finally {
            if(es!=null) es.shutdown();
        }


        System.out.println("Finish!");
    }


    public static class MyTaskMax implements Callable<Double>
    {
        String name;

        public MyTaskMax(String name)
        {
            this.name=name;
        }

        @Override
        public Double call() throws Exception{

            double res=0;
            try (Scanner in =new Scanner(new File(name)))
            {
                while (in.hasNext())
                {
                    String s=in.nextLine();
                    double r=Double.parseDouble(s);
                    if(r>res)
                    {
                        res=r;
                    }

                }
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            return res;
        }

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
