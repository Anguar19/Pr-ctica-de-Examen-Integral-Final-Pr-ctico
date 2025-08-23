/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;
import dominio.entidades.Cliente;
import dominio.entidades.Orden;
import repositorios.RepositorioClientes;
import repositorios.RepositorioOrdenes;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class ServicioClientes {
    private final RepositorioClientes repositorioClientes;
    private final RepositorioOrdenes repositorioOrdenes;
    
    public ServicioClientes() {
        this.repositorioClientes = new RepositorioClientes();
        this.repositorioOrdenes = new RepositorioOrdenes();
    }
    
    public void crearCliente(Cliente cliente) {
        repositorioClientes.guardar(cliente);
    }
    
    public List<Cliente> obtenerTodosClientes() {
        return repositorioClientes.buscarTodos();
    }
    
    public Cliente obtenerClientePorId(String id) {
        return repositorioClientes.buscarPorId(id);
    }
    
    public void actualizarCliente(Cliente cliente) {
        repositorioClientes.guardar(cliente);
    }
    
    public void eliminarCliente(String id) {
        repositorioClientes.eliminar(id);
    }
    
    public List<Cliente> buscarClientesPorTexto(String texto) {
        return repositorioClientes.buscarPorTexto(texto);
    }
    
    public double calcularMontoMes(String idCliente) {
        List<Orden> ordenesCliente = repositorioOrdenes.buscarPorCliente(idCliente);
        return ordenesCliente.stream()
                .mapToDouble(Orden::getTotalNeto)
                .sum();
    }
    
    public void recalcularFidelizacion() {
        // Simula proceso pesado de recálculo de fidelización
        try {
            Thread.sleep(10); // Pequeña pausa para simular procesamiento
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public List<Cliente> obtenerTop5ClientesPorMonto() {
        return repositorioClientes.buscarTodos().stream()
                .sorted((c1, c2) -> Double.compare(
                    calcularMontoMes(c2.getIdInterno()), 
                    calcularMontoMes(c1.getIdInterno())
                ))
                .limit(5)
                .collect(Collectors.toList());
    }
}
