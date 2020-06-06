package es.alumnos.urjc.pmirag2016.tema3.sincronizacionbarrera.ejemplos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/*
 * Creo que se puede hacer mejor el programa si se extrae el semExclusion.release();
 * aunque daría igual. Es cuestión de ser más perfeccionista (no sé si está bien).
 * -----------------------------------------------------------------------------
 * Programa con N procesos que escriban A y luego B.
 * Todos tienen que esperar a que se haya escrito A para poder escribir B.
 * Usa una barrera.
 * -----------------------------------------------------------------------------
 * NOTA: Esta implementación no permite usar una única barrera en un bucle ya que
 * se podría producir inanición.
 */
public class MainMejora {

    private static final int NUM_PROCS = 3;

    private static volatile int numProcesos;    /* Llevar la cuenta de procesos que
                                                   han realizado la primera parte
                                                   (escribir "A") */

    private static Semaphore semBarrera;        /* Semáforo que bloquea a todos los
                                                   semáforos que han realizado su primera
                                                   parte (escribir "A") para esperar
                                                   a que termine el resto de hacerlo y
                                                   escribir luego la B. */

    private static Semaphore semExclusion;      /* Semáforo que impide que más de un
                                                   proceso modifique numProcesos
                                                   (sección crítica) (creo). */

    private static void proc() throws InterruptedException {
        System.out.println("A");

        // Hacemos que solo un proceso pase por la sección crítica (creo).
        System.out.println(Thread.currentThread().getName() + " espero el semExclusion.");
        semExclusion.acquire();
        System.out.println(Thread.currentThread().getName() + " cojo el semExclusion.");

        // Incrementamos el número de procesos que han escrito la A (que han terminado
        // su trabajo) (creo).
        System.out.println(Thread.currentThread().getName() + " numProcesos++.");
        numProcesos++;

        // Si ya no hay más procesos.
        if (numProcesos == NUM_PROCS) {
            // Y para cada proceso que hay añade un permiso al semáforo barrera para
            // dejar pasar a todos los semáforos que quedan.
            for (int i = 0; i < NUM_PROCS; i++) {
                System.out.println(Thread.currentThread().getName() + " Añado 1 semBarrera.");
                semBarrera.release();
            }
        }

        // El proceso devuelve el permiso del semáforo que ha cogido.
        System.out.println(Thread.currentThread().getName() + " devuelvo el semExclusion.");
        semExclusion.release();

        // Espera a que hayan permisos disponibles en semáforo barrera
        // (ver bucle for dentro del if) para poder pasar.
        // (Pasarán todos a la vez, es lo que se quiere, así funciona el semBarrera).
        System.out.println(Thread.currentThread().getName() + " espero el semBarrera.");
        semBarrera.acquire();
        System.out.println(Thread.currentThread().getName() + " cojo el semBarrera.");

        // Todos a la vez escriben B una vez han escrito A
        System.out.println("B");
    }

    public static void main(String[] args) {

        numProcesos = 0;
        semBarrera = new Semaphore(0);
        semExclusion = new Semaphore(1);

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < NUM_PROCS; i++) {
            Thread thread = new Thread(() -> {
                try {
                    proc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Proceso: " + (i + 1));

            thread.start();
            threads.add(thread);
        }
    }
}
