package estrategias.agentes;

import juegos.EstadoJuego;

/**
 * Representación de un jugador.
 * Toda clase que represente a un agente jugador debe implementar esta interfaz.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 09/08/2011 
 *
 */
public interface Jugador {

	/**
	 * 
	 * @param e Estado.
	 * @return	Estado resultante de que el jugador mueva en el estado e.
	 */
	public EstadoJuego mueve(EstadoJuego e);
	
	/**
	 * Proporciona información sobre el jugador.
	 * 
	 * @return	Información.
	 */
	public String toString();
}
