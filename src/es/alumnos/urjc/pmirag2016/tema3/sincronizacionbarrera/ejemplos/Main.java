package es.alumnos.urjc.pmirag2016.tema3.sincronizacionbarrera.ejemplos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/*
 * Programa con N procesos que escriban A y luego B.
 * Todos tienen que esperar a que se haya escrito A para poder escribir B.
 * Usa una barrera.
 * -----------------------------------------------------------------------------
 * NOTA: Esta implementación no permite usar una única barrera en un bucle ya que
 * se podría producir inanición.
 */
public class Main {

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
        semExclusion.acquire();

        // Incrementamos el número de procesos que han escrito la A (que han terminado
        // su trabajo) (creo).
        numProcesos++;

        // Si ya no hay más procesos.
        if (numProcesos == NUM_PROCS) {
            // El proceso devuelve el permiso del semáforo que ha cogido.
            semExclusion.release();

            // Y para cada proceso que hay añade un permiso al semáforo barrera para
            // dejar pasar a todos los semáforos que quedan.
            for (int i = 0; i < NUM_PROCS; i++) {
                semBarrera.release();
            }
        }
        // El proceso devuelve el permiso del semáforo que ha cogido.
        semExclusion.release();

        // Espera a que hayan permisos disponibles en semáforo barrera
        // (ver bucle for dentro del if) para poder pasar.
        // (Pasarán todos a la vez, es lo que se quiere, así funciona el semBarrera).
        semBarrera.acquire();

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
