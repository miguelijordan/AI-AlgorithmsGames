package juegos.conectak.estrategias.agentes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import juegos.EstadoJuego;
import juegos.conectak.juego.ConectaK;
import estrategias.agentes.Jugador;

/**
 * Clase JugadorHumanoCK.
 * Representa un jugador humano para el juego del ConectaK.
 * Los movimientos se realizan a través del teclado.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 3/10/2011
 *
 */
public class JugadorHumanoCK implements Jugador {

	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		ConectaK estado = (ConectaK) e;
		int c;
		
		do {
			c = pedirMovimiento();
		} while (!estado.posibleP(c));
		return estado.elegirSucNth(c);
	}

	/**
	 * Pide una columna sobre la que soltar una ficha.
	 * 
	 * @return Columna.
	 */
	private int pedirMovimiento () {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		String linea;
		
		System.out.println("¿En qué columna soltamos la ficha?");
		try {
			linea = br.readLine(); 
		} catch (IOException e) {
			return -1;
		}
		return Integer.parseInt(linea);
	}

	@Override
	public String toString() {
		return "Jugador Humano para el Conecta-K.";
	}
	
	
}
