package Lab20_JavaRMI_3;

import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.frontend.ServerFactoryBean;

class KalkulatorImpl implements Kalkulator
{
    @Override
    public double dodaj(double a, double b)
    {
        return a + b;
    }

    @Override
    public double odejmij(double a, double b)
    {
        return a - b;
    }

    @Override
    public double mnoz(double a, double b)
    {
        return a * b;
    }

    @Override
    public double dziel(double a, double b)
    {
        return b > 0 ? a/b : Double.NaN;
    }
}

class Serwer
{
    public static void main(String[] args) throws InterruptedException
    {
        Kalkulator kalkulator = new KalkulatorImpl();

        ServerFactoryBean factory = new ServerFactoryBean();
        factory.setServiceClass(Kalkulator.class);
        factory.setAddress("http://localhost:5000/Kalkulator");
        factory.setServiceBean(kalkulator);
        factory.create();
    }
}

class Client
{
    public static void main(String[] args)
    {
        ClientProxyFactoryBean clientFactory = new ClientProxyFactoryBean();
        clientFactory.setAddress("http://localhost:5000/Kalkulator");

        Kalkulator kalkulator = clientFactory.create(Kalkulator.class);

        int a=1, b=2;

        System.out.println("Obliczenia dla a="+a+" i b="+b);
        System.out.println(kalkulator.dodaj(a, b));
        System.out.println(kalkulator.odejmij(a,b));
        System.out.println(kalkulator.mnoz(a, b));
        System.out.println(kalkulator.dziel(a,b));
    }
}