/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio.entidades;

/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class ItemOrden {
     private Producto producto;
    private int cantidad;
    private double precioUnitario;
    
    public ItemOrden(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecio();
    }
    
    public double getSubtotal() {
        return cantidad * precioUnitario;
    }
    
    // Getters
    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
}
