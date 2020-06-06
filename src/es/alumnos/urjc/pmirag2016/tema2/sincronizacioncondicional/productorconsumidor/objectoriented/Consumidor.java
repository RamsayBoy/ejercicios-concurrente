package es.alumnos.urjc.pmirag2016.tema2.sincronizacioncondicional.productorconsumidor.objectoriented;

public class Consumidor implements Runnable {
    @Override
    public void run() {
        System.out.println("Consumidor: Esperando al productor...");
        while (!Productor.isContinuar()) ;
        System.out.println("Consumidor: " + Productor.getNumAleatorio());
    }
}
