package estrategias.agentes.evaluar;

import heuristicos.evaluadores.Evaluador;

import java.util.Collections;
import java.util.List;

import juegos.EstadoJuego;
import juegos.util.Ficha;
import estrategias.agentes.estadisticas.EstadisticasJugador;
import estrategias.util.EstadoValor;


/**
 * Agente capaz de evaluar heurísticamente los estados de un juego.
 * Este jugador emplea un objeto evaluador para determinar si una situación dada le es favorable o no.
 * El agente emplea la siguiente estrategia de juego: "dada una situación, considera todos los movimientos inmediatos,
 * los evalúa heurísticamente y escoge el mejor."
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 16/08/2011
 *
 */
public class JugadorEvaluarEstadisticas extends JugadorEvaluar implements EstadisticasJugador {
	
	private int numMovimientos;
	private long tiempoTotal;
	
	/**
	 * Crea un agente evaluador heurístico a partir de la función de evaluación heurística ev.
	 * 
	 * @param ev	Función de evaluación.
	 */
	public JugadorEvaluarEstadisticas(Evaluador ev) {
		super(ev);
		inicializarEstadisticas();
	}
	
	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		numMovimientos++;
		long tiempo = System.currentTimeMillis();
		EstadoJuego mejorE = null;
		double mejorV = Double.NEGATIVE_INFINITY;
		double v2;
		
		//for (EstadoJuego e2 : e.hijos()) {
		for (EstadoJuego e2 : barajar(e.hijos())) {
			v2 = evalua(e2, e.fichaActual(), e.fichaOtro());
			if (v2 >= mejorV) {
				mejorV = v2;
				mejorE = e2;
			}
		}
		setMejorMovimiento(new EstadoValor(mejorE, mejorV));
		tiempoTotal += System.currentTimeMillis() - tiempo;
		return mejorE;
	}

	@Override
	public String getEstadisticas() {
		return null;
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
		numMovimientos = 0;
		tiempoTotal = 0;
	}
}
