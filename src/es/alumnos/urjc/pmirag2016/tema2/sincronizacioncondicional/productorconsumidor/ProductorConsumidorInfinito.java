package es.alumnos.urjc.pmirag2016.tema2.sincronizacioncondicional.productorconsumidor;

// Ejercicio productor consumidor con espera activa
// Productor genera números random y el consumidor los muestra de manera infinita
public class ProductorConsumidorInfinito {

    private static int aleatorio;
    private static volatile boolean consumido;  // Ésta puede sobrar
    private static volatile boolean generado;

    private static void productor() {
        while (true) {
            while (!consumido) ;
            System.out.println("Productor: Generating random number...");
            aleatorio = (int) (Math.random() * (10 - 1 + 1) + 1);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            generado = true;
            consumido = false;
        }
    }

    private static void consumidor() {
        while (true) {
            System.out.println("Consumidor: Waiting for producer...");
            while (!generado) ;
            System.out.println("Consumidor: The number receive is " + aleatorio);
            consumido = true;
            generado = false;
        }
    }

    public static void main(String[] args) {
        aleatorio = -1;
        consumido = true;
        generado = false;

        Thread productor = new Thread(ProductorConsumidorInfinito::productor);
        Thread consumidor = new Thread(ProductorConsumidorInfinito::consumidor);

        productor.start();
        consumidor.start();
    }
}
