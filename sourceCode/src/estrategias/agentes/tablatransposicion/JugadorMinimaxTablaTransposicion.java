package estrategias.agentes.tablatransposicion;

import heuristicos.evaluadores.Evaluador;
import juegos.EstadoJuego;
import juegos.util.Ficha;
import estrategias.agentes.evaluar.JugadorEvaluar;
import estrategias.util.EstadoValor;

/**
 * Estrategia minimax con límite de profundidad en la búsqueda y tabla de transposición.
 * Este jugador evalúa los estados de un juego hasta una determinada profundidad y
 * hace uso de una tabla de transposición para detectar los estados que ya han sido visitados y evaluados
 * anteriormente por otro camino.
 * 
 * @author José Miguel Horcas Aguilera.
 * @version 2.00, 16/08/2011
 *
 */
public class JugadorMinimaxTablaTransposicion extends JugadorEvaluar {

	/**
	 * Profundidad máxima de búsqueda.
	 */
	private int profMax;
	
	/**
	 * Tabla de transposición.
	 */
	protected TablaTransposicion tt;
	
	/**
	 * Crea un jugador minimax con un evaluador, una profundidad máxima de búsqueda y
	 * una tabla de transposición inicialmente vacía.
	 * 
	 * @param ev				Evaluador.
	 * @param profundidadMaxima	Profundidad máxima de búsqueda.
	 */
	public JugadorMinimaxTablaTransposicion(Evaluador ev, int profundidadMaxima) {
		super(ev);
		this.profMax = profundidadMaxima;
		this.tt = new TablaTransposicion();
	}
	
	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		tt = new TablaTransposicion();
		EstadoValor mejorMovimiento = negamax(e, profMax, e.fichaActual(), e.fichaOtro(), 1);
		setMejorMovimiento(mejorMovimiento);
		return mejorMovimiento.getEstado();
	}
	
	/**
	 * Realiza una evaluación MINIMAX de un estado dado a una profundidad dada.
	 * Devuelve el mejor movimiento posible junto con su evaluación.
	 * 
	 * @param e		Estado del juego.
	 * @param prof	Profundiad de la búsqueda.
	 * @param fmax	Ficha del jugador MAX.
	 * @param fmin	Ficha del jugador MIN.
	 * @param signo	Tomará inicialmente el valor 1, e irá alternando de signo con la profundidad.
	 * @return		Mejor movimiento y su evaluación (objeto de la clase EstadoValor).
	 */
	public EstadoValor negamax(EstadoJuego e, int prof, Ficha fmax, Ficha fmin, int signo) {
		EstadoJuego mejorE = null;
		double mejorV = Double.NEGATIVE_INFINITY;
		double v2;

		if (prof == 0 || e.ganador()!=null || e.agotado()) {
			mejorV = signo*evalua(e, fmax, fmin);
		} else if (tt.esta(e)) {		// Si esta en la tabla cojo la información almacenada.
			EstadoValor n = tt.getNodo(e);
			mejorV = n.getValor();
			mejorE = n.getEstado();
		} else {
			for (EstadoJuego e2 : barajar(e.hijos())) {
			//for (EstadoJuego e2 : e.hijos()) {
				v2 = -negamax(e2, prof-1, fmax, fmin, -signo).getValor();
				if (v2 >= mejorV) {
					mejorV = v2;
					mejorE = e2;
				}
			}
			// Añado el mejor valor y el mejor movimiento para e en la tabla de transposición.
			EstadoValor n = new EstadoValor(mejorE, mejorV);
			tt.setNodo(e, n);
		}
		return new EstadoValor(mejorE, mejorV);
	}

	/**
	 * @return Profundidad máxima de búsqueda.
	 */
	public int getProfMax() {
		return profMax;
	}
	
	@Override
	public String toString() {
		String res = "Minimax con tabla de transposición.";
		res += "\nProfundidad máxima de búsqueda: " + profMax;
		res += "\n" + getEvaluador().toString();
		return res;
	}
}
