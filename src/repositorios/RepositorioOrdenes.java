/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;
import dominio.entidades.Orden;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class RepositorioOrdenes implements Repositorio<Orden>{
    private Map<String, Orden> ordenes;
    
    public RepositorioOrdenes() {
        this.ordenes = new HashMap<>();
    }
    
    @Override
    public void guardar(Orden orden) {
        ordenes.put(orden.getId(), orden);
    }
    
    @Override
    public List<Orden> buscarTodos() {
        return new ArrayList<>(ordenes.values());
    }
    
    @Override
    public Orden buscarPorId(String id) {
        return ordenes.get(id);
    }
    
    @Override
    public void eliminar(String id) {
        ordenes.remove(id);
    }
    
    public List<Orden> buscarPorCliente(String idCliente) {
        List<Orden> resultado = new ArrayList<>();
        for (Orden orden : ordenes.values()) {
            if (orden.getCliente().getIdInterno().equals(idCliente)) {
                resultado.add(orden);
            }
        }
        return resultado;
    }
}
