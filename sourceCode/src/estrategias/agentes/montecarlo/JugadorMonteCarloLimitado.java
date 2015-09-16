package estrategias.agentes.montecarlo;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import estrategias.agentes.Jugador;
import estrategias.agentes.aleatorio.JugadorAleatorio;
import estrategias.util.Alarma;

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
public class JugadorMonteCarloLimitado implements Jugador {
	
	// VARIABLES DE CLASE
	/**
	 * Estrategia aleatoria para realizar las simulaciones.
	 */
	protected static Jugador jugadorSimulador = new JugadorAleatorio();
	
	public static final long TIEMPO_LIMITE = 1;
	/**
	 * Generador de números aleatorios.
	 */
	protected static Random rd = new Random();
	
	// ATRIBUTOS
	/**
	 * Límite de tiempo (en segundos).
	 */
	protected long limiteT;
	
	/**
	 * Crea un nuevo jugador que emplea el método de Monte-Carlo con tiempo limitado.
	 * 
	 * @param limiteT	Límite de tiempo (en segundos).
	 */
	public JugadorMonteCarloLimitado(long limiteT) {
		this.limiteT = limiteT;
	}
	
	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		Alarma a = new Alarma(limiteT);
		
		EstadoJuego mejorMovimiento = null;
		int jug = e.jug1() ? 1 : -1;
		List<EstadoJuego> hijos = e.hijos();
		int[] zi = new int[hijos.size()];
		int[] ni = new int[hijos.size()];	// Número de simulaciones de cada hijo.
		
		while (!a.alarma()) {
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
	private int mejorEstado(int[] zi, int[] ni) {
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
		String res = "Monte-Carlo Limitado";
		res += "\nTiempo límite: " + limiteT + " segundos.";
		return res;
	}
}
