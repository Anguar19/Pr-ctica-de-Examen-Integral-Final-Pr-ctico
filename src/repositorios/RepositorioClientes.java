/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;
import dominio.entidades.Cliente;
import persistencia.ManejadorCSV;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class RepositorioClientes  implements Repositorio<Cliente>{
    private Map<String, Cliente> clientes;
    private final String rutaArchivoCSV = "datos/clientes.csv";
    
    public RepositorioClientes() {
        this.clientes = new HashMap<>();
        cargarDesdeCSV();
    }
    
    @Override
    public void guardar(Cliente cliente) {
        clientes.put(cliente.getIdInterno(), cliente);
        guardarEnCSV();
    }
    
    @Override
    public List<Cliente> buscarTodos() {
        return new ArrayList<>(clientes.values());
    }
    
    @Override
    public Cliente buscarPorId(String id) {
        return clientes.get(id);
    }
    
    @Override
    public void eliminar(String id) {
        clientes.remove(id);
        guardarEnCSV();
    }
    
    private void cargarDesdeCSV() {
        List<String[]> datos = ManejadorCSV.leerCSV(rutaArchivoCSV);
        for (String[] fila : datos) {
            if (fila.length >= 5) {
                try {
                    Cliente cliente = new Cliente(
                        fila[0], fila[1], fila[2], fila[3], fila[4]
                    );
                    clientes.put(cliente.getIdInterno(), cliente);
                } catch (Exception e) {
                    System.err.println("Error al cargar cliente: " + e.getMessage());
                }
            }
        }
    }
    
    private void guardarEnCSV() {
        List<String[]> datos = new ArrayList<>();
        for (Cliente cliente : clientes.values()) {
            String[] fila = {
                cliente.getIdInterno(),
                cliente.getNombre(),
                cliente.getCorreo(),
                cliente.getTelefono(),
                cliente.getCedulaCifrada() // Ya viene cifrada
            };
            datos.add(fila);
        }
        ManejadorCSV.escribirCSV(rutaArchivoCSV, datos);
    }
    
    public List<Cliente> buscarPorTexto(String texto) {
        List<Cliente> resultado = new ArrayList<>();
        for (Cliente cliente : clientes.values()) {
            if (cliente.getNombre().toLowerCase().contains(texto.toLowerCase()) ||
                cliente.getCorreo().toLowerCase().contains(texto.toLowerCase()) ||
                cliente.getIdInterno().toLowerCase().contains(texto.toLowerCase())) {
                resultado.add(cliente);
            }
        }
        return resultado;
    }
}
