package es.alumnos.urjc.pmirag2016.tema3.sincrocondsemaforos;

import java.util.concurrent.Semaphore;

public class SincronizacionCondicionalSemaforo {

    private static Semaphore continuar;

    private static void procA() {
        new Thread(() -> {
            System.out.println("PA1");
            continuar.release();
            System.out.println("PA2");
        }, "Proceso A").start();
    }

    private static void procB() {
        new Thread(() -> {
            System.out.println("PB1");
            try {
                continuar.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("PB2");
        }, "Proceso B").start();
    }

    public static void main(String[] args) {

        continuar = new Semaphore(0);
        
        procA();
        procB();
    }
}
