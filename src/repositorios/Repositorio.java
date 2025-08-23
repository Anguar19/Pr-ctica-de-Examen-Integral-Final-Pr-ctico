/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;

import java.util.List;

/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public interface Repositorio <T>{
    void guardar(T entidad);
    List<T> buscarTodos();
    T buscarPorId(String id);
    void eliminar(String id);
}
