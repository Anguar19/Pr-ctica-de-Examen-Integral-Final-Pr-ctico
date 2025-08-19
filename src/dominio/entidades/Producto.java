/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio.entidades;
import excepciones.PrecioInvalidoException;
import excepciones.StockNegativoException;
/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class Producto {
  private String codigo;
    private String nombre;
    private String categoria;
    private double precio;
    private int stockMinimo;
    private int stockActual;
    
    public Producto(String codigo, String nombre, String categoria, 
                   double precio, int stockMinimo, int stockActual) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stockMinimo = stockMinimo;
        this.stockActual = stockActual;
        validar();
    }
    
    private void validar() {
        if (precio <= 0) {
            throw new PrecioInvalidoException("El precio debe ser mayor a cero");
        }
        if (stockActual < 0) {
            throw new StockNegativoException("El stock no puede ser negativo");
        }
    }
    
    public void actualizarStock(int cantidad) {
        if (this.stockActual + cantidad < 0) {
            throw new StockNegativoException("No hay suficiente stock disponible");
        }
        this.stockActual += cantidad;
    }
    
    // Getters y Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { 
        this.precio = precio; 
        validar();
    }
    
    public int getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(int stockMinimo) { this.stockMinimo = stockMinimo; }
    
    public int getStockActual() { return stockActual; }
    public void setStockActual(int stockActual) { 
        this.stockActual = stockActual; 
        validar();
    }
    
    @Override
    public String toString() {
        return nombre + " (" + codigo + ") - $" + precio;
    }  
}
