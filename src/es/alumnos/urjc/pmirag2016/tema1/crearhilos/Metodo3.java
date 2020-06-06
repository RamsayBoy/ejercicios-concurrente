package es.alumnos.urjc.pmirag2016.tema1.crearhilos;

public class Metodo3 {
    public static void main(String[] args) {
        System.out.println("--- Method 3 ---");

        System.out.println("Creating a runnable...");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread: " + Thread.currentThread().getName());
            }
        };

        System.out.println("Creating a thread...");
        Thread thr = new Thread(runnable);

        System.out.println("Starting a thread...");
        thr.start();
    }
}
