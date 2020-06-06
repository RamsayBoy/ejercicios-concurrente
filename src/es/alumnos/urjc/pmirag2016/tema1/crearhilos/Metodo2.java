package es.alumnos.urjc.pmirag2016.tema1.crearhilos;

// Method 2: Haciendo que un objeto implemente la interfaz Runnable
// Cualquier clase cuya instancia necesite ser ejecutada por un hilo necesita
// debe implementar la interfaz Runnable (la clase Thread lo implementa)
public class Metodo2 {

    public static void main(String[] args) {
        System.out.println("--- Method 2 ---");

        System.out.println("Creating a runnable...");
        Runnable runnable = new RunnableExample();

        System.out.println("Creating a thread...");
        Thread thr = new Thread(runnable);

        System.out.println("Starting a thread...");
        thr.start();
    }

    static class RunnableExample implements Runnable {

        @Override
        public void run() {
            System.out.println("Thread: " + Thread.currentThread().getName());
        }
    }
}
