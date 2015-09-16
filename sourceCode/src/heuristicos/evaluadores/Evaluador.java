package heuristicos.evaluadores;

import java.io.Serializable;

import juegos.EstadoJuego;
import juegos.util.Ficha;

/**
 * Función de evaluación heurística que deben implementar los objetos evaluadores heurísticos.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 16/08/2011
 *
 */
public interface Evaluador {

	/**
	 * Función de evaluación heurística.
	 * 
	 * @param estado	Estado del juego.
	 * @param fmax		Ficha de MAX.
	 * @param fmin		Ficha de MIN.	
	 * @return			Evaluación del estado suponiendo que MAX juega con la ficha fmax y MIN con fmin.
	 */
	public double evaluacion(EstadoJuego estado, Ficha fmax, Ficha fmin);
	
	public String toString();

}
