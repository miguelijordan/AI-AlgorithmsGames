package juegos.go.estrategias.agentes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import juegos.EstadoJuego;
import juegos.go.juego.Go;
import juegos.util.Movimiento;
import estrategias.agentes.Jugador;

/**
 * Clase JugadorHumanoGo.
 * Representa un jugador humano para el juego del Go.
 * Los movimientos se realizan a través del teclado.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 1.00, 3/10/2011
 *
 */
public class JugadorHumanoGo implements Jugador {

	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		Go estado = (Go) e;
		Movimiento m;
		
		do {
			m = pedirMovimiento();
		} while (!estado.movimientoValido(m.getFila(), m.getColumna()));
		return estado.elegirSuc(m.getFila(), m.getColumna());
	}

	/**
	 * Pide un movimiento (fila, columna) donde poner una ficha.
	 * 
	 * @return Movimiento (fila, columna).
	 */
	private Movimiento pedirMovimiento () {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		String linea;
		int f = 0;
		int c = 0;
		System.out.println("f,c:");
		try {
			linea = br.readLine();
			String[] p = linea.split(",");
			f = Integer.parseInt(p[0]);
			c = Integer.parseInt(p[1]);
		} catch (IOException e) {
			return null;
		}
		return new Movimiento(f,c);
	}
	
	@Override
	public String toString() {
		return "Jugador Humano para el Go.";
	}
}
