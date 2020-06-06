package es.alumnos.urjc.pmirag2016.tema2.sincronizacioncondicional.productorconsumidor.objectoriented;

public class Productor implements Runnable {

    private static int numAleatorio;
    private static volatile boolean continuar;

    static {
        numAleatorio = -1;
        continuar = false;
    }

    @Override
    public void run() {
        System.out.println("Productor: Generando número aleatorio...");
        numAleatorio = (int) (Math.random() * (10 - 1 + 1) + 1);
        continuar = true;
        System.out.println("Productor: Número aleatorio generado (" + numAleatorio + ")");
    }

    public static int getNumAleatorio() {
        return numAleatorio;
    }

    public static boolean isContinuar() {
        return continuar;
    }
}
