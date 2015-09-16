package estrategias.agentes.alfabeta;

import heuristicos.evaluadores.Evaluador;

import java.util.Iterator;

import juegos.EstadoJuego;
import juegos.util.Ficha;

import estrategias.agentes.evaluar.JugadorEvaluar;
import estrategias.util.EstadoValor;


/**
 * Estrategia minimax con poda alfa-beta y límite de profundidad en la búsqueda.
 * Este jugador realiza una búsqueda con poda alfa-beta y evalua los estados de un juego hasta una determinada profundidad.
 * 
 * @author José Miguel Horcas Aguilera.
 * @version 2.00, 18/08/2011
 *
 */
public class JugadorAlfaBeta extends JugadorEvaluar {

	/**
	 * Profundidad máxima de búsqueda.
	 */
	private int profMax;
	
	/**
	 * Crea un jugador alfa-beta con un evaluador y una profundidad máxima de búsqueda.
	 * 
	 * @param ev				Evaluador.
	 * @param profundidadMaxima	Profundidad máxima de búsqueda.
	 */
	public JugadorAlfaBeta(Evaluador ev, int profundidadMaxima) {
		super(ev);
		this.profMax = profundidadMaxima;
	}
	
	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		EstadoValor mejorMovimiento = podaAlfaBeta(e, profMax, e.fichaActual(), e.fichaOtro(), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 1);
		setMejorMovimiento(mejorMovimiento);
		return mejorMovimiento.getEstado();
	}
	
	/**
	 * Realiza una búsqueda hasta la profundidad indicada con poda alfa-beta incluida.
	 * Devuelve el mejor movimiento posible junto con su evaluación.
	 * 
	 * @param e			Estado inicial del juego.
	 * @param prof		Profundidad máxima de búsqueda.
	 * @param fmax		Ficha del jugador MAX.
	 * @param fmin		Ficha del jugador MIN.
	 * @param alfa		Valor de corte alfa.
	 * @param beta		Valor de corte beta.
	 * @param signo		Tomará inicialmente el valor 1, e irá alternando de signo con la profundidad.
	 * @return			Mejor movimiento y su evaluación (objeto de la clase EstadoValor).
	 */
	public EstadoValor podaAlfaBeta(EstadoJuego e, int prof, Ficha fmax, Ficha fmin, double alfa, double beta, int signo) {
		EstadoJuego mejorE = null;
		double mejorV = Double.NEGATIVE_INFINITY;
		double v2;
		EstadoJuego e2 = null;

		if (prof == 0 || e.ganador()!=null || e.agotado()) {
			mejorV = signo*evalua(e, fmax, fmin);
		} else {
			Iterator<EstadoJuego> i = barajar(e.hijos()).iterator();
			//Iterator<EstadoJuego> i = e.hijos().iterator();
			while (i.hasNext() && beta > alfa) {
				e2 = i.next();
				v2 = -podaAlfaBeta(e2, prof-1, fmax, fmin, -beta, -alfa, -signo).getValor();
				alfa = Math.max(alfa, v2);
				if (v2 >= mejorV) {
					mejorV = v2;
					mejorE = e2;
				}
			}
			if (i.hasNext()) {	// Se ha producido poda.
				//No debe escoger ningún hijo de esta rama porque puede ser PEOR.
				mejorV = Double.POSITIVE_INFINITY;
			}
		}
		return new EstadoValor(mejorE, mejorV);
	}

	/**
	 * @return Profundidad máxima de búsqueda.
	 */
	public int getProfMax() {
		return profMax;
	}	
	
	@Override
	public String toString() {
		String res = "Minimax con poda alfa-beta.";
		res += "\nProfundidad máxima de búsqueda: " + profMax;
		res += "\n" + getEvaluador().toString();
		return res;
	}
}
