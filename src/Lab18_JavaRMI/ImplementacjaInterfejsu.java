package Lab18_JavaRMI;

import java.rmi.RemoteException;

public class ImplementacjaInterfejsu implements JakisInterfejs{


    @Override
    public void wyslij(String s) throws RemoteException {
        System.out.println("Odebrałem: "+s);
    }

    @Override
    public double dodaj(double a, double b) throws RemoteException
    {
        System.out.println("Dodaję a+b="+a+"+"+b);
        return a+b;
    }

    @Override
    public double mnoz(double a, double b) throws RemoteException
    {
        System.out.println("Mnożę a*b="+a+"*"+b);
        return a*b;
    }

    @Override
    public double dziel(double a, double b) throws RemoteException
    {
        System.out.println("Dzielę a/b="+a+"/"+b);
        if (b!=0.0)
        {
            return a/b;
        }
        return 0.0;
    }

    @Override
    public double odejmij(double a, double b) throws RemoteException
    {
        System.out.println("Odejmuję a-b="+a+"-"+b);
        return a-b;
    }

    @Override
    public void policz(IZadanie t) throws RemoteException {

    }

    @Override
    public Object wynikObliczen() throws RemoteException {
        return null;
    }
}
