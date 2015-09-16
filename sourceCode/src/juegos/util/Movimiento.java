package juegos.util;

/**
 * Representación de un movimiento realizado en un tablero durante el juego.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 07/08/2011
 */
public class Movimiento {

	private int fila;	
	private int columna;
	
	/**
	 * @param f	Fila.
	 * @param c	Columna.
	 */
	public Movimiento(int f, int c) {
		this.fila = f;
		this.columna = c;
	}
	
	/**
	 * @return Fila.
	 */
	public int getFila() {
		return fila;
	}
	
	/**
	 * @return Columna.
	 */
	public int getColumna() {
		return columna;
	}
	
	@Override
	public String toString() {
		return new String("(" + fila + "," + columna + ")");
	}
}
