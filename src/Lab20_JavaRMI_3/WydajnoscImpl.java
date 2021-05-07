package Lab20_JavaRMI_3;

import org.apache.wss4j.common.util.UsernameTokenUtil;

import java.rmi.RemoteException;

public class WydajnoscImpl implements Wydajnosc
{
    private byte[] data_10B;
    private byte[] data_10KB;

    private float srednia = 0;

    public WydajnoscImpl() throws RemoteException
    {
        data_10B = get10B();
        data_10KB = get10KB();
    }

    @Override
    public byte[] get10B() throws RemoteException
    {
        byte[] tempData = new byte[10];

        for(byte i = 0; i < tempData.length; i++)
        {
            tempData[i] = i;
        }

        return tempData;
    }

    @Override
    public byte[] get10KB() throws RemoteException
    {
        byte[] tempData = new byte[10000];

        for(int i = 0; i < tempData.length; i++)
        {
            tempData[i] = (byte)i;
        }

        return tempData;
    }

    @Override
    public void setData(byte[] data) throws RemoteException
    {
        int suma = 0;

        for (int i = 0; i < data.length; i++)
        {
            suma += data[i];
        }

        srednia = suma / data.length;
    }
}
