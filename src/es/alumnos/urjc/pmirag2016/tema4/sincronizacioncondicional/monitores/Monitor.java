package es.alumnos.urjc.pmirag2016.tema4.sincronizacioncondicional.monitores;

import java.util.LinkedList;
import java.util.Queue;

public class Monitor {

    private static final int PRECIO = 100;
    private Queue<Integer> solicitudes;
    private int dinero;

    public Monitor() {
        solicitudes = new LinkedList<>();
        dinero = 0;
    }

    public synchronized void vender() throws InterruptedException {
        // Recomendable tener que comprobar una condición (por si el notifyAll())
        // despierta un hilo que no debería haber sido despertado (notifyAll es más
        // recomendable que usar notify ya que no podemos elegir el hilo que despertamos).
        while (this.solicitudes.isEmpty()) {
            System.out.println("Esperando...");
            this.wait();
        }

        int client = solicitudes.poll();
        System.out.println("Se ha vendido: " + client + ".");

        dinero += PRECIO;

        this.notifyAll();
    }

    public synchronized void comprar(int cliente) {
        this.solicitudes.add(cliente);
        System.out.println("Cliente " + cliente + " ha solicitado una compra.");
        
        this.notifyAll();
    }

    public synchronized void estado() throws InterruptedException {
        System.out.println("Dinero ganado: " + dinero + ".");
    }

}
