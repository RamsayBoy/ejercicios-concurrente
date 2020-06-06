package es.alumnos.urjc.pmirag2016.tema2.sincronizacioncondicional.productorconsumidor;

// Ejercicio productor consumidor con espera activa
// Productor genera número random y el consumidor lo muestra
public class ProductorConsumidor {

    private static int aleatorio;
    private static volatile boolean continuar;

    private static void productor() {
        System.out.println("Productor: Generating random number...");
        aleatorio = (int) (Math.random() * (10 - 1 + 1) + 1);
        continuar = true;
    }

    private static void consumidor() {
        System.out.println("Consumidor: Waiting for producer...");
        while (!continuar) ;
        System.out.println("Consumidor: The number receive is " + aleatorio);
    }

    public static void main(String[] args) {
        aleatorio = -1;
        continuar = false;

        Thread productor = new Thread(ProductorConsumidor::productor);
        Thread consumidor = new Thread(ProductorConsumidor::consumidor);

        productor.start();
        consumidor.start();
    }
}
