package estrategias.agentes.estadisticas;

/**
 * Clase EstadisticasEvaluacion.
 * Representa objetos que contienen estadisticas de una determinada búsqueda en un árbol de juego.
 * 
 * 
 * @author José Miguel Horcas Aguilera
 *
 */
public class EstadisticasEvaluacion {

	private int profundidad;				// Profundidad actual de búsqueda.
	private int nNodos;						// Número de nodos a esa profundidad.
	private Double evaluacion;				// Evaluación del mejor nodo la profundidad actual.
	
	/**
	 * Constructor.
	 * 
	 * @param profundidad		Profundidad actual de búsqueda.
	 * @param nNodos			Número de nodos examinados actualmente.
	 */
	public EstadisticasEvaluacion(int profundidad, int nNodos) {
		this.profundidad = profundidad;
		this.nNodos = nNodos;
		this.evaluacion = null;
	}

	/**
	 * @return the profundidad
	 */
	public int getProfundidad() {
		return profundidad;
	}

	/**
	 * @return the nNodos
	 */
	public int getnNodos() {
		return nNodos;
	}

	/**
	 * @param profundidad the profundidad to set
	 */
	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}

	/**
	 * @param nNodos the nNodos to set
	 */
	public void setnNodos(int nNodos) {
		this.nNodos = nNodos;
	}

	public Double getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(Double evaluacion) {
		this.evaluacion = evaluacion;
	}
	
	
}
