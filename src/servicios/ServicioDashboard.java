/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;
import dominio.entidades.Producto;
import dominio.entidades.Cliente;
import java.util.List;
/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class ServicioDashboard {
    private final ServicioProductos servicioProductos;
    private final ServicioClientes servicioClientes;
    private final ServicioOrdenes servicioOrdenes;
    
    public ServicioDashboard() {
        this.servicioProductos = new ServicioProductos();
        this.servicioClientes = new ServicioClientes();
        this.servicioOrdenes = new ServicioOrdenes();
    }
    
    public String generarReporteDashboard() {
        StringBuilder reporte = new StringBuilder();
        
        reporte.append("DASHBOARD - PURAVIDA MIPYME SUITE\n");
        reporte.append("==================================\n\n");
        
        // Top 5 productos por ingresos
        reporte.append("TOP 5 PRODUCTOS POR INGRESOS:\n");
        reporte.append("-----------------------------\n");
        List<Producto> topProductos = servicioProductos.obtenerTop5ProductosPorIngresos();
        for (int i = 0; i < topProductos.size(); i++) {
            Producto p = topProductos.get(i);
            double ingresos = p.getPrecio() * p.getStockActual();
            reporte.append(String.format("%d. %s: $%.2f\n", i+1, p.getNombre(), ingresos));
        }
        reporte.append("\n");
        
        // Top 5 clientes por monto
        reporte.append("TOP 5 CLIENTES POR MONTO:\n");
        reporte.append("-------------------------\n");
        List<Cliente> topClientes = servicioClientes.obtenerTop5ClientesPorMonto();
        for (int i = 0; i < topClientes.size(); i++) {
            Cliente c = topClientes.get(i);
            double monto = servicioClientes.calcularMontoMes(c.getIdInterno());
            reporte.append(String.format("%d. %s: $%.2f\n", i+1, c.getNombre(), monto));
        }
        reporte.append("\n");
        
        // Existencias críticas
        reporte.append("EXISTENCIAS CRÍTICAS:\n");
        reporte.append("---------------------\n");
        List<Producto> productosCriticos = servicioProductos.obtenerProductosConStockCritico();
        if (productosCriticos.isEmpty()) {
            reporte.append("No hay existencias críticas\n");
        } else {
            for (Producto p : productosCriticos) {
                reporte.append(String.format("- %s: %d (mínimo: %d)\n", 
                    p.getNombre(), p.getStockActual(), p.getStockMinimo()));
            }
        }
        reporte.append("\n");
        
        // Resumen general
        reporte.append("RESUMEN GENERAL:\n");
        reporte.append("----------------\n");
        reporte.append("Total productos: ").append(servicioProductos.obtenerTodosProductos().size()).append("\n");
        reporte.append("Total clientes: ").append(servicioClientes.obtenerTodosClientes().size()).append("\n");
        reporte.append("Total órdenes: ").append(servicioOrdenes.obtenerTodasOrdenes().size()).append("\n");
        
        return reporte.toString();
    }
}
