/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio.entidades;

import cifrado.UtilidadCifrado;

/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class Cliente {
    private String idInterno;
    private String nombre;
    private String correo;
    private String telefono;
    private String cedulaCifrada;
    
    public Cliente(String idInterno, String nombre, String correo, 
                  String telefono, String cedula) {
        this.idInterno = idInterno;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.cedulaCifrada = UtilidadCifrado.cifrar(cedula);
    }
    
    // Getters y Setters
    public String getIdInterno() { return idInterno; }
    public void setIdInterno(String idInterno) { this.idInterno = idInterno; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    public String getCedulaCifrada() { return cedulaCifrada; }
    
    public void setCedula(String cedula) {
        this.cedulaCifrada = UtilidadCifrado.cifrar(cedula);
    }
    
    public String getCedulaDescifrada() {
        return UtilidadCifrado.descifrar(cedulaCifrada);
    }
    
    @Override
    public String toString() {
        return nombre + " (" + idInterno + ")";
    }
}
