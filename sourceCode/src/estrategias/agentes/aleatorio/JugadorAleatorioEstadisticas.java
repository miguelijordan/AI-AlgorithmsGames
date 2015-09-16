package estrategias.agentes.aleatorio;

import java.util.List;
import java.util.Random;

import juegos.EstadoJuego;
import estrategias.agentes.estadisticas.EstadisticasJugador;



/**
 * Estrategia aleatoria.
 * Este jugador realizará movimientos aleatorios.
 * Este jugador puede jugar a cualquier juego que implemente la interfaz EstadoJuego.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 16/08/2011
 *
 */
public class JugadorAleatorioEstadisticas extends JugadorAleatorio implements EstadisticasJugador {
	
	private int numMovimientos;
	private long tiempoTotal;
	
	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		numMovimientos++;
		long tiempo = System.currentTimeMillis();
		
		List<EstadoJuego> lh = e.hijos();
		int n = rd.nextInt(lh.size());
		
		tiempoTotal += System.currentTimeMillis() - tiempo;
		
		return lh.get(n);
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
