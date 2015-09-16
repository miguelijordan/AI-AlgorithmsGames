package juegos.conectak.estrategias.agentes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import juegos.EstadoJuego;
import juegos.conectak.juego.ConectaK;
import estrategias.agentes.Jugador;
import estrategias.agentes.estadisticas.EstadisticasJugador;

/**
 * Clase JugadorHumanoCK.
 * Representa un jugador humano para el juego del ConectaK.
 * Los movimientos se realizan a través del teclado.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 3/10/2011
 *
 */
public class JugadorHumanoCKEstadisticas extends JugadorHumanoCK implements EstadisticasJugador {

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
