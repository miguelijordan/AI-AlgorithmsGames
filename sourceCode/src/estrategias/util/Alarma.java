package estrategias.util;

/**
 * Alarma que se activa pasado un tiempo indicado.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 18/08/2011
 *
 */
public class Alarma {

	/**
	 * Inicio de la alarma.
	 */
	private long inicio;
	
	/**
	 * Tiempo de la alarma en milisegundos. 
	 */
	private long tiempo;
	
	/**
	 * Crea una nueva alarma.
	 * 
	 * @param tiempo Tiempo.
	 */
	public Alarma(long tiempo) {
		this.inicio = System.currentTimeMillis();
		this.tiempo = tiempo*1000;
	}
	
	/**
	 * La alarma se activará después de s segundos con una precisión de (+ -) 1 seg.
	 * 
	 * @param s Número de segundos.
	 */
	public void conectar(long s) {
		inicio = System.currentTimeMillis();
		tiempo = s*1000+1000;
	}
	
	/**
	 * ¿Se activó ya la alarma?
	 * 
	 * @return Verdadero si se activó ya la alarma; falso en caso contrario.
	 */
	public boolean alarma() {
		return tiempo <= System.currentTimeMillis()-inicio;
	}
}
