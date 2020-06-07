package es.alumnos.urjc.pmirag2016.tema4.sincronizacioncondicional.intercambiadores;

import java.util.concurrent.Exchanger;

public class ProductorConsumidorIntercambiador {

    private static Exchanger<Double> exchanger = new Exchanger<>();

    private static void productor() throws InterruptedException {
        double producto = 0;
        for (int i = 0; i < 10; i++) {
            producto++;
            Thread.sleep(1000);
            exchanger.exchange(producto);
        }
    }

    private static void consumidor() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            // Se ignora el intercambio en un sentido.
            double producto = exchanger.exchange(null);
            System.out.println(Thread.currentThread().getName() + "::Producto: " + producto);
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) {
        Thread productor = new Thread(() -> {
            try {
                productor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Productor");
        Thread consumidor = new Thread(() -> {
            try {
                consumidor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Consumidor");

        productor.start();
        consumidor.start();
    }
}
