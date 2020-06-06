package es.alumnos.urjc.pmirag2016.tema2.sincronizacioncondicional;

// No se debería usar nunca (creo)
public class EsperaActiva {

    static volatile boolean continuar;

    public static void procA() throws InterruptedException {
        new Thread(() -> {
            System.out.println("PA1");
            continuar = true;
            System.out.println("PA2");
        }).start();
    }

    public static void procB() throws InterruptedException {
        new Thread(() -> {
            System.out.println("PB1");
            while (!continuar) ;
            System.out.println("PB2");
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
        continuar = false;
        procA();
        procB();
    }
}
