package es.alumnos.urjc.pmirag2016.tema3.exclusionmutua;

import java.util.concurrent.Semaphore;

public class ExclusionMutuaSemaforo {

    private static Semaphore semExclusionMutua;

    private static void procA() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            // Preprotocolo
            semExclusionMutua.acquire();    // Obtiene semáforo si hay (si no se bloquea)

            // Sección crítica
            System.out.println("Sección crítica1");
            System.out.println("Sección crítica2");

            // Postprotocolo
            semExclusionMutua.release();    // Liberamos semáforo cogido (crea uno si no se cogió)

            // Sección no crítica
            System.out.println("Sección no crítica1");
            System.out.println("Sección no crítica2");
        }
    }

    private static void procB() {
        new Thread(() -> {
            try {
                procA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        
        semExclusionMutua = new Semaphore(1);

        // procA()
        try {
            procA();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // procB()
        procB();
    }
}
