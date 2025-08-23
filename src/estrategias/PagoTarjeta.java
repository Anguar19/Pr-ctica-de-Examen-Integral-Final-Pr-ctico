/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estrategias;
import java.util.Random;
import excepciones.PagoRechazadoException;
/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class PagoTarjeta extends Pago{
     @Override
    public boolean procesarPago(double monto) {
        // Simulación de pago con tarjeta (90% de éxito)
        Random random = new Random();
        if (random.nextDouble() < 0.9) {
            return true;
        } else {
            throw new PagoRechazadoException("Pago con tarjeta rechazado");
        }
    }
    
    @Override
    public String toString() {
        return "Tarjeta";
    }
}
