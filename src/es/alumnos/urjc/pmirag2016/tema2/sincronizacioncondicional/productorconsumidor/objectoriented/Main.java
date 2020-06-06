package es.alumnos.urjc.pmirag2016.tema2.sincronizacioncondicional.productorconsumidor.objectoriented;

public class Main {
    public static void main(String[] args) {
        Thread productor = new Thread(new Productor());
        Thread consumidor = new Thread(new Consumidor());

        productor.start();
        consumidor.start();
    }
}
