/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class ManejadorCSV {
    public static List<String[]> leerCSV(String rutaArchivo) {
        List<String[]> datos = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                // Manejar comillas dobles para escapar
                List<String> campos = new ArrayList<>();
                boolean entreComillas = false;
                StringBuilder campo = new StringBuilder();
                
                for (char c : linea.toCharArray()) {
                    if (c == '"') {
                        entreComillas = !entreComillas;
                    } else if (c == ',' && !entreComillas) {
                        campos.add(campo.toString());
                        campo = new StringBuilder();
                    } else {
                        campo.append(c);
                    }
                }
                campos.add(campo.toString());
                datos.add(campos.toArray(new String[0]));
            }
        } catch (IOException e) {
            // Si el archivo no existe, se creará al guardar
            System.out.println("Archivo no encontrado, se creará uno nuevo: " + rutaArchivo);
        }
        return datos;
    }
    
    public static void escribirCSV(String rutaArchivo, List<String[]> datos) {
        // Crear backup primero
        File archivo = new File(rutaArchivo);
        if (archivo.exists()) {
            File backup = new File(rutaArchivo + ".bak");
            archivo.renameTo(backup);
        }
        
        // Escribir a archivo temporal
        File archivoTemporal = new File(rutaArchivo + ".tmp");
        try (PrintWriter escritor = new PrintWriter(new FileWriter(archivoTemporal))) {
            for (String[] fila : datos) {
                for (int i = 0; i < fila.length; i++) {
                    // Escapar comas con comillas dobles si es necesario
                    if (fila[i].contains(",") || fila[i].contains("\"")) {
                        fila[i] = "\"" + fila[i].replace("\"", "\"\"") + "\"";
                    }
                    escritor.print(fila[i]);
                    if (i < fila.length - 1) {
                        escritor.print(",");
                    }
                }
                escritor.println();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir CSV", e);
        }
        
        // Renombrar atómicamente
        if (!archivoTemporal.renameTo(archivo)) {
            throw new RuntimeException("Error al renombrar archivo temporal");
        }
    }
}
