/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;
import dominio.entidades.Orden;
import dominio.entidades.Producto;
import dominio.entidades.Cliente;
import repositorios.RepositorioOrdenes;
import estrategias.Pago;
import estrategias.Descuento;
import cifrado.UtilidadHMAC;
import dominio.entidades.ItemOrden;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class ServicioOrdenes {
    private final RepositorioOrdenes repositorioOrdenes;
    private final ServicioProductos servicioProductos;
    private final ServicioClientes servicioClientes;
    
    public ServicioOrdenes() {
        this.repositorioOrdenes = new RepositorioOrdenes();
        this.servicioProductos = new ServicioProductos();
        this.servicioClientes = new ServicioClientes();
    }
    
    public Orden crearOrden(String id, String idCliente) {
        Cliente cliente = servicioClientes.obtenerClientePorId(idCliente);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no encontrado: " + idCliente);
        }
        
        Orden orden = new Orden(id, cliente);
        repositorioOrdenes.guardar(orden);
        return orden;
    }
    
    public void agregarItemAOrden(String idOrden, String codigoProducto, int cantidad) {
        Orden orden = repositorioOrdenes.buscarPorId(idOrden);
        Producto producto = servicioProductos.obtenerProductoPorCodigo(codigoProducto);
        
        if (orden == null || producto == null) {
            throw new IllegalArgumentException("Orden o producto no encontrado");
        }
        
        orden.agregarItem(producto, cantidad);
        repositorioOrdenes.guardar(orden);
    }
    
    public void aplicarEstrategiaPago(String idOrden, Pago estrategiaPago) {
        Orden orden = repositorioOrdenes.buscarPorId(idOrden);
        if (orden != null) {
            orden.setEstrategiaPago(estrategiaPago);
            repositorioOrdenes.guardar(orden);
        }
    }
    
    public void aplicarEstrategiaDescuento(String idOrden, Descuento estrategiaDescuento) {
        Orden orden = repositorioOrdenes.buscarPorId(idOrden);
        if (orden != null) {
            orden.setEstrategiaDescuento(estrategiaDescuento);
            repositorioOrdenes.guardar(orden);
        }
    }
    
    public boolean procesarPago(String idOrden) {
        Orden orden = repositorioOrdenes.buscarPorId(idOrden);
        if (orden != null) {
            return orden.procesarPago();
        }
        return false;
    }
    
    public void generarComprobante(String idOrden, String rutaArchivo) {
        Orden orden = repositorioOrdenes.buscarPorId(idOrden);
        if (orden == null) {
            throw new IllegalArgumentException("Orden no encontrada");
        }
        
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            StringBuilder comprobante = new StringBuilder();
            comprobante.append("COMPROBANTE DE PAGO - PURAVIDA MIPYME SUITE\n");
            comprobante.append("============================================\n");
            comprobante.append("Orden ID: ").append(orden.getId()).append("\n");
            comprobante.append("Cliente: ").append(orden.getCliente().getNombre()).append("\n");
            comprobante.append("Fecha: ").append(orden.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))).append("\n");
            comprobante.append("--------------------------------------------\n");
            
            for (ItemOrden item : orden.getItems()) {
                comprobante.append(String.format("%s x%d: $%.2f\n", 
                    item.getProducto().getNombre(), 
                    item.getCantidad(), 
                    item.getSubtotal()));
            }
            
            comprobante.append("--------------------------------------------\n");
            comprobante.append(String.format("Subtotal: $%.2f\n", orden.getTotalBruto()));
            comprobante.append(String.format("Descuentos: $%.2f\n", orden.getDescuentos()));
            comprobante.append(String.format("Impuestos (13%%): $%.2f\n", orden.getImpuestos()));
            comprobante.append(String.format("TOTAL: $%.2f\n", orden.getTotalNeto()));
            comprobante.append("============================================\n");
            
            // Calcular y agregar HMAC para integridad
            String datosComprobante = comprobante.toString();
            String hmac = UtilidadHMAC.calcularHMAC(datosComprobante);
            comprobante.append("Código de verificación: ").append(hmac).append("\n");
            
            writer.write(comprobante.toString());
        } catch (IOException e) {
            throw new RuntimeException("Error al generar comprobante: " + e.getMessage());
        }
    }
    
    public List<Orden> obtenerTodasOrdenes() {
        return repositorioOrdenes.buscarTodos();
    }
    
    public Orden obtenerOrdenPorId(String id) {
        return repositorioOrdenes.buscarPorId(id);
    }
}
