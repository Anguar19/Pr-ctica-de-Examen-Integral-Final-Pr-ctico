/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;
import dominio.entidades.Producto;
import repositorios.RepositorioProductos;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class ServicioProductos {
   private final RepositorioProductos repositorioProductos;
    
    public ServicioProductos() {
        this.repositorioProductos = new RepositorioProductos();
    }
    
    public void crearProducto(Producto producto) {
        repositorioProductos.guardar(producto);
    }
    
    public List<Producto> obtenerTodosProductos() {
        return repositorioProductos.buscarTodos();
    }
    
    public Producto obtenerProductoPorCodigo(String codigo) {
        return repositorioProductos.buscarPorId(codigo);
    }
    
    public void actualizarProducto(Producto producto) {
        repositorioProductos.actualizar(producto);
    }
    
    public void eliminarProducto(String codigo) {
        repositorioProductos.eliminar(codigo);
    }
    
    public List<Producto> buscarProductosPorTexto(String texto) {
        return repositorioProductos.buscarPorTexto(texto);
    }
    
    public List<Producto> buscarProductosPorCategoria(String categoria) {
        return repositorioProductos.buscarPorCategoria(categoria);
    }
    
    public List<Producto> ordenarProductosPorPrecioDesc() {
        return repositorioProductos.buscarTodos().stream()
                .sorted(Comparator.comparingDouble(Producto::getPrecio).reversed())
                .collect(Collectors.toList());
    }
    
    public List<Producto> ordenarProductosPorNombreAsc() {
        return repositorioProductos.buscarTodos().stream()
                .sorted(Comparator.comparing(Producto::getNombre))
                .collect(Collectors.toList());
    }
    
    public List<Producto> ordenarProductosPorStockAsc() {
        return repositorioProductos.buscarTodos().stream()
                .sorted(Comparator.comparingInt(Producto::getStockActual))
                .collect(Collectors.toList());
    }
    
    public List<Producto> obtenerProductosConStockCritico() {
        return repositorioProductos.buscarTodos().stream()
                .filter(p -> p.getStockActual() < p.getStockMinimo())
                .collect(Collectors.toList());
    }
    
    public List<Producto> importarProductosDesdeCSV(String rutaArchivo) {
        return repositorioProductos.importarDesdeCSV(rutaArchivo);
    }
    
    public List<Producto> obtenerTop5ProductosPorIngresos() {
        // Implementar lógica para calcular productos con mayores ingresos
        return repositorioProductos.buscarTodos().stream()
                .sorted(Comparator.comparingDouble(p -> p.getPrecio() * p.getStockActual()).reversed())
                .limit(5)
                .collect(Collectors.toList());
    } 
}
