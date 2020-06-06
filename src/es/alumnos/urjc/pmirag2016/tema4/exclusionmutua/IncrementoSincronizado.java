package es.alumnos.urjc.pmirag2016.tema4.exclusionmutua;

public class IncrementoSincronizado {

    private static int x = 0;
    private static Object xLock = new Object(); /* El cerrojo puede ser un objeto
                                                   de cualquier clase. */

    private static void inc() {
        for (int i = 0; i < 1000000; i++) {
            // En la sentencia sincronizada se indica el cerrojo
            synchronized (xLock) {
                x = x + 1;
            }
        }
    }

    public static void main(String[] args) {
        Thread thr1 = new Thread(IncrementoSincronizado::inc);
        Thread thr2 = new Thread(IncrementoSincronizado::inc);
        Thread thr3 = new Thread(IncrementoSincronizado::inc);

        thr1.start();
        thr2.start();
        thr3.start();

        try {
            thr1.join();
            thr2.join();
            thr3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("x: " + x);
    }
}
