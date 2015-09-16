package estrategias.agentes.montecarlotreesearch;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import juegos.EstadoJuego;
import estrategias.agentes.Jugador;
import estrategias.agentes.aleatorio.JugadorAleatorio;
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
public class JugadorMonteCarloTreeSearch implements Jugador {

	// CONSTANTES
	/**
	 * Número de simulaciones por defecto.
	 */
	public static final int N_SIMULACIONES = 100;
	
	/**
	 * Constante de exploración.
	 */
	public static final double CONSTANTE_EXPLORACION = 0.5;
	
	// VARIABLES DE CLASE
	/**
	 * Estrategia aleatoria para realizar las simulaciones.
	 */
	private static Jugador jugadorSimulador = new JugadorAleatorio();
	
	/**
	 * Generador de números aleatorios.
	 */
	private static Random rd = new Random();
	
	// ATRIBUTOS
	/**
	 * Número de simulaciones.
	 */
	protected int nSimulaciones;
	
	private double constante_exploracion;
	
	/**
	 * Tabla hash para almacenar los nodos del árbol.
	 */
	protected TablaHash<NodoMC> tree;

	/**
	 * Último estado añadido al árbol y 
	 * a partir del cual se realiza las simulaciones con la estrategia por defecto (aleatoria).
	 */
	protected EstadoJuego ultimoNuevoEstado;
	
	/**
	 * Indica si se debe mantener el árbol en cada movimiento y por tanto en cada partida o
	 * se genera un árbol nuevo con cada movimiento.
	 */
	protected boolean reutilizarArbol;
	
	/**
	 * Crea un nuevo jugador que emplea el método de Monte-Carlo Tree Search.
	 * Realiza N_SIMULACIONES por defecto.
	 * 
	 * @param nSimulaciones	Número de simulaciones.
	 */
	public JugadorMonteCarloTreeSearch(int nSimulaciones) {
		this.nSimulaciones = nSimulaciones;
		constante_exploracion = CONSTANTE_EXPLORACION;
		ultimoNuevoEstado = null;
		reutilizarArbol = false;
		tree = new TablaHash<NodoMC>();
	}
	
	/**
	 * Crea un nuevo jugador que emplea el método de Monte-Carlo Tree Search.
	 * 
	 * @param nSimulaciones		Número de simulaciones por movimiento.
	 * @param exploracion		Constante de exploración.
	 * @param reutilizarArbol	Indica si se debe o no reutilizar el árbol en cada movimiento.
	 */
	public JugadorMonteCarloTreeSearch(int nSimulaciones, double exploracion, boolean reutilizarArbol) {
		this.nSimulaciones = nSimulaciones;
		this.constante_exploracion = exploracion;
		this.reutilizarArbol = reutilizarArbol;
		ultimoNuevoEstado = null;
		tree = new TablaHash<NodoMC>();
	}
	
	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		if (!reutilizarArbol) {
			ultimoNuevoEstado = null;
			tree = new TablaHash<NodoMC>();	
		}
		for (int i = 1; i <= nSimulaciones; i++) {
			simular(e);
		}
		EstadoJuego m = seleccionaMovimiento(e, 0);
		return m;
	}
	
	/**
	 * Realiza una simulación completa a partir del estado s hasta terminar la partida.
	 * 
	 * @param s	Estado inicial.
	 * @return	Resultado de la partida: 1 si gana el jugador 1, -1 si gana el jugador 2, 0 en caso de empate.
	 */
	public int simulacion(EstadoJuego s) {
		int z = 0;
		
		while (s.ganador() == null && !s.agotado()) {
			s = jugadorSimulador.mueve(s);
		}
		if (s.ganador()!=null) {
			z = s.jug1() ? -1 : 1;
		} else if (s.agotado()) {
			z = 0;
		}
		return z;
	}
	
	/**
	 * Realiza una simulación completa realizando primero una búsqueda en el árbol
	 * hasta encontrar un nuevo estado que añadir y 
	 * a partir de ese estado usa la política por defecto.
	 * 
	 * @param s	Estado inicial.
	 */
	public void simular(EstadoJuego s) {
		Map<EstadoJuego, EstadoJuego> estadosVisitadosAcciones = simulacionArbol(s);
		int z = simulacion(ultimoNuevoEstado);
		backup(estadosVisitadosAcciones, z);
	}
	
	/**
	 * Realiza una simulación completa o hasta que encuentre un nodo que no se encuentra almacenado en el árbol.
	 * 
	 * @param s	Estado inicial.
	 * @return	Estados visitados durante la simulación.
	 */
	public Map<EstadoJuego, EstadoJuego> simulacionArbol(EstadoJuego s) {
		Map<EstadoJuego, EstadoJuego> estadosVisitadosAcciones = new HashMap<EstadoJuego, EstadoJuego>();
		
		while (s.ganador() == null && !s.agotado() && tree.esta(s)) {
			EstadoJuego s2 = seleccionaMovimiento(s, constante_exploracion);
			estadosVisitadosAcciones.put(s, s2);
			s = s2;
		}
		if (s.ganador() != null || s.agotado()) {
			this.ultimoNuevoEstado = s;
		} else if (!tree.esta(s)) {
			tree.setNodo(s, new NodoMC(s));
			NodoMC n = tree.getNodo(s);
			estadosVisitadosAcciones.put(s, n.getA().get(rd.nextInt(n.getA().size())));
			this.ultimoNuevoEstado = estadosVisitadosAcciones.get(s);
		}
		return estadosVisitadosAcciones;
	}
	
	/**
	 * Actualiza la información de los estados visitados en el árbol durante la simulación.
	 * 
	 * @param estadosVisitadosAcciones	Estados visitados y movimiento realizado (estado resultante).
	 * @param z							Resultado de la simulación (1: gana jugador 1, -1: gana jugador 2, 0: empate).
	 */
	public void backup(Map<EstadoJuego, EstadoJuego> estadosVisitadosAcciones, int z) {
		for (EstadoJuego s : estadosVisitadosAcciones.keySet()) {
			NodoMC n = tree.getNodo(s);
			n.setNs(n.getNs() + 1);
			int indiceA = n.getA().indexOf(estadosVisitadosAcciones.get(s));
			n.setNsa(indiceA, n.getNsa().get(indiceA) + 1);
			double qa = n.getQsa().get(indiceA);
			n.setQsa(indiceA,  qa + (z-qa)/n.getNsa().get(indiceA));	
		}	
	}
	
	/**
	 * Selecciona un movimiento teniendo en cuenta la información del estado dado almacenada en el árbol. 
	 * 
	 * @param s		Estado.
	 * @param c		Constante de exploración.
	 * @return		Mejor movimiento posible (mejor estado sucesor de s).
	 */
	public EstadoJuego seleccionaMovimiento(EstadoJuego s, double c) {
		EstadoJuego a = null;
		NodoMC n = tree.getNodo(s);
		if (s.jug1()) {
			a = n.argmax(c);
		} else {
			a = n.argmin(c);
		}
		return a;
	}
	
	@Override
	public String toString() {
		String res = "Monte-Carlo Tree Search.";
		res += "\nNº de simulaciones: " + nSimulaciones;
		res += "\nConstante de exploración: " + constante_exploracion;
		res += "\nSe reutiliza el árbol en cada movimiento? " + (reutilizarArbol ? "sí" : "no");
		return res;
	}
	
	

}
