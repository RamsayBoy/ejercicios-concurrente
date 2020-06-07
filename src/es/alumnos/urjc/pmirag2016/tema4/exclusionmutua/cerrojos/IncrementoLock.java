package es.alumnos.urjc.pmirag2016.tema4.exclusionmutua.cerrojos;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class IncrementoLock {

    private static double x = 0;
    private static Lock xLock = new ReentrantLock();    /* Con los objectos Lock podemos
                                                           elegir cuando se adquiere y
                                                           cuando se libera el cerrojo */

    public static void inc() {
        for (int i = 0; i < 1000_000; i++) {
            xLock.lock();
            // Buena decisión usar try/finally para liberar el cerrojo.
            // Tanto si se produce una excepción como si no.
            try {
                x = x + 1;
            } finally {
                xLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Thread thr1 = new Thread(IncrementoLock::inc);
        Thread thr2 = new Thread(IncrementoLock::inc);
        Thread thr3 = new Thread(IncrementoLock::inc);

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
