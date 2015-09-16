package heuristicos.aprendizaje;

import juegos.EstadoJuego;

/**
 * Esta interfaz permite entrenar un evaluador mediante el método de las diferencias temporales.
 * 
 * @author José Miguel Horcas Aguilera
 *
 */
public interface DiferenciasTemporales {

	/**
	 * Enseña al evaluador que e es un estado ganador.
	 * 
	 * @param e	Estado.
	 */
	public void estadoGanador(EstadoJuego e);
	
	/**
	 * Enseña al evaluador que e es un estado perdedor.
	 * 
	 * @param e	Estado.
	 */
	public void estadoPerdedor(EstadoJuego e);
	
	/**
	 * Enseña al evaluador que e es un estado final de empate.
	 * 
	 * @param e	Estado.
	 */
	public void estadoEmpate(EstadoJuego e);

	/**
	 * Entrena al evaluador empleando diferencias temporales y siendo e2 sucesor de e.
	 * 
	 * @param e		Estado.
	 * @param e2	Estado sucesor de e.
	 */
	public void actualizaDt(EstadoJuego e, EstadoJuego e2);
}
