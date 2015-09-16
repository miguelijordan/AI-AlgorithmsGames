package estrategias.agentes.tablatransposicion;

import heuristicos.evaluadores.Evaluador;

import java.util.HashMap;
import java.util.Map;

import juegos.EstadoJuego;
import juegos.util.Ficha;
import estrategias.agentes.estadisticas.EstadisticasEvaluacion;
import estrategias.agentes.estadisticas.EstadisticasJugador;
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
public class JugadorMinimaxTablaTransposicionEstadisticas extends JugadorMinimaxTablaTransposicion implements EstadisticasJugador {
	
	// ATRIBUTOS ESTADISTICAS
	private Map<Integer, EstadisticasEvaluacion> estadisticas;
	private int numMovimientos;
	private long tiempoTotal;
	private int tamTablaTotal;
	private int numTransposiciones;
		
		
	/**
	 * Crea un jugador minimax con un evaluador, una profundidad máxima de búsqueda y
	 * una tabla de transposición inicialmente vacía.
	 * 
	 * @param ev				Evaluador.
	 * @param profundidadMaxima	Profundidad máxima de búsqueda.
	 */
	public JugadorMinimaxTablaTransposicionEstadisticas(Evaluador ev, int profundidadMaxima) {
		super(ev, profundidadMaxima);
		inicializarEstadisticas();
	}
	
	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		numMovimientos++;
		long tiempo = System.currentTimeMillis();
		tt = new TablaTransposicion();
		EstadoValor mejorMovimiento = negamax(e, getProfMax(), e.fichaActual(), e.fichaOtro(), 1);
		setMejorMovimiento(mejorMovimiento);
		tiempoTotal += System.currentTimeMillis() - tiempo;
		tamTablaTotal += tt.tamanyo();
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
		/* Estadísticas */
		int nivel = getProfMax() - prof;
		EstadisticasEvaluacion ee = estadisticas.get(nivel);
		if (ee == null) {
			ee = new EstadisticasEvaluacion(nivel, 1);
		} else {
			ee.setnNodos(ee.getnNodos()+1);
		}
		/* Fin estadísticas */
		
		if (prof == 0 || e.ganador()!=null || e.agotado()) {
			mejorV = signo*evalua(e, fmax, fmin);
		} else if (tt.esta(e)) {		// Si esta en la tabla cojo la información almacenada.
			numTransposiciones++;
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
		ee.setEvaluacion(mejorV);
		estadisticas.put(nivel, ee);
		return new EstadoValor(mejorE, mejorV);
	}

	@Override
	public String getEstadisticas() {
		String res = "Prof. Nº medio de nodos examinados\n";
		int total = 0;
		
		for (int i = 0; i <= estadisticas.size()-1; i++) {
			EstadisticasEvaluacion ee = estadisticas.get(i);
			res += ee.getProfundidad();
			res += "      " + Math.round((double)ee.getnNodos() / (double)numMovimientos);
			res += "\n";
			total += ee.getnNodos();
		}
		res += "\nNº total de nodos examinados: " + total + "\n";
		res += "Tamaño medio de la tabla de transposición en cada movimiento: " + tamTablaTotal / numMovimientos + " nodos.";
		res += "\nNº total de transposiciones encontradas: " + numTransposiciones; 
		return res;
	}

	@Override
	public long tiempoMedioPorMovimiento() {
		return tiempoTotal / numMovimientos;
	}

	@Override
	public int numTotalMovimientos() {
		return numMovimientos;
	}

	@Override
	public void inicializarEstadisticas() {
		estadisticas = new HashMap<Integer, EstadisticasEvaluacion>();
		numMovimientos = 0;
		tiempoTotal = 0;
		tamTablaTotal = 0;
		numTransposiciones = 0;
	}
}
