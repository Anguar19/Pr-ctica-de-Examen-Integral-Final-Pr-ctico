/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cifrado;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class UtilidadHMAC {
    private static final String ALGORITMO = "HmacSHA256";
    private static final String CLAVE_SECRETA = "CONTROL-CLAVE-2025";
    
    public static String calcularHMAC(String datos) {
        try {
            SecretKeySpec clave = new SecretKeySpec(CLAVE_SECRETA.getBytes(), ALGORITMO);
            Mac mac = Mac.getInstance(ALGORITMO);
            mac.init(clave);
            
            byte[] hmacBytes = mac.doFinal(datos.getBytes());
            return Base64.getEncoder().encodeToString(hmacBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error al calcular HMAC", e);
        }
    }
    
    public static boolean verificarHMAC(String datos, String hmac) {
        String hmacCalculado = calcularHMAC(datos);
        return hmacCalculado.equals(hmac);
    }
}
