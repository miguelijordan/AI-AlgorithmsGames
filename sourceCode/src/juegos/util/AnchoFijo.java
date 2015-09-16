package juegos.util;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Esta clase contiene rutinas utilitarias para generar strings de ancho fijo
 * tipicamente empleados en ambientes Mainframe/Cobol
 *
 * @author diego
 */
public class AnchoFijo {

    static String relleno;

    static {
        byte[] espacios = new byte[1024];
        for (int z = 0; z < espacios.length; z++) {
            espacios[z] = ' ';
        }
        relleno = new String(espacios, 0, espacios.length);
    }

    /* Implementacion lenta para anchos muy grandes */
    private static String anchoFijoAuxiliar(String s, int ancho) {

        while (s.length() < ancho) {
            s = s + "                                             ";
        }
        return s.substring(0, ancho);
    }

    /**
     * Retorna un String de ancho deseado generado rellenando un String
     * inicial con espacios a la derecha. Si el String inicial es menor
     * que el ancho deseado, éste es truncado empezando desde la posición
     * cero. Si se pasa null el resultado es indefinido.
     *
     * @param El String inicial
     * @param Ancho deseado
     * @return El String de ancho especificado
     */
    public static String anchoFijo(String s, int ancho) {

        int slen = s.length();
        if (slen == ancho) {
            return s;
        }
        if (slen > ancho) {
            return s.substring(0, ancho);
        }
        // rellenar
        if (ancho - slen > relleno.length()) {
            return anchoFijoAuxiliar(s, ancho);
        }

        String rellenante = relleno.substring(0, ancho - slen);
        return s + rellenante;
    }

    private static String anchoFijoNumerico(String s, int ancho) {

        while (s.length() < ancho) {
            s = "00000000000000000000000" + s;
        }
        return s.substring(s.length() - ancho);
    }

    /**
     * Retorna un String de ancho deseado generado agregando ceros a la
     * izquierda a un número proporcionado. Si el número inicial es menor
     * que el ancho deseado, éste es truncado empezando desde la posición
     * final (el valor se corrompe.)
     *
     * @param n El valor numérico inicial
     * @param ancho Ancho deseado
     * @return El String de ancho especificado rellenado con ceros
     */
    public static String anchoFijo(int n, int ancho) {

        return anchoFijoNumerico("" + n, ancho);
    }

    /**
     * Retorna un String de ancho deseado generado agregando ceros a la
     * izquierda a un número proporcionado. Si el número inicial es menor
     * que el ancho deseado, éste es truncado empezando desde la posición
     * final (el valor se corrompe.)
     *
     * @param n El valor numérico inicial
     * @param ancho Ancho deseado
     * @return El String de ancho especificado rellenado con ceros
     */
    public static String anchoFijo(long n, int ancho) {

        return anchoFijoNumerico("" + n, ancho);
    }

    /** Retorna un String de ancho deseado generado agregando ceros a la
     * izquierda a un número proporcionado y con la precision especificada. Si
     * el número inicial es menor que el ancho deseado, éste es truncado empezando
     * desde la posición final (y el valor se corrompe totalmente.)
     *
     * @param n El valor numérico inicial
     * @param precision Cantidad de digitos decimales a generarse
     * @param ancho Ancho deseado
     * @return El String de ancho especificado rellenado con ceros a la izquierda
     */
    public static String anchoFijo(BigDecimal n, int precision, int ancho) {

        return anchoFijo(n.scaleByPowerOfTen(precision).toBigInteger(), ancho);
    }

    /** Retorna un String de ancho deseado generado agregando ceros a la
     * izquierda a un número proporcionado y con la precision especificada. Si
     * el número inicial es menor que el ancho deseado, éste es truncado empezando
     * desde la posición final (y el valor se corrompe totalmente.)
     *
     * @param n El valor numérico inicial
     * @param ancho Ancho deseado
     * @return El String de ancho especificado rellenado con ceros a la izquierda
     */
    public static String anchoFijo(BigInteger integer, int ancho) {

        return anchoFijoNumerico("" + integer, ancho);
    }

    public static String anchoFijo(double n, int precision, int ancho) {

        return anchoFijo(new BigDecimal(n), precision, ancho);
    }

}