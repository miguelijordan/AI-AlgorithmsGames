package estrategias.util;

import juegos.EstadoJuego;

/**
 * Clase EstadoValor (Clase de implementación).
 * Almacena un estado del juego junto con el valor de su evaluación.
 * Esta clase permitirá devolver como un único objeto los valores necesarios
 * de los diferentes algoritmos minimax (negamax, alfa-beta),...
 *
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 07/08/2011
 *
 */
public class EstadoValor {

	/**
	 * Estado del juego.
	 */
	private EstadoJuego estado;
	
	/**
	 * Valor de la evaluación del estado.
	 */
	private Double valor;
	
	/**
	 * @param e	Estado del juego.
	 * @param v	Valor de la evaluación del estado.
	 */
	public EstadoValor(EstadoJuego e, Double v) {
		this.estado = e;
		this.valor = v;
	}
	
	/**
	 * @return Estado del juego.
	 */
	public EstadoJuego getEstado() {
		return estado;
	}
	
	/**
	 * @return Valor de la evaluación del estado.
	 */
	public Double getValor() {
		return valor;
	}

	/**
	 * @param e	Nuevo estado del juego.
	 */
	public void setEstado(EstadoJuego e) {
		this.estado = e;
	}

	/**
	 * @param v	Nuevo valor de evaluación del estado.
	 */
	public void setValor(Double v) {
		this.valor = v;
	}
}