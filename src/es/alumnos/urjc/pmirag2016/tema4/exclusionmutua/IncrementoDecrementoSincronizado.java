package es.alumnos.urjc.pmirag2016.tema4.exclusionmutua;

public class IncrementoDecrementoSincronizado {

    private static int x = 0;
    private static Object xLock = new Object(); /* El cerrojo puede ser un objeto
                                                   de cualquier clase.
                                                   Se usa xLock en dos métodos
                                                   diferentes => Secciones críticas
                                                   diferentes de la misma exclusión
                                                   mutua. */

    /*
     * Se pueden añadir tantos cerrojos como se quieran
     * Si se creara un xLock para el inc y un yLock para el dec, cada cerrojo se usaría
     * en una exclusión mutua diferente
     */

    private static void inc() {
        for (int i = 0; i < 1000000; i++) {
            // En la sentencia sincronizada se indica el cerrojo
            synchronized (xLock) {
                x = x + 1;
            }
        }
    }

    private static void dec() {
        for (int i = 0; i < 1000000; i++) {
            // En la sentencia sincronizada se indica el cerrojo
            synchronized (xLock) {
                x = x - 1;
            }
        }
    }

    public static void main(String[] args) {
        Thread tInc = new Thread(IncrementoDecrementoSincronizado::inc);
        Thread tDec = new Thread(IncrementoDecrementoSincronizado::dec);

        tInc.start();
        tDec.start();

        try {
            tInc.join();
            tDec.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("x: " + x);
    }
}
