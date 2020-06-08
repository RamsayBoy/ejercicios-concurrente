package es.alumnos.urjc.pmirag2016.tema4.estructurasdedatosconcurrentes.productorconsumidor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProductorConsumidorColas {

    private static BlockingQueue productos = new ArrayBlockingQueue(7);
    private static int contador = 0;

    public static void productor() throws InterruptedException {
        while (!Thread.interrupted()) {
            System.out.println(Thread.currentThread().getName() + ": PRODUCIENDO");
            //Thread.sleep(500);
            //String producto = "PRODUCTO";
            int producto = (int) (Math.random() * 25 + 1);
            productos.put(producto);
            System.out.println(Thread.currentThread().getName() + " ha producido "
                    + producto);
        }
    }

    public static void consumidor() throws InterruptedException {
        while (!Thread.interrupted()) {
            //Thread.sleep(600);
            int consumiendo = (int) productos.take();
            System.out.println(Thread.currentThread().getName() + " CONSUMIENDO: "
                    + consumiendo);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    productor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Productor " + i).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    consumidor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Consumidor " + i).start();
        }

    }
}
