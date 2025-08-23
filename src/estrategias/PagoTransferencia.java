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
public class PagoTransferencia extends Pago {
    @Override
    public boolean procesarPago(double monto) {
        // Simulación de transferencia bancaria (95% de éxito)
        Random random = new Random();
        if (random.nextDouble() < 0.95) {
            return true;
        } else {
            throw new PagoRechazadoException("Transferencia bancaria rechazada");
        }
    }
    
    @Override
    public String toString() {
        return "Transferencia";
    }
}
