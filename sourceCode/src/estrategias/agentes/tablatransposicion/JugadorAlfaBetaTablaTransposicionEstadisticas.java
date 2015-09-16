package estrategias.agentes.tablatransposicion;

import heuristicos.evaluadores.Evaluador;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import juegos.EstadoJuego;
import juegos.util.Ficha;
import estrategias.agentes.estadisticas.EstadisticasEvaluacion;
import estrategias.agentes.estadisticas.EstadisticasJugador;
import estrategias.util.EstadoValor;



/**
 * Estrategia minimax con poda alfa-beta, límite de profundidad en la búsqueda y tabla de transposición.
 * Este jugador realiza una búsqueda con poda alfa-beta y evalua los estados de un juego hasta una determinada profundidad.
 * Hace uso de una tabla de transposición para detectar los estados que ya han sido visitados y evaluados
 * anteriormente por otro camino.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 18/08/2011
 *
 */
public class JugadorAlfaBetaTablaTransposicionEstadisticas extends JugadorAlfaBetaTablaTransposicion implements EstadisticasJugador {

	// ATRIBUTOS ESTADISTICAS
	private Map<Integer, EstadisticasEvaluacion> estadisticas;
	private int numMovimientos;
	private long tiempoTotal;
	private int tamTablaTotal;
	private int numTransposiciones;
	
	/**
	 * Crea un jugador alfa-beta con un evaluador, una profundidad máxima de búsqueda y
	 * una tabla de transposición inicialmente vacía.
	 * 
	 * @param ev				Evaluador.
	 * @param profundidadMaxima	Profundidad máxima de búsqueda.
	 */
	public JugadorAlfaBetaTablaTransposicionEstadisticas(Evaluador ev, int profundidadMaxima) {
		super(ev, profundidadMaxima);
		inicializarEstadisticas();
	}
	
	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		numMovimientos++;
		long tiempo = System.currentTimeMillis();
		tt = new TablaTransposicion();
		EstadoValor mejorMovimiento = podaAlfaBeta(e, getProfMax(), e.fichaActual(), e.fichaOtro(), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 1);
		setMejorMovimiento(mejorMovimiento);
		tiempoTotal += System.currentTimeMillis() - tiempo;
		tamTablaTotal += tt.tamanyo();
		return mejorMovimiento.getEstado();
	}
	
	/**
	 * Realiza una búsqueda hasta la profundidad indicada con poda alfa-beta incluida.
	 * Devuelve el mejor movimiento posible junto con su evaluación.
	 * 
	 * @param e			Estado inicial del juego.
	 * @param prof		Profundidad máxima de búsqueda.
	 * @param fmax		Ficha del jugador MAX.
	 * @param fmin		Ficha del jugador MIN.
	 * @param alfa		Valor de corte alfa.
	 * @param beta		Valor de corte beta.
	 * @param signo		Tomará inicialmente el valor 1, e irá alternando de signo con la profundidad.
	 * @return			Mejor movimiento y su evaluación (objeto de la clase EstadoValor).
	 */
	public EstadoValor podaAlfaBeta(EstadoJuego e, int prof, Ficha fmax, Ficha fmin, double alfa, double beta, int signo) {
		EstadoJuego mejorE = null;
		double mejorV = Double.NEGATIVE_INFINITY;
		double v2;
		EstadoJuego e2 = null;
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
			Iterator<EstadoJuego> i = barajar(e.hijos()).iterator();
			//Iterator<EstadoJuego> i = e.hijos().iterator();
			while (i.hasNext() && beta > alfa) {
				e2 = i.next();
				v2 = -podaAlfaBeta(e2, prof-1, fmax, fmin, -beta, -alfa, -signo).getValor();
				alfa = Math.max(alfa, v2);
				if (v2 >= mejorV) {
					mejorV = v2;
					mejorE = e2;
				}
			}
			if (i.hasNext()) {	// Se ha producido poda.
				//No debe escoger ningún hijo de esta rama porque puede ser PEOR.
				mejorV = Double.POSITIVE_INFINITY;
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
