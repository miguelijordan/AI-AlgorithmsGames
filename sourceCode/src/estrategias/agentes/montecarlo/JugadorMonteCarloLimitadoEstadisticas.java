package estrategias.agentes.montecarlo;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import juegos.EstadoJuego;
import juegos.Juego;
import estrategias.agentes.Jugador;
import estrategias.agentes.aleatorio.JugadorAleatorio;
import estrategias.agentes.estadisticas.EstadisticasJugador;
import estrategias.agentes.estadisticas.Util;
import estrategias.util.Alarma;

/**
 * Agente que realiza una búsqueda en árbol mediante el método de Monte-Carlo.
 * Realiza un número determinado de simulaciones a partir del estado actual para decidir el próximo movimiento.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 24/08/2011
 *
 */
public class JugadorMonteCarloLimitadoEstadisticas extends JugadorMonteCarloLimitado implements EstadisticasJugador {
	
	// ATRIBUTOS ESTADISTICAS
	private int numMovimientos;
	private int nSimulaciones;
	private long tiempoSimulacion;
	private long tiempoTotal;
		
	/**
	 * Crea un nuevo jugador que emplea el método de Monte-Carlo con tiempo limitado.
	 * 
	 * @param limiteT	Límite de tiempo (en segundos).
	 */
	public JugadorMonteCarloLimitadoEstadisticas(long limiteT) {
		super(limiteT);
		inicializarEstadisticas();
	}
	
	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		numMovimientos++;
		long tiempo = System.currentTimeMillis();
		
		Alarma a = new Alarma(limiteT);
		
		EstadoJuego mejorMovimiento = null;
		int jug = e.jug1() ? 1 : -1;
		List<EstadoJuego> hijos = e.hijos();
		int[] zi = new int[hijos.size()];
		int[] ni = new int[hijos.size()];	// Número de simulaciones de cada hijo.
		
		while (!a.alarma()) {
			int nh = rd.nextInt(hijos.size());
			EstadoJuego h = hijos.get(nh);
			long tiempoSim = System.currentTimeMillis();
			int z = Juego.jugarIter(jugadorSimulador, jugadorSimulador, h, false);
			tiempoSimulacion += System.currentTimeMillis() - tiempoSim;
			ni[nh]++;
			zi[nh] += z*jug;
			nSimulaciones++;
		}
		/*System.out.println("valores zi:");
		for (int i = 0; i < zi.length; i++) {
			System.out.print(zi[i] + " ");
		}
		System.out.println("simulaciones:");
		for (int i = 0; i < ni.length; i++) {
			System.out.print(ni[i] + " ");
		}*/
		mejorMovimiento = hijos.get(mejorEstado(zi, ni));
		//System.out.println("Movimiento elegido: " + mejorMovimiento.toString());
		//esperar();
		tiempoTotal += System.currentTimeMillis() - tiempo;
		return mejorMovimiento;
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
		//System.out.println("probabilidades: ");
		for (int i = 0; i < zi.length; i++) {
			double pr = (double)zi[i] / (double)ni[i];
			//System.out.print(pr + " ");
			if (pr > prMayor) {
				mayor = i;
				prMayor = pr;
			}
		}
		return mayor;
	}

	@Override
	public String getEstadisticas() {
		String res = "Número de simulaciones por movimiento: " + nSimulaciones / numMovimientos;
		res += "\nTiempo medio de cada simulación: ";
		//res += Util.formatearTiempo(limiteT/(nSimulaciones/numMovimientos));
		res += Util.formatearTiempo(tiempoSimulacion/(numMovimientos*nSimulaciones));
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
	}	
}
