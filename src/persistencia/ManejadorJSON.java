/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;
import dominio.entidades.Producto;
import java.io.*;
import java.util.Collection;
/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class ManejadorJSON {
     public static void guardarJSON(String rutaArchivo, Collection<Producto> productos) {
        StringBuilder json = new StringBuilder();
        json.append("[\n");
        
        boolean primero = true;
        for (Producto producto : productos) {
            if (!primero) {
                json.append(",\n");
            }
            json.append("  {\n");
            json.append("    \"codigo\": \"").append(escaparJSON(producto.getCodigo())).append("\",\n");
            json.append("    \"nombre\": \"").append(escaparJSON(producto.getNombre())).append("\",\n");
            json.append("    \"categoria\": \"").append(escaparJSON(producto.getCategoria())).append("\",\n");
            json.append("    \"precio\": ").append(producto.getPrecio()).append(",\n");
            json.append("    \"stockMinimo\": ").append(producto.getStockMinimo()).append(",\n");
            json.append("    \"stockActual\": ").append(producto.getStockActual()).append("\n");
            json.append("  }");
            primero = false;
        }
        
        json.append("\n]");
        
        // Crear backup primero
        File archivo = new File(rutaArchivo);
        if (archivo.exists()) {
            File backup = new File(rutaArchivo + ".bak");
            archivo.renameTo(backup);
        }
        
        // Escribir a archivo temporal
        File archivoTemporal = new File(rutaArchivo + ".tmp");
        try (PrintWriter escritor = new PrintWriter(new FileWriter(archivoTemporal))) {
            escritor.print(json.toString());
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir JSON", e);
        }
        
        // Renombrar atómicamente
        if (!archivoTemporal.renameTo(archivo)) {
            throw new RuntimeException("Error al renombrar archivo temporal");
        }
    }
    
    private static String escaparJSON(String texto) {
        return texto.replace("\\", "\\\\")
                  .replace("\"", "\\\"")
                  .replace("\b", "\\b")
                  .replace("\f", "\\f")
                  .replace("\n", "\\n")
                  .replace("\r", "\\r")
                  .replace("\t", "\\t");
    }
}
