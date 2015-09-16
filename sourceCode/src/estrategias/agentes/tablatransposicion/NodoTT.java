package estrategias.agentes.tablatransposicion;

/**
 * Nodo que contiene información para una tabla de transposición.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 16/08/2011
 *
 */
public class NodoTT {
		
	/**
	 * Valor almacenado.
	 */
	private double v;
		
	/**
	 * Crea un nodo nuevo.
	 * 
	 * @param v		Valor a almacenar.
	 */
	public NodoTT(double v) {
		this.v = v;
	}

	/**
	 * @return	Valor almacenado.
	 */
	public double getValor() {
		return v;
	}

	/**
	 * Actualiza el valor actual por el nuevo que se pasa como parámetro.
	 * 
	 * @param v	Nuevo valor a almacenar.
	 */
	public void setValor(double v) {
		this.v = v;
	}
}