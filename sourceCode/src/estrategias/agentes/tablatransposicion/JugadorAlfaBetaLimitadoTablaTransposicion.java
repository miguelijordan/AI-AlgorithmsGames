package estrategias.agentes.tablatransposicion;

import heuristicos.evaluadores.Evaluador;

import java.util.Iterator;

import juegos.EstadoJuego;
import juegos.util.Ficha;

import estrategias.agentes.evaluar.JugadorEvaluar;
import estrategias.util.Alarma;
import estrategias.util.EstadoValor;



/**
 * Estrategia minimax con poda alfa-beta, con límite de tiempo en la búsqueda y tabla de transposición.
 * Este jugador realiza una búsqueda con poda alfa-beta y dispone de un tiempo limitado para evaluar los estados de un juego.
 * Hace uso de una tabla de transposición para detectar los estados que ya han sido visitados y evaluados
 * anteriormente por otro camino.
 * 
 * @author José Miguel Horcas Aguilera
 * @version 2.00, 18/08/2011
 * 
 */
public class JugadorAlfaBetaLimitadoTablaTransposicion extends JugadorEvaluar {

	/**
	 * Límite de tiempo.
	 */
	protected long limiteT;
	
	/**
	 * Indica si se ha explorado el árbol de juegos completamente.
	 */
	private boolean finBusqueda;	
	
	/**
	 * Tabla de transposición.
	 */
	protected TablaTransposicion tt;

	/**
	 * Crea un jugador alfa-beta con un evaluador, un tiempo límite de búsqueda y
	 * una tabla de transposición inicialmente vacía.
	 * 
	 * @param ev		Evaluador.
	 * @param limiteT	Límite de tiempo.
	 */
	public JugadorAlfaBetaLimitadoTablaTransposicion (Evaluador ev, long limiteT) {
		super(ev);
		this.limiteT = limiteT;
		this.finBusqueda = false;
		this.tt = new TablaTransposicion();
	}
	
	@Override
	public EstadoJuego mueve(EstadoJuego e) {
		EstadoJuego movimiento = podaAlfaBetaLimitado(e, e.fichaActual(), e.fichaOtro());
		if (movimiento == null) {
			movimiento = barajar(e.hijos()).get(0);		// Por si no le da tiempo.
		}
		return movimiento;
	}
	
	/**
	 * Realiza la búsqueda con poda alfa-beta dado un límite de tiempo.
	 * 
	 * @param e		Estado.
	 * @param fmax	Ficha de MAX.
	 * @param fmin	Ficha de MIN.
	 * @return		Mejor movimiento.
	 */
	public EstadoJuego podaAlfaBetaLimitado(EstadoJuego e, Ficha fmax, Ficha fmin) {
		Alarma a = new Alarma(limiteT);
		EstadoValor mejorMovimiento = null;
		EstadoValor movimiento = null;
		setFinBusqueda(false);
		int cota = 0;
		while (!a.alarma() && !isFinBusqueda()) {
			tt = new TablaTransposicion();
			movimiento = podaAlfaBetaConAlarma(e, cota, fmax, fmin, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, a, 1);
			if (!a.alarma()) {
				if (movimiento.getEstado() != null) {
					mejorMovimiento = movimiento;
				}
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
	 * Realiza una búsqueda con poda alfa-beta durante un tiempo limitado.
	 * Devuelve el mejor movimiento posible junto con su evaluación.
	 * 
	 * @param e			Estado inicial del juego.
	 * @param prof		Profundidad máxima de búsqueda.
	 * @param fmax		Ficha del jugador MAX.
	 * @param fmin		Ficha del jugador MIN.
	 * @param alfa		Valor de corte alfa.
	 * @param beta		Valor de corte beta.
	 * @param alarma	Alarma.
	 * @param signo		Tomará inicialmente el valor 1, e irá alternando de signo con la profundidad.
	 * @return			Mejor movimiento y su evaluación (objeto de la clase EstadoValor) o null si no se encontró ningún estado cuando el tiempo se acabe.
	 */
	public EstadoValor podaAlfaBetaConAlarma(EstadoJuego e, int prof, Ficha fmax, Ficha fmin, double alfa, double beta, Alarma alarma, int signo) {
		EstadoJuego mejorE = null;
		Double mejorV = Double.NEGATIVE_INFINITY;
		Double v2;
		EstadoJuego e2;

		if (alarma.alarma()) {
			mejorV = null;
		} else if (prof == 0 || e.ganador()!=null || e.agotado()) {
			mejorV = signo*evalua(e, fmax, fmin);
			if (e.agotado()) {
				setFinBusqueda(true);
			}
		} else if (tt.esta(e)) {		// Si esta en la tabla cojo la información almacenada.
			EstadoValor n = tt.getNodo(e);
			mejorV = n.getValor();
			mejorE = n.getEstado();
		} else {
			Iterator<EstadoJuego> i = barajar(e.hijos()).iterator();
			//Iterator<EstadoJuego> i = e.hijos().iterator();
			while (i.hasNext() && beta > alfa) {
				e2 = (EstadoJuego)i.next();
				v2 = podaAlfaBetaConAlarma(e2, prof-1, fmax, fmin, -beta, -alfa, alarma, -signo).getValor();
				if (v2 != null) {
					v2 = -v2;
					alfa = Math.max(alfa, v2);
					if (v2 >= mejorV) {
						mejorV = v2;
						mejorE = e2;
					}
				} else {
					mejorV = null;
					mejorE = null;
				}
			}
			if (i.hasNext()) {
				mejorV = Double.POSITIVE_INFINITY;
			}
			// Añado el mejor valor y el mejor movimiento para e en la tabla de transposición.
			EstadoValor n = new EstadoValor(mejorE, mejorV);
			tt.setNodo(e, n);
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
		String res = "Minimax con poda alfa-beta y tabla de transposición.";
		res += "\nLímite de tiempo: " + limiteT + " s.";
		res += "\n" + getEvaluador().toString();
		return res;
	}
}
