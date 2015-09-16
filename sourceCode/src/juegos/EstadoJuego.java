package juegos;

import java.util.List;

import juegos.util.Ficha;

/**
 * Representación de los estados de un juego.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 04/08/2011
 *
 */
public interface EstadoJuego {

	/**
	 * 
	 * @return Lista de estados directamente accesibles.
	 */
	public List<EstadoJuego> hijos();
	
	/**
	 * Genera un hijo aleatorio accesible a partir del estado actual.
	 * 
	 * @return	Hijo generado aleatoriamente.
	 */
	//public EstadoJuego hijoAleatorio();
	
	/**
	 * 
	 * @return Ficha del jugador que ha ganado, o null si nadie ganó.
	 */
	public Ficha ganador();
	
	/**
	 * 
	 * @return Verdadero si el estado es final (y hay empate), falso en otro caso.
	 */
	public boolean agotado();
	
	/**
	 * 
	 * @return Verdadero si es el turno del primer jugador, falso en otro caso.
	 */
	public boolean jug1();
	
	/**
	 * 
	 * @return Descripción del estado.
	 */
	public String toString();
	
	/**
	 * 
	 * @return Clave que permite indexar el estado.
	 */
	public String clave();
	
	/**
	 * 
	 * @return Ficha del jugador al que le toca jugar.
	 */
	public Ficha fichaActual();
	
	/**
	 * 
	 * @return Ficha del jugador contrario al que le toca jugar.
	 */
	public Ficha fichaOtro();
	
	/**
	 * 
	 * @param o	Estado a comparar.
	 * @return	Verdadero si el estado e y el propio estado tienen la misma clase y claves.
	 */
	public boolean equals(Object o);
}
