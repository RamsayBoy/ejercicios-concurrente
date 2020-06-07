package es.alumnos.urjc.pmirag2016.tema4.gestiondehilos;

public class EjercicioProfeSol {

    public static void mensajes(int id) {
        String[] frases = new String[]{
                "La vida es bella",
                "Oh no...",
                "Los pajaritos cantan...",
                "Y molestan..."
        };
        while (true) {
            try {
                Thread.sleep(2000);
                System.out.println(frases[id]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread mensaje = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 4; i++) {
                    mensajes(i);
                }
            }
        });
        int tiempo = 0;
        boolean continuar = true;

        while (continuar) {
            try {
                Thread.sleep(1000);
                tiempo++;
                if (tiempo == 5) {
                    System.out.println("Cansado de esperar");
                    mensaje.interrupt();
                    mensaje.join();
                    continuar = false;
                    System.out.println("Se acabo");
                } else {
                    System.out.println("Todavia esperando...");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Por fin");
    }
}
