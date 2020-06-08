package es.alumnos.urjc.pmirag2016.tema4.estructurasdedatossincronizadas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EjemploColeccionSincronizada {

    private static final int NUM_PROCESOS = 3;

    private static List<String> sharedList;

    private static void proceso(int numero) {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep((long) (Math.random() * 500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            sharedList.add("H" + numero + "_I" + i);
        }
    }

    public static void main(String[] args) {

        List<Thread> threads = new ArrayList<>();

        sharedList = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < NUM_PROCESOS; i++) {
            int finalI = i;
            Thread thr = new Thread(() -> proceso(finalI));
            
            thr.start();
            threads.add(thr);
        }

        for (int i = 0; i < NUM_PROCESOS; i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("List: " + sharedList);
    }
}
