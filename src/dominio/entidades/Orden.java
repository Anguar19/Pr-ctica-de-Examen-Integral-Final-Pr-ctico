/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio.entidades;
import estrategias.Descuento;
import estrategias.Pago;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class Orden {
    private String id;
    private Cliente cliente;
    private List<ItemOrden> items;
    private double totalBruto;
    private double impuestos;
    private double descuentos;
    private double totalNeto;
    private LocalDateTime fecha;
    private Pago estrategiaPago;
    private Descuento estrategiaDescuento;
    
    public Orden(String id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.items = new ArrayList<>();
        this.fecha = LocalDateTime.now();
        this.totalBruto = 0;
        this.impuestos = 0;
        this.descuentos = 0;
        this.totalNeto = 0;
    }
    
    public void agregarItem(Producto producto, int cantidad) {
        // Verificar stock suficiente
        if (producto.getStockActual() < cantidad) {
            throw new dominio.excepciones.StockInsuficienteException(
                "Stock insuficiente para " + producto.getNombre() + 
                ". Disponible: " + producto.getStockActual()
            );
        }
        
        ItemOrden item = new ItemOrden(producto, cantidad);
        items.add(item);
        producto.actualizarStock(-cantidad); // Reducir stock
        calcularTotales();
    }
    
    public void calculateTotales() {
    totalBruto = items.stream().mapToDouble(ItemOrden::getSubtotal).sum();
    
    // Aplicar descuentos si existe estrategia
    if (estrategiaDescuento != null) {
        descuentos = estrategiaDescuento.aplicarDescuento(this);
    } else {
        descuentos = 0;
    }
    
    // Calcular impuestos (13% IVA)
    impuestos = (totalBruto - descuentos) * 0.13;
    
    totalNeto = totalBruto - descuentos + impuestos;
}

public boolean procesarPago() {
    if (estrategiaPago == null) {
        throw new IllegalStateException("No se ha seleccionado método de pago");
    }
    return estrategiaPago.procesarPago(totalNeto);
}
    
    // Getters y Setters
    public String getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public List<ItemOrden> getItems() { return items; }
    public double getTotalBruto() { return totalBruto; }
    public double getImpuestos() { return impuestos; }
    public double getDescuentos() { return descuentos; }
    public double getTotalNeto() { return totalNeto; }
    public LocalDateTime getFecha() { return fecha; }
    
    public void setEstrategiaPago(Pago estrategiaPago) { 
        this.estrategiaPago = estrategiaPago; 
    }
    
    public void setEstrategiaDescuento(Descuento estrategiaDescuento) { 
        this.estrategiaDescuento = estrategiaDescuento; 
        calcularTotales();
    }
}
