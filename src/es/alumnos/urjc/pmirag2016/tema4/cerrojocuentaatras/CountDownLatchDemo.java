package es.alumnos.urjc.pmirag2016.tema4.cerrojocuentaatras;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    private static CountDownLatch latch = new CountDownLatch(4);

    private static void runner() throws InterruptedException {
        System.out.println("Listo.");
        latch.await();
        System.out.println("Running...");
    }

    private static void juez() throws InterruptedException {
        for (int i = 3; i >= 0; i--) {
            System.out.println(i);
            latch.countDown();
            Thread.sleep(500);
        }
    }

    public static void main(String[] args) {

        List<Thread> thrs = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Thread thr = new Thread(() -> {
                try {
                    runner();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            thr.start();
            thrs.add(thr);
        }

        Thread juez = new Thread(() -> {
            try {
                juez();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        juez.start();
    }
}
