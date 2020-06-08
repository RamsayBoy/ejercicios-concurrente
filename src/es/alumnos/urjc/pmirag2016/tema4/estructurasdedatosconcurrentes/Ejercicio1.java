package es.alumnos.urjc.pmirag2016.tema4.estructurasdedatosconcurrentes;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/*
 * Se desea implementar de forma concurrente un programa que busca ficheros con el mismo
 * nombre dentro de una carpeta.
 *
 * La búsqueda se realiza recursivamente en unas carpetas dentro de otras.
 *
 * Se proporciona la versión secuencial del programa.
 *
 * Por simplicidad, en la carpeta raíz no hay ficheros y se crearán tantos hilos
 * como carpetas.
 *
 * -----------------------------------------------------------------------------
 *
 * NOTA: Esto es solo la versión secuencial.
 */
public class Ejercicio1 {

    private static final String PATHNAME = "C:\\Dir";

    private static Map<String, String> duplicates = new HashMap<>();

    public static void findDuplicates(File root) {
        if (root.isDirectory()) {
            for (File file : root.listFiles()) {
                if (file.isDirectory()) {
                    findDuplicates(file);
                } else {
                    String path = duplicates.get(file.getName());
                    if (path == null) {
                        duplicates.put(file.getName(), file.getAbsolutePath());
                    } else {
                        System.out.println("Found duplicate file: " + file.getName());
                        System.out.println("    " + path);
                        System.out.println("    " + file.getAbsolutePath());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        findDuplicates(new File(PATHNAME));
    }
}
