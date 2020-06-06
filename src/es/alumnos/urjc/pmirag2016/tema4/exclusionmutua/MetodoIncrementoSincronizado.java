package es.alumnos.urjc.pmirag2016.tema4.exclusionmutua;

public class MetodoIncrementoSincronizado {

    private static int x = 0;

    /*
     * Creo que de esta manera haría que, si hay dos hilos, uno lo ejecute primero
     * todo y luego el otro.
     */
    /*private synchronized static void inc() {
        for (int i = 0; i < 10000000; i++)
            x = x + 1;
    }*/

    /*
     * Y de esta manera los hilos lo hacen a la vez todo excepto cuando van a hacer
     * la suma.
     */
    private synchronized static void inc() {
        x = x + 1;
    }

    private static void inc1000_000() {
        for (int i = 0; i < 1000_000; i++)
            inc();
    }

    public static void main(String[] args) {
        Thread thr1 = new Thread(MetodoIncrementoSincronizado::inc1000_000);
        Thread thr2 = new Thread(MetodoIncrementoSincronizado::inc1000_000);

        thr1.start();
        thr2.start();

        try {
            thr1.join();
            thr2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("x: " + x);
    }
}
