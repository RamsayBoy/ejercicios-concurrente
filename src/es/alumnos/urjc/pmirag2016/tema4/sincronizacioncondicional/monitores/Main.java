package es.alumnos.urjc.pmirag2016.tema4.sincronizacioncondicional.monitores;

import java.util.ArrayList;
import java.util.List;

public class Main {

    /*private static int nc = 10;
    private static int ns = 2;*/
    private static Monitor manager;

    public static void vendedor() {
        try {
            manager.vender();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void comprador(int id) {
        manager.comprar(id);
    }

    public static void mostrar() {
        try {
            manager.estado();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Thread> ts = new ArrayList<>();
        manager = new Monitor();

        ts.add(new Thread(Main::vendedor, "Vendedor 0"));
        ts.add(new Thread(Main::vendedor, "Vendedor 1"));
        ts.add(new Thread(() -> comprador(0), "Comprador 0"));
        ts.add(new Thread(() -> comprador(1), "Comprador 1"));
        ts.add(new Thread(() -> comprador(2), "Comprador 2"));
        ts.add(new Thread(() -> comprador(3), "Comprador 3"));

        for (int i = 0; i < 6; i++) {
            ts.get(i).start();
        }

        for (int i = 0; i < 6; i++) {
            ts.get(i).join();
        }
    }
}
