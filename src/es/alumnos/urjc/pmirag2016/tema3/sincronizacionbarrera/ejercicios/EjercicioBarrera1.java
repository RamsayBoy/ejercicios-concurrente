package es.alumnos.urjc.pmirag2016.tema3.sincronizacionbarrera.ejercicios;

import java.util.concurrent.Semaphore;

/*
 * Se desea implementar un bucle en el que todos los procesos muestran A y se esperan.
 * El ultimo proceso muestra * y desbloquea a los demás.
 *
 * Si se necesita usar una única barrera en un bucle, debe tenerse control de los
 * desbloqueos de los procesos.
 *
 * -----------------------------------------------------------------------------
 * La solución del profe tenía más movida.
 * Por lo visto me funciona, en principio, como debe ser pero si el suyo es diferente
 * por algo será.
 */
public class EjercicioBarrera1 {

    private static final int NUM_PROCESOS = 3;

    private static int numProcesos;
    private static Semaphore semBarrera;
    private static Semaphore semNProcesos;

    private static void proc() {
        System.out.println(Thread.currentThread().getName() + ": A");

        try {
            semNProcesos.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        numProcesos++;

        semNProcesos.release();

        if (numProcesos == NUM_PROCESOS) {
            // El último es el único que imprime "*"
            System.out.println(Thread.currentThread().getName() + ": *");

            for (int i = 0; i < NUM_PROCESOS; i++) {
                semBarrera.release();
            }
        }

        try {
            semBarrera.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + ": He terminado.");
    }

    public static void main(String[] args) {
        numProcesos = 0;
        semBarrera = new Semaphore(0);
        semNProcesos = new Semaphore(1);

        for (int i = 0; i < NUM_PROCESOS; i++) {
            Thread thr = new Thread(EjercicioBarrera1::proc, "Proc" + (i + 1));
            thr.start();
        }
    }
}
