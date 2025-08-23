/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cifrado;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;
/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class UtilidadCifrado {
   private static final String ALGORITMO = "AES/GCM/NoPadding";
    private static final String ALGORITMO_CLAVE_SECRETA = "PBKDF2WithHmacSHA256";
    private static final String CONTRASENA = "PuraVida-20251";
    private static final byte[] SAL = "PuraVidaSalt".getBytes();
    private static final int ITERACIONES = 65536;
    private static final int LONGITUD_CLAVE = 256;
    private static final int LONGITUD_ETIQUETA_GCM = 128;
    private static final byte[] VECTOR_INICIALIZACION = new byte[12];
    
    static {
        // Inicializar IV con valores predefinidos
        for (int i = 0; i < VECTOR_INICIALIZACION.length; i++) {
            VECTOR_INICIALIZACION[i] = (byte) i;
        }
    }
    
    public static String cifrar(String datos) {
        try {
            SecretKeyFactory fabrica = SecretKeyFactory.getInstance(ALGORITMO_CLAVE_SECRETA);
            KeySpec especificacion = new PBEKeySpec(CONTRASENA.toCharArray(), SAL, ITERACIONES, LONGITUD_CLAVE);
            SecretKey claveTemporal = fabrica.generateSecret(especificacion);
            SecretKeySpec claveSecreta = new SecretKeySpec(claveTemporal.getEncoded(), "AES");
            
            Cipher cifrador = Cipher.getInstance(ALGORITMO);
            GCMParameterSpec parametros = new GCMParameterSpec(LONGITUD_ETIQUETA_GCM, VECTOR_INICIALIZACION);
            cifrador.init(Cipher.ENCRYPT_MODE, claveSecreta, parametros);
            
            byte[] datosCifrados = cifrador.doFinal(datos.getBytes());
            return Base64.getEncoder().encodeToString(datosCifrados);
        } catch (Exception e) {
            throw new RuntimeException("Error al cifrar datos", e);
        }
    }
    
    public static String descifrar(String datosCifrados) {
        try {
            SecretKeyFactory fabrica = SecretKeyFactory.getInstance(ALGORITMO_CLAVE_SECRETA);
            KeySpec especificacion = new PBEKeySpec(CONTRASENA.toCharArray(), SAL, ITERACIONES, LONGITUD_CLAVE);
            SecretKey claveTemporal = fabrica.generateSecret(especificacion);
            SecretKeySpec claveSecreta = new SecretKeySpec(claveTemporal.getEncoded(), "AES");
            
            Cipher cifrador = Cipher.getInstance(ALGORITMO);
            GCMParameterSpec parametros = new GCMParameterSpec(LONGITUD_ETIQUETA_GCM, VECTOR_INICIALIZACION);
            cifrador.init(Cipher.DECRYPT_MODE, claveSecreta, parametros);
            
            byte[] datosDecodificados = Base64.getDecoder().decode(datosCifrados);
            byte[] datosDescifrados = cifrador.doFinal(datosDecodificados);
            return new String(datosDescifrados);
        } catch (Exception e) {
            throw new RuntimeException("Error al descifrar datos", e);
        }
    } 
}
