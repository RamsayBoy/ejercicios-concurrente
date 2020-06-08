package es.alumnos.urjc.pmirag2016.tema5.ejercicios;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ContarVocales {

    private static final String FRASE = "Hola me llamo Pepe.";

    private static int contarVocales(String palabra) {
        int numVocales = 0;

        for (int i = 0; i < palabra.length(); i++) {
            switch (palabra.charAt(i)) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':

                case 'A':
                case 'E':
                case 'I':
                case 'O':
                case 'U':
                    numVocales++;
            }
        }

        return numVocales;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        String[] palabras = FRASE.split(" ");

        ExecutorService servicio = Executors.newFixedThreadPool(palabras.length);

        int vocales = 0;
        for (String palabra : palabras) {
            Future<Integer> resultado = servicio.submit(() -> contarVocales(palabra));
            vocales += resultado.get();
        }

        System.out.println("Número de vocales: " + vocales);

        servicio.shutdown();
    }
}
