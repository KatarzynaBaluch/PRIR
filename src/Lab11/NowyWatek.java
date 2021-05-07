
package Lab11;

public class NowyWatek extends Thread {
    final int id;
    static boolean[] blokady = new boolean[10];

    public NowyWatek(int id){ this.id = id;}

    public void run() {
        try{
            while(true) {
                synchronized (blokady) {
                    while (blokady[id]) {
                        blokady.wait();
                    }
                }
                System.out.println(id);

                synchronized (blokady) {

                    blokady[id] = true;

                    if (id <= 4) { //grupa A
                        if (blokady[0] && blokady[1] && blokady[2] && blokady[3] && blokady[4]) {
                            for (int i = 5; i < 10; i++) blokady[i] = false;
                        }
                    }

                    if (id >= 5) { //grupa B
                        if (blokady[5] && blokady[6] && blokady[7] && blokady[8] && blokady[9]) {
                            for (int i = 0; i <= 4; i++) blokady[i] = false;
                        }
                    }
                    blokady.notifyAll();
                }
            }
        }catch (InterruptedException e){
            System.out.println("Koniec watku:"+ id);
        }
    }
}
