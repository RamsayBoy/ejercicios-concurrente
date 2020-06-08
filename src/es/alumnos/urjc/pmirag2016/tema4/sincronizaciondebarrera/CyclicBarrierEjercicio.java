package es.alumnos.urjc.pmirag2016.tema4.sincronizaciondebarrera;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierEjercicio {

    private static final int NUM_PROCESOS = 3;

    private static CyclicBarrier barrera;

    private static void proc() {
        System.out.println("A");
        try {
            barrera.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        barrera = new CyclicBarrier(NUM_PROCESOS, () -> {
            System.out.println("*");
        });

        Thread thr = new Thread(CyclicBarrierEjercicio::proc);
        Thread thr2 = new Thread(CyclicBarrierEjercicio::proc);
        Thread thr3 = new Thread(CyclicBarrierEjercicio::proc);

        thr.start();
        thr2.start();
        thr3.start();
    }
}
