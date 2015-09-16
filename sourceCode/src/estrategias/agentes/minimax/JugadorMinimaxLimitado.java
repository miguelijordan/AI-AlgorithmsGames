package estrategias.agentes.minimax;

import heuristicos.evaluadores.Evaluador;
import juegos.EstadoJuego;
import juegos.util.Ficha;
import estrategias.agentes.evaluar.JugadorEvaluar;
import estrategias.util.Alarma;
import estrategias.util.EstadoValor;

/**
 * Estrategia minimax con límite de tiempo.
 * Este jugador dispone de un tiempo limitado para realizar la evaluación minimax de los estados de un juego. 
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 16/08/2011
 *
 */
public class JugadorMinimaxLimitado extends JugadorEvaluar {

	/**
	 * Límite de tiempo.
	 */
	private long limiteT;
	
	/**
	 * Indica si se ha explorado el árbol de juegos completamente.
	 */
	private boolean finBusqueda;

	/**
	 * Crea un jugador minimax con un evaluador y un límite de tiempo.
	 * 
	 * @param ev		Evaluador.
	 * @param limiteT	Límite de tiempo.
	 */
	public JugadorMinimaxLimitado(Evaluador ev, long limiteT) {
		super(ev);
		this.limiteT = limiteT;
		this.finBusqueda = false;
	}
	
	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		EstadoJuego movimiento = minimaxLimitado(e, e.fichaActual(), e.fichaOtro());
		if (movimiento == null) {
			movimiento = barajar(e.hijos()).get(0);		// Por si no le da tiempo.
		}
		return movimiento;
	}
	
	/**
	 * Realiza la evaluación MINIMAX de un estado dado un límite de tiempo.
	 * 
	 * @param e		Estado.
	 * @param fmax	Ficha de MAX.
	 * @param fmin	Ficha de MIN.
	 * @return		Mejor movimiento.
	 */
	public EstadoJuego minimaxLimitado(EstadoJuego e, Ficha fmax, Ficha fmin) {
		Alarma a = new Alarma(limiteT);
		EstadoValor mejorMovimiento = null;
		EstadoValor movimiento = null;
		setFinBusqueda(false);
		int cota = 0;
		while (!a.alarma() && !isFinBusqueda()) {
			movimiento = negamaxConAlarma(e, cota, fmax, fmin, a, 1);
			if (!a.alarma()) {
				if (movimiento.getEstado() != null) {
					mejorMovimiento = movimiento;
				}
				// Comprueba si se ha llegado al final del árbol de búsqueda antes de que salte la alarma
				// El movimiento devuelto puede ser ganador o perdedor.
				if (movimiento.getValor() == Double.POSITIVE_INFINITY || movimiento.getValor() == Double.NEGATIVE_INFINITY) {
					setFinBusqueda(true);
				}
			}
			cota = cota + 1;
		}
		setMejorMovimiento(mejorMovimiento);
		return mejorMovimiento.getEstado();
	}
	
	/**
	 * Realiza una evaluación MINIMAX de un estado dado en un tiempo límite.
	 * Devuelve el mejor movimiento junto con su evaluación.
	 * 
	 * @param e			Estado.
	 * @param prof		Profundidad de búsqueda.
	 * @param fmax		Ficha de MAX.
	 * @param fmin		Ficha de MIN.
	 * @param alarma	Alarma.
	 * @param signo		Tomará inicialmente el valor 1, e irá alternando de signo con la profundidad.
	 * @return			Mejor movimiento y su evaluación (objeto de la clase EstadoValor) o null si no se encontró ningún estado cuando el tiempo se acabe.
	 */
	public EstadoValor negamaxConAlarma(EstadoJuego e, int prof, Ficha fmax, Ficha fmin, Alarma alarma, int signo) {
		EstadoJuego mejorE = null;
		Double mejorV = Double.NEGATIVE_INFINITY;
		Double v2;
		if (alarma.alarma()) {
			mejorV = null;
		} else if (prof == 0 || e.ganador()!=null || e.agotado()) {
			mejorV = signo*evalua(e, fmax, fmin);
			if (e.agotado()) {
				setFinBusqueda(true);
			}
		} else {
			for (EstadoJuego e2 : barajar(e.hijos())) {
			//for (EstadoJuego e2 : e.hijos()) {
				v2 = negamaxConAlarma(e2, prof-1, fmax, fmin, alarma, -signo).getValor();
				if (v2 != null) {
					v2 = -v2;
					if (v2 >= mejorV) {
						mejorV = v2;
						mejorE = e2;
					}
				} else {
					mejorV = null;
					mejorE = null;
				}
			}
		}
		return new EstadoValor(mejorE, mejorV);
	}

	/**
	 * @return Límite de tiempo.
	 */
	public long getLimiteT() {
		return limiteT;
	}

	/**
	 * @return Verdadero si el árbol de juegos se ha explorado completamente, falso en otro caso.
	 */
	public boolean isFinBusqueda() {
		return finBusqueda;
	}

	/**
	 * Actualiza si el árbol de juegos se ha explorado completamente o no.
	 * 
	 * @param finBusqueda Nuevo valor. 
	 */
	public void setFinBusqueda(boolean finBusqueda) {
		this.finBusqueda = finBusqueda;
	}
	
	@Override
	public String toString() {
		String res = "Minimax.";
		res += "\nLímite de tiempo: " + limiteT + " s.";
		res += "\n" + getEvaluador().toString();
		return res;
	}
}
