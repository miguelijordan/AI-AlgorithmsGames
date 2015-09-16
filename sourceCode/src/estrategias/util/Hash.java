package estrategias.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Proporciona una función hash mediante el algoritmo MD5.
 * Se utilizará para indexar las claves de los estados de los juegos.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 09/08/2011
 */
public class Hash {

    private MessageDigest md;
    private static final String ALGORITMO_MD5 = "MD5";

    /**
     * Crea una instancia del algoritmo MD5.
     */
    public Hash() {
    	try{
    		md = MessageDigest.getInstance(ALGORITMO_MD5);
        } catch(NoSuchAlgorithmException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Devuelve el valor hash de la cadena recibida.
     * 
     * @param s	Cadena.
     * @return	Valor hash de s.
     */
    public String getHash(String s) {
        byte[] bytes = s.getBytes();

        md.reset();
        md.update(bytes);
        byte[] messageDigest = md.digest();

        StringBuffer hexString = new StringBuffer(messageDigest.length);
        for (int i = 0; i < messageDigest.length; i++) {
            hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
        }
        return hexString.toString();
    }
}