/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estrategias;

/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class PagoEfectivo extends Pago{
    @Override
    public boolean procesarPago(double monto) {
        // Simulación de pago en efectivo (siempre exitoso)
        return true;
    }
    
    @Override
    public String toString() {
        return "Efectivo";
    }
}
