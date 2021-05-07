package Lab13_CzytelnicyIPisarze;

public class CzyteniaC implements Czytelnia {

    private boolean pisarzPisze;
    private boolean pisarzCzeka;
    private boolean pisarzWlansnieCosWpisal;
    private int liczbaCzytelnikow;

    @Override
    public synchronized void chceCzytac() {
        try
        {
            while (pisarzPisze || (pisarzCzeka && !pisarzWlansnieCosWpisal))
            {
                wait();
            }
            liczbaCzytelnikow++;
            pisarzWlansnieCosWpisal=false;
        } catch (InterruptedException ex) {}
    }

    @Override
    public synchronized void koniecCzytania() {
        liczbaCzytelnikow--;
        notifyAll();
    }

    @Override
    public synchronized void chcePisac() {
        try
        {
            pisarzCzeka=true;
            while (liczbaCzytelnikow>0 || pisarzPisze)
            {
                wait();
            }
            pisarzCzeka=false;
            pisarzPisze=true;
        }
        catch (InterruptedException ex) {}

    }

    @Override
    public synchronized void koniecPisania() {
        pisarzPisze=false;
        pisarzWlansnieCosWpisal=true;
        notifyAll();
    }
}
