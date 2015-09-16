package estrategias.agentes.minimax;

import heuristicos.evaluadores.Evaluador;

import java.util.HashMap;
import java.util.Map;

import juegos.EstadoJuego;
import juegos.util.Ficha;
import estrategias.agentes.Jugador;
import estrategias.agentes.estadisticas.EstadisticasEvaluacion;
import estrategias.agentes.estadisticas.EstadisticasJugador;
import estrategias.util.EstadoValor;

/**
 * Estrategia minimax con límite de profundidad en la búsqueda.
 * Este jugador evalua los estados de un juego hasta una determinada profundidad.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 16/08/2011
 *
 */
public class JugadorMinimaxEstadisticas extends JugadorMinimax implements EstadisticasJugador {
	
	// ATRIBUTOS ESTADISTICAS
	private Map<Integer, EstadisticasEvaluacion> estadisticas;
	private int numMovimientos;
	private long tiempoTotal;
	
	/**
	 * Constructor.
	 * 
	 * @param ev					Evaluador heurístico.
	 * @param profundidadMaxima		Profundidad máxima de búsqueda.
	 */
	public JugadorMinimaxEstadisticas(Evaluador ev, int profundidadMaxima) {
		super(ev, profundidadMaxima);
		inicializarEstadisticas();
	}
	
	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		numMovimientos++;
		long tiempo = System.currentTimeMillis();
		EstadoValor mejorMovimiento = negamax(e, getProfMax(), e.fichaActual(), e.fichaOtro(), 1);
		tiempoTotal += System.currentTimeMillis() - tiempo;
		setMejorMovimiento(mejorMovimiento);
		return mejorMovimiento.getEstado();
	}
	
	@Override
	public EstadoValor negamax(EstadoJuego e, int prof, Ficha fmax, Ficha fmin, int signo) {
		EstadoJuego mejorE = null;
		double mejorV = Double.NEGATIVE_INFINITY;
		double v2;	
		/* Estadísticas */
		//double evaluacionNodo = signo*PruebasDefinitivas.evaluarEstado((ConectaK) e);
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
			for (EstadoJuego e2 : barajar(e.hijos())) {
			//for (EstadoJuego e2 : e.hijos()) {
				v2 = -negamax(e2, prof-1, fmax, fmin, -signo).getValor();
				if (v2 >= mejorV) {
					mejorV = v2;
					mejorE = e2;
				}
			}
		}
		ee.setEvaluacion(mejorV);
		estadisticas.put(nivel, ee);
		EstadoValor mejorMovimiento = new EstadoValor(mejorE, mejorV);
		return mejorMovimiento;
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

	@Override
	public void inicializarEstadisticas() {
		estadisticas = new HashMap<Integer, EstadisticasEvaluacion>();
		numMovimientos = 0;
		tiempoTotal = 0;
	}	
}
