package estrategias.agentes.montecarlotreesearch;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import juegos.EstadoJuego;
import estrategias.agentes.Jugador;
import estrategias.agentes.aleatorio.JugadorAleatorio;
import estrategias.agentes.estadisticas.EstadisticasJugador;
import estrategias.agentes.estadisticas.Util;
import estrategias.util.Alarma;
import estrategias.util.TablaHash;

/**
 * Agente que realiza una búsqueda en árbol mediante el método de Monte-Carlo (Monte-Carlo Tree Search).
 * Realiza simulaciones a partir del estado actual durante un tiempo limitado para decidir el próximo movimiento.
 * En cada simulación almacena un nuevo estado en el árbol y se actualiza la información de todos los nodos visitados durante la simulación.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 24/08/2011
 *
 */
public class JugadorMonteCarloTreeSearchLimitadoEstadisticas extends JugadorMonteCarloTreeSearchLimitado implements EstadisticasJugador {
	
	// ATRIBUTOS ESTADISTICAS
	private int numMovimientos;
	private int nSimulaciones;
	private long tiempoSimulacion;
	private long tiempoTotal;
	
	/**
	 * Crea un nuevo jugador que emplea el método de Monte-Carlo Tree Search con tiempo limitado.
	 * 
	 * @param limiteT	Límite de tiempo (en segundos).
	 */
	public JugadorMonteCarloTreeSearchLimitadoEstadisticas(long limiteT) {
		super(limiteT);
		inicializarEstadisticas();
	}
	
	/**
	 * Crea un nuevo jugador que emplea el método de Monte-Carlo Tree Search con tiempo limitado.
	 * 
	 * @param limiteT			Límite de tiempo (en segundos).
	 * @param exploracion 		Constante de exploración.
	 * @param reutilizarArbol	Indica si se debe o no reutilizar el árbol en cada movimiento.
	 */
	public JugadorMonteCarloTreeSearchLimitadoEstadisticas(long limiteT, double exploracion, boolean reutilizarArbol) {
		super(limiteT, exploracion, reutilizarArbol);
		inicializarEstadisticas();
	}
	
	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		numMovimientos++;
		long tiempo = System.currentTimeMillis();
		
		Alarma a = new Alarma(limiteT);
		
		if (!reutilizarArbol) {
			ultimoNuevoEstado = null;
			tree = new TablaHash<NodoMC>();	
		}
		
		while (!a.alarma()) {
			long tiempoSim = System.currentTimeMillis();
			simular(e);
			nSimulaciones++;
			tiempoSimulacion += System.currentTimeMillis() - tiempoSim;
		}
		//System.out.println(nSimulaciones);
		
		/*System.out.println("Estado actual: " + e.toString());
		NodoMC n = tree.getNodo(e);
		System.out.println("n(s): " + n.getNs());
		for (int i = 0; i < n.getNsa().size(); i++) {
			System.out.print("hijo: " + i);
			System.out.print(" ,n(s," + i + "): " + n.getNsa().get(i));
			System.out.print(" ,q(s," + i + "): " + n.getQsa().get(i));
			System.out.println();
		}*/
		EstadoJuego m = seleccionaMovimiento(e, 0);
		/*System.out.println("Mejor movimiento: " + m.toString());
		esperar();*/
		tiempoTotal += System.currentTimeMillis() - tiempo;
		return m;
	}

	private void esperar() {
		try {
			System.out.println("Press any key to continue...");
			System.in.read();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@Override
	public String getEstadisticas() {
		String res = "Número de simulaciones por movimiento: " + nSimulaciones / numMovimientos;
		res += "\nTiempo medio de cada simulación: ";
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
		nSimulaciones = 0;
		tiempoTotal = 0;
		tiempoSimulacion = 0;
		ultimoNuevoEstado = null;
		tree = new TablaHash<NodoMC>();
	}	
	
	
}
