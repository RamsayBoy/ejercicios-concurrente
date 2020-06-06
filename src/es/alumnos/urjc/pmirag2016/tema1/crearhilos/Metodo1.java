package es.alumnos.urjc.pmirag2016.tema1.crearhilos;

// Método 1: Crear hilos extendiendo de la clase Thread.
public class Metodo1 extends Thread {

    // El método run() contiene el código que se ejecutará en el hilo.
    @Override
    public void run() {
        System.out.println("Thread: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("--- Method 1 ---");

        System.out.println("Creating a thread...");
        Thread thr = new Metodo1();

        System.out.println("Starting a thread...");
        thr.start();
    }
}
