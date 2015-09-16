package juegos.go.estrategias.agentes;

import estrategias.agentes.estadisticas.EstadisticasJugador;

/**
 * Clase JugadorHumanoGo.
 * Representa un jugador humano para el juego del Go.
 * Los movimientos se realizan a través del teclado.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 3/10/2011
 *
 */
public class JugadorHumanoGoEstadisticas extends JugadorHumanoGo implements EstadisticasJugador {

	private int numMovimientos;
	private long tiempoTotal;
	
	/**
	 * @param numMovimientos the numMovimientos to set
	 */
	public void setNumMovimientos(int numMovimientos) {
		this.numMovimientos = numMovimientos;
	}
	
	/**
	 * @param tiempoTotal the tiempoTotal to set
	 */
	public void setTiempoTotal(long tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
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
