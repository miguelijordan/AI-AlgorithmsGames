package estrategias.agentes.montecarlo;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import juegos.EstadoJuego;
import juegos.Juego;
import estrategias.agentes.Jugador;
import estrategias.agentes.aleatorio.JugadorAleatorio;
import estrategias.agentes.estadisticas.EstadisticasJugador;
import estrategias.agentes.estadisticas.Util;

/**
 * Agente que realiza una búsqueda en árbol mediante el método de Monte-Carlo.
 * Realiza un número determinado de simulaciones a partir del estado actual para decidir el próximo movimiento.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 24/08/2011
 *
 */
public class JugadorMonteCarloEstadisticas extends JugadorMonteCarlo implements EstadisticasJugador {

	// ATRIBUTOS ESTADISTICAS
	private int numMovimientos;
	private long tiempoTotal;
	private long tiempoSimulacion;
	
	/**
	 * Crea un nuevo jugador que emplea el método de Monte-Carlo.
	 * 
	 * @param nSimulaciones	Número de simulaciones por movimiento.
	 */
	public JugadorMonteCarloEstadisticas(int nSimulaciones) {
		super(nSimulaciones);
		inicializarEstadisticas();
	}
	
	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		numMovimientos++;
		long tiempo = System.currentTimeMillis();
		
		EstadoJuego mejorMovimiento = null;
		int jug = e.jug1() ? 1 : -1;
		List<EstadoJuego> hijos = e.hijos();
		int[] zi = new int[hijos.size()];
		int[] ni = new int[hijos.size()];	// Número de simulaciones de cada hijo.
		
		for (int i = 1; i <= nSimulaciones; i++) {
			int nh = rd.nextInt(hijos.size());
			EstadoJuego h = hijos.get(nh);
			long tiempoSim = System.currentTimeMillis();
			int z = Juego.jugarIter(jugadorSimulador, jugadorSimulador, h, false);
			tiempoSimulacion += System.currentTimeMillis() - tiempoSim;
			ni[nh]++;
			zi[nh] += z*jug;
		}
		mejorMovimiento = hijos.get(mejorEstado(zi, ni));
		
		tiempoTotal += System.currentTimeMillis() - tiempo;
		return mejorMovimiento;
	}

	@Override
	public String getEstadisticas() {
		String res = "Tiempo medio de cada simulación: ";
		res += Util.formatearTiempo(tiempoSimulacion/(numMovimientos*nSimulaciones));
		//res += Util.formatearTiempo(tiempoMedioPorMovimiento()/nSimulaciones);
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
		numMovimientos = 0;
		tiempoTotal = 0;
		tiempoSimulacion = 0;
	}	

}
