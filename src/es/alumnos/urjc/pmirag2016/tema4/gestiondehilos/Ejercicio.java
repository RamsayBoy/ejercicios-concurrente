package es.alumnos.urjc.pmirag2016.tema4.gestiondehilos;

public class Ejercicio {

    private static final int MAX_SEGUNDOS = 5;

    // Ojo, si lo hacemos de esta manera, dependiendo del catch en el que caiga,
    // el método sigue por donde se ha quedado a no ser que se le indique explícitamente
    // o sea el último catch, en cuyo caso finalizaría el método (obvio).
    private static void procMensajes() {
        System.out.println(Thread.currentThread().getName() + ": La vida es bella.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // Se activa cuando recibe una interrupción por parte de otro hilo y
            // estaba bloqueado (un sleep en este caso).
            System.out.println(Thread.currentThread().getName() + ": Se acabo.");
        }
        System.out.println(Thread.currentThread().getName() + ": O no...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // Se activa cuando recibe una interrupción por parte de otro hilo y
            // estaba bloqueado (un sleep en este caso).
            System.out.println(Thread.currentThread().getName() + ": Se acabo.");
        }
        System.out.println(Thread.currentThread().getName() + ": Los pajaritos cantan.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // Se activa cuando recibe una interrupción por parte de otro hilo y
            // estaba bloqueado (un sleep en este caso).
            System.out.println(Thread.currentThread().getName() + ": Se acabo.");
        }
    }

    // Por otra parte, si lo ponemos de esta manera, al pasar al catch, finalizaría
    // sin pasar por todos los mensajes (obvio).
    private static void procMensajes2() {
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + ": La vida es bella.");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + ": O no...");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + ": Los pajaritos cantan.");
        } catch (InterruptedException e) {
            // Se activa cuando recibe una interrupción por parte de otro hilo y
            // estaba bloqueado (un sleep en este caso).
            System.out.println(Thread.currentThread().getName() + ": Se acabo.");
        }
    }

    public static void main(String[] args) {
        Thread hiloMensajes = new Thread(Ejercicio::procMensajes2);
        hiloMensajes.start();

        int segundos = 0;

        while (hiloMensajes.isAlive() && segundos < MAX_SEGUNDOS) {
            try {
                Thread.sleep(1000);
                segundos++;
                System.out.println(Thread.currentThread().getName() + ": Todavía esperando...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (segundos == MAX_SEGUNDOS) {
                System.out.println(Thread.currentThread().getName() + ": ¡Cansado de esperar!");
                hiloMensajes.interrupt();
            }
        }

        try {
            hiloMensajes.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": ¡Por fin!");
    }
}
