/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;
import dominio.entidades.Producto;
import excepciones.ProductoDuplicadoException;
import persistencia.ManejadorCSV;
import persistencia.ManejadorJSON;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class RepositorioProductos implements Repositorio<Producto>{

    private Map<String, Producto> productos;
    private final String rutaArchivoCSV = "datos/productos.csv";
    private final String rutaArchivoJSON = "datos/productos.json";
    
    public RepositorioProductos() {
        this.productos = new HashMap<>();
        cargarDesdeCSV();
    }
    
    @Override
    public void guardar(Producto producto) {
        if (productos.containsKey(producto.getCodigo())) {
            throw new ProductoDuplicadoException("Producto con código " + producto.getCodigo() + " ya existe");
        }
        productos.put(producto.getCodigo(), producto);
        guardarEnCSV();
        guardarEnJSON();
    }
    
    @Override
    public List<Producto> buscarTodos() {
        return new ArrayList<>(productos.values());
    }
    
    @Override
    public Producto buscarPorId(String codigo) {
        return productos.get(codigo);
    }
    
    @Override
    public void eliminar(String codigo) {
        productos.remove(codigo);
        guardarEnCSV();
        guardarEnJSON();
    }
    
    public void actualizar(Producto producto) {
        if (!productos.containsKey(producto.getCodigo())) {
            throw new IllegalArgumentException("Producto no existe");
        }
        productos.put(producto.getCodigo(), producto);
        guardarEnCSV();
        guardarEnJSON();
    }
    
    private void cargarDesdeCSV() {
        List<String[]> datos = ManejadorCSV.leerCSV(rutaArchivoCSV);
        for (String[] fila : datos) {
            if (fila.length >= 6) {
                try {
                    Producto producto = new Producto(
                        fila[0], fila[1], fila[2], 
                        Double.parseDouble(fila[3]), 
                        Integer.parseInt(fila[4]), 
                        Integer.parseInt(fila[5])
                    );
                    productos.put(producto.getCodigo(), producto);
                } catch (Exception e) {
                    System.err.println("Error al cargar producto: " + e.getMessage());
                }
            }
        }
    }
    
    private void guardarEnCSV() {
        List<String[]> datos = new ArrayList<>();
        for (Producto producto : productos.values()) {
            String[] fila = {
                producto.getCodigo(),
                producto.getNombre(),
                producto.getCategoria(),
                String.valueOf(producto.getPrecio()),
                String.valueOf(producto.getStockMinimo()),
                String.valueOf(producto.getStockActual())
            };
            datos.add(fila);
        }
        ManejadorCSV.escribirCSV(rutaArchivoCSV, datos);
    }
    
    private void guardarEnJSON() {
        ManejadorJSON.guardarJSON(rutaArchivoJSON, productos.values());
    }
    
    public List<Producto> importarDesdeCSV(String rutaArchivo) {
        List<Producto> productosImportados = new ArrayList<>();
        List<String[]> datos = ManejadorCSV.leerCSV(rutaArchivo);
        
        for (int i = 0; i < datos.size(); i++) {
            try {
                String[] fila = datos.get(i);
                if (fila.length >= 6) {
                    Producto producto = new Producto(
                        fila[0], fila[1], fila[2], 
                        Double.parseDouble(fila[3]), 
                        Integer.parseInt(fila[4]), 
                        Integer.parseInt(fila[5])
                    );
                    
                    if (productos.containsKey(producto.getCodigo())) {
                        productos.put(producto.getCodigo(), producto); // Actualizar
                    } else {
                        productos.put(producto.getCodigo(), producto); // Nuevo
                    }
                    productosImportados.add(producto);
                }
            } catch (Exception e) {
                System.err.println("Error en línea " + (i+1) + ": " + e.getMessage());
            }
        }
        
        guardarEnCSV();
        guardarEnJSON();
        return productosImportados;
    }
    
    public List<Producto> buscarPorCategoria(String categoria) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto producto : productos.values()) {
            if (producto.getCategoria().equalsIgnoreCase(categoria)) {
                resultado.add(producto);
            }
        }
        return resultado;
    }
    
    public List<Producto> buscarPorTexto(String texto) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto producto : productos.values()) {
            if (producto.getNombre().toLowerCase().contains(texto.toLowerCase()) ||
                producto.getCategoria().toLowerCase().contains(texto.toLowerCase()) ||
                producto.getCodigo().toLowerCase().contains(texto.toLowerCase())) {
                resultado.add(producto);
            }
        }
        return resultado;
    }
    
}
