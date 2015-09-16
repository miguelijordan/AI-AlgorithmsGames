package estrategias.agentes.montecarlotreesearch;

import juegos.EstadoJuego;
import estrategias.agentes.estadisticas.EstadisticasJugador;
import estrategias.agentes.estadisticas.Util;
import estrategias.util.TablaHash;

/**
 * Agente que realiza una búsqueda en árbol mediante el método de Monte-Carlo (Monte-Carlo Tree Search).
 * Realiza un número determinado de simulaciones a partir del estado actual para decidir el próximo movimiento.
 * En cada simulación almacena un nuevo estado en el árbol y se actualiza la información de todos los nodos visitados durante la simulación.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 24/08/2011
 *
 */
public class JugadorMonteCarloTreeSearchEstadisticas extends JugadorMonteCarloTreeSearch implements EstadisticasJugador {
	
	// ATRIBUTOS ESTADISTICAS
	private int numMovimientos;
	private long tiempoTotal;
	private long tiempoSimulacion;
		
		
	/**
	 * Crea un nuevo jugador que emplea el método de Monte-Carlo Tree Search.
	 * Realiza N_SIMULACIONES por defecto.
	 * 
	 * @param nSimulaciones	Número de simulaciones.
	 */
	public JugadorMonteCarloTreeSearchEstadisticas(int nSimulaciones) {
		super(nSimulaciones);
		inicializarEstadisticas();
	}
	
	/**
	 * Crea un nuevo jugador que emplea el método de Monte-Carlo Tree Search.
	 * 
	 * @param nSimulaciones		Número de simulaciones por movimiento.
	 * @param exploracion		Constante de exploración.
	 * @param reutilizarArbol	Indica si se mantiene el árbol de MCTS o no de un movimiento a otro.
	 */
	public JugadorMonteCarloTreeSearchEstadisticas(int nSimulaciones, double exploracion, boolean reutilizarArbol) {
		super(nSimulaciones, exploracion, reutilizarArbol);
		inicializarEstadisticas();
	}
	
	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		numMovimientos++;
		long tiempo = System.currentTimeMillis();
		
		if (!reutilizarArbol) {
			ultimoNuevoEstado = null;
			tree = new TablaHash<NodoMC>();	
		}
		for (int i = 1; i <= nSimulaciones; i++) {
			long tiempoSim = System.currentTimeMillis();
			simular(e);
			tiempoSimulacion += System.currentTimeMillis() - tiempoSim;
		}
		EstadoJuego m = seleccionaMovimiento(e, 0);
		
		tiempoTotal += System.currentTimeMillis() - tiempo;
		return m;
	}

	@Override
	public String getEstadisticas() {
		String res = "Tiempo medio de cada simulación: ";
		res += Util.formatearTiempo(tiempoSimulacion/(numMovimientos*nSimulaciones));
		res += "\nNúmero de nodos en el árbol: " + tree.tamanyo();
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
		ultimoNuevoEstado = null;
		tree = new TablaHash<NodoMC>();
	}	

}
