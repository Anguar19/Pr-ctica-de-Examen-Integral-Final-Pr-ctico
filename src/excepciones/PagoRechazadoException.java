/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class PagoRechazadoException extends RuntimeException{
     public PagoRechazadoException(String mensaje) {
        super(mensaje);
    }
}
