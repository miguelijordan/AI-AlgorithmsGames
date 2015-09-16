package estrategias.agentes.alfabeta;

import heuristicos.evaluadores.Evaluador;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import juegos.EstadoJuego;
import juegos.util.Ficha;
import estrategias.agentes.estadisticas.EstadisticasJugador;
import estrategias.agentes.estadisticas.EstadisticasEvaluacion;
import estrategias.util.EstadoValor;

/**
 * Jugador que realiza una búsqueda de la mejor jugada con poda alfa-beta,
 * y almacena las estadísticas de la búsqueda.
 * 
 * @author José Miguel Horcas Aguilera
 *
 */
public class JugadorAlfaBetaEstadisticas extends JugadorAlfaBeta implements EstadisticasJugador {

	// ATRIBUTOS
	private Map<Integer, EstadisticasEvaluacion> estadisticas;
	private long tiempoTotal;
	private int numMovimientos;
	
	/**
	 * Constructor.
	 * 
	 * @param ev					Evaluador heurístico.
	 * @param profundidadMaxima		Profundidad máxima de búsqueda.
	 */
	public JugadorAlfaBetaEstadisticas(Evaluador ev, int profundidadMaxima) {
		super(ev, profundidadMaxima);
		inicializarEstadisticas();
	}

	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		numMovimientos++;
		long tiempo = System.currentTimeMillis();
		EstadoValor mejorMovimiento = podaAlfaBeta(e, this.getProfMax(), e.fichaActual(), e.fichaOtro(), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 1);
		setMejorMovimiento(mejorMovimiento);
		tiempoTotal += System.currentTimeMillis() - tiempo;
		return mejorMovimiento.getEstado();
	}
	
	@Override
	public EstadoValor podaAlfaBeta(EstadoJuego e, int prof, Ficha fmax, Ficha fmin, double alfa, double beta, int signo) {
		EstadoJuego mejorE = null;
		double mejorV = Double.NEGATIVE_INFINITY;
		double v2;
		EstadoJuego e2 = null;
		/* Estadísticas */
		//double evaluacionNodo = -PruebasDefinitivas.evaluarEstado((ConectaK) e);
		int nivel = getProfMax() - prof;
		EstadisticasEvaluacion ee = estadisticas.get(nivel);
		if (ee == null) {
			ee = new EstadisticasEvaluacion(nivel, 1);
			//ee.setEvaluacion(evaluacionNodo);
		} else {
			ee.setnNodos(ee.getnNodos()+1);
			/*if (evaluacionNodo > ee.getEvaluacion()) {
				ee.setEvaluacion(evaluacionNodo);
			}*/
		}
		//estadisticas.put(nivel, ee);
		/* Fin estadísticas */
		
		if (prof == 0 || e.ganador()!=null || e.agotado()) {
			mejorV = signo*evalua(e, fmax, fmin);
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
		}
		ee.setEvaluacion(mejorV);
		estadisticas.put(nivel, ee);
		EstadoValor mejorMovimiento = new EstadoValor(mejorE, mejorV);
		return mejorMovimiento;
	}		
	
	/**
	 * Inicializa las estadísticas.
	 */
	public void inicializarEstadisticas() {
		estadisticas = new HashMap<Integer, EstadisticasEvaluacion>();
		numMovimientos = 0;
		tiempoTotal = 0;
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
	
}
