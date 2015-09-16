package estrategias.agentes.montecarlo;

import java.util.List;
import java.util.Random;

import estrategias.agentes.Jugador;
import estrategias.agentes.aleatorio.JugadorAleatorio;

import juegos.EstadoJuego;
import juegos.Juego;

/**
 * Agente que realiza una búsqueda en árbol mediante el método de Monte-Carlo.
 * Realiza un número determinado de simulaciones a partir del estado actual para decidir el próximo movimiento.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 24/08/2011
 *
 */
public class JugadorMonteCarlo implements Jugador {

	// CONSTANTES
	/**
	 * Número de simulaciones por defecto.
	 */
	public static final int N_SIMULACIONES = 1000;
	
	// VARIABLES DE CLASE
	/**
	 * Estrategia aleatoria para realizar las simulaciones.
	 */
	protected static Jugador jugadorSimulador = new JugadorAleatorio();
	
	/**
	 * Generador de números aleatorios.
	 */
	protected static Random rd = new Random();
	
	// ATRIBUTOS
	/**
	 * Número de simulaciones.
	 */
	protected int nSimulaciones;
	
	/**
	 * Crea un nuevo jugador que emplea el método de Monte-Carlo.
	 * Realiza N_SIMULACIONES por defecto.
	 */
	public JugadorMonteCarlo() {
		this.nSimulaciones = N_SIMULACIONES;
	}
	
	/**
	 * Crea un nuevo jugador que emplea el método de Monte-Carlo.
	 * 
	 * @param nSimulaciones	Número de simulaciones por movimiento.
	 */
	public JugadorMonteCarlo(int nSimulaciones) {
		this.nSimulaciones = nSimulaciones;
	}
	
	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		EstadoJuego mejorMovimiento = null;
		int jug = e.jug1() ? 1 : -1;
		List<EstadoJuego> hijos = e.hijos();
		int[] zi = new int[hijos.size()];
		int[] ni = new int[hijos.size()];	// Número de simulaciones de cada hijo.
		
		for (int i = 1; i <= nSimulaciones; i++) {
			int nh = rd.nextInt(hijos.size());
			EstadoJuego h = hijos.get(nh);
			int z = Juego.jugarIter(jugadorSimulador, jugadorSimulador, h, false);
			ni[nh]++;
			zi[nh] += z*jug;
		}
		mejorMovimiento = hijos.get(mejorEstado(zi, ni));
		return mejorMovimiento;
	}
	
	/**
	 * Calcula el mejor movimiento posible a partir de los resultados de las simulaciones.
	 * 
	 * @param zi	Valores resultados de las simulaciones para cada posible movimiento. 
	 * @param ni	Número de simulaciones realizadas para cada posible movimiento.
	 * @return		&Iacute;ndice del mejor movimiento.
	 */
	protected int mejorEstado(int[] zi, int[] ni) {
		int mayor = 0;
		double prMayor = Double.NEGATIVE_INFINITY;
	
		for (int i = 0; i < zi.length; i++) {
			double pr = (double)zi[i] / (double)ni[i];
			if (pr > prMayor) {
				mayor = i;
				prMayor = pr;
			}
		}
		return mayor;
	}
	
	@Override
	public String toString() {
		String res = "Monte-Carlo.";
		res += "\nNº de simulaciones: " + nSimulaciones;
		return res;
	}
}
