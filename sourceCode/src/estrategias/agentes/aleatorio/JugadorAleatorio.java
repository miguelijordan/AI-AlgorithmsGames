package estrategias.agentes.aleatorio;

import java.util.List;
import java.util.Random;

import estrategias.agentes.Jugador;

import juegos.EstadoJuego;



/**
 * Estrategia aleatoria.
 * Este jugador realizará movimientos aleatorios.
 * Este jugador puede jugar a cualquier juego que implemente la interfaz EstadoJuego.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 16/08/2011
 *
 */
public class JugadorAleatorio implements Jugador {

	protected static Random rd = new Random();
	
	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		List<EstadoJuego> lh = e.hijos();
		int n = rd.nextInt(lh.size());
		return lh.get(n);
	}
	
	@Override
	public String toString() {
		return "Aleatorio";
	}
}
