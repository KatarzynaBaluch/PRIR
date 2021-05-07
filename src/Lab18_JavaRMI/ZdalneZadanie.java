package Lab18_JavaRMI;

import java.rmi.RemoteException;

public class ZdalneZadanie implements IZdalneZadanie{

    IZadanie zad=null;
    @Override
    public void policz(IZadanie zadanie) throws RemoteException {
        this.zad=zadanie;
        zadanie.licz();
    }

    @Override
    public Object wynikObliczen() throws RemoteException {
       if(zad==null) return 0;
       return zad.getWynik();
    }
}
